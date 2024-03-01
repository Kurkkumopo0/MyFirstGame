package GGame.classes

import scala.io.StdIn.*
import scala.math.*
import scala.util.Random

class Combat(val player: Player, val enemy: EnemyNPC):

  private var turn = 0                               // Even turns = player turns, uneven = enemy
  private var playerRanAway = false                  // One-way flag that determines whether the combat should continue
  private val silverAtBeginning = player.silver
  private val bagHPAtBeginning = player.hpRemaining
  private var enemyIsIdentified = false
  private var attackNextPhase = false
  private var currentCombo = ""

  //Combat works as following: First the player gets a turn, if they defeat the enemy, the combat ends.
  //Secondly, the enemy gets a turn.

  //Turn counter
  def turnCount: Int = turn
  //This method is called at the end of a turn. Adds 1 to turn counter and reduces the duration of effects on combatants
  def advanceTurn(): Unit =
    turn += 1
    attackNextPhase = false
    this.player.reduceStatusEffectCounter()
    this.enemy.reduceStatusEffectCounter()
  //True if it's the enemies turn to move
  def isEnemyTurn: Boolean = turn % 2 == 1
  //Called at the start of combat. Returns the enemies unique line or 1 of 3 predetermined lines.
  def welcomeCombat: String = this.enemy.lineOrRandom + "\n" * 2

  //Returns the info text given to player before each combat turn.
  def combatInfo: String =
    val enemyInfo = if enemyIsIdentified then s"\nStats:\nHp: ${this.enemy.hpRemaining}/${this.enemy.maxHP}\nAttack: ${this.enemy.damage}\nDefence: ${this.enemy.defence}"
    else ""
    val combat = s"\nYou are in combat with ${enemy.name}."
    combat + "\n" + "-" * combat.length + s"\n$enemyInfo \n\n${player.bagCondition} ${player.hpRemaining} / ${player.maxHP} HP.\nYou have ${player.silver} Silver Coins and ${this.player.gold} Goldcoins left.\n"

  //Returns the outcome of the combat in a String.
  def goodbyeCombat: String =
    if playerRanAway then ""
    else if enemy.isDefeated then  // the good ending :)
      val silverLost = silverAtBeginning - this.player.silver
      s"You defeated ${enemy.name}. You lost: " + silverLost.toString + " Silver coins.\n" + "You got rewarded 10 Gold coins!\n\n" + this.player.bagCondition
    else s"You perish while fighting ${enemy.name}." // the bad ending :(


  //An enemy turn. Enemies can only attack.
  def enemyTurn: String =
    val tookDamage =
      if this.player.difficulty == 3 then max(0, this.enemy.damage * 1.5 - 0.3 * this.player.defence).toInt
      else max(0, this.enemy.damage - this.player.defence)
    player.takeDamage(tookDamage)
    val report = s"\nYour foe struck you and slashed your bag. Your precious bag took $tookDamage hp of damage."
    report + "\n" + "-" * report.length


  //Command "run". Player has a 60/40 chance of successfully escaping.
  def flee: String =
    this.advanceTurn()
    playerRanAway = if this.player.isBossRoom then false else Random.shuffle(Vector(true, true, true, false, false)).head
    if playerRanAway then "You ran away from the battle." else "You try to escape but trip and fall to the ground."


  def playerIsAttacking = attackNextPhase

  def cancelAttack = 
    attackNextPhase = false
    "Cancelled attack."
  
  //Method that determines whether the player wrote the combo fast enough. Called after pressing enter after command "attack".
  private def isInTime(startingTime: Long): Boolean =
    val skill = this.player.weapon match
      case None => 1
      case Some(weapon) => weapon.skillRating
    System.currentTimeMillis - startingTime <= skill * 500 + (4000 / pow(this.player.difficulty, 2))

  def combo = currentCombo

  //Command "attack". An attack is successful if the player types a random combo correctly fast enough.
  def attack: String =
    attackNextPhase = true
    var weapon = "Moneybag"
    var move = "strike"
    var skill = 1
    player.weapon match
      case None =>
      case Some(wpn) =>
        weapon = wpn.name
        move = wpn.attackMove
        skill = wpn.skillRating
    val instructions = move match
      case "rattle" => "[Repeatedly press Enter]"
      case other => "[Type your combo as fast as possible]"
    currentCombo = Random.shuffle("ABCDEFGHIJKLMNOPQRSTUVWXYZ").take(skill).toString
    s"You raise your $weapon. Are you ready to $move?\n$instructions\n\n"

  def attackPhase2(input: String, timer: Long) =
    advanceTurn()
    this.player.weapon match
    case None => attackStrike(input, timer) //default setup
    case Some(weapon) => weapon.attackMove match
      //case "rattle" => attackRattle(weapon)
      case other => attackStrike(input, timer)


  private def attackStrike(playerInput: String, timer: Long): String =
    if playerInput == combo then
      if isInTime(timer) then // "Stop timer"
        val tookDamage = max(0, player.damage - enemy.defence)
        inflictDamage(tookDamage)
        s"You hit your combo!\tEnemy took $tookDamage damage."
      else
        val tookDamage = max(0, (player.damage / 2.0).toInt - enemy.defence)
        inflictDamage(tookDamage)
        s"Your combo was not in time. Combo failed.\tEnemy took $tookDamage damage."
    else
      val tookDamage = max(0, (player.damage / 2.0).toInt - enemy.defence)
      inflictDamage(tookDamage)
      s"You failed your combo.\tEnemy took $tookDamage damage."


  def attackRattle(counter: Int, skill: Int): String =
    if counter < skill * (2 * player.difficulty - 1) + 1 then
      val tookDamage = max(0, (player.damage / 2.0).toInt - enemy.defence)
      inflictDamage(tookDamage)
      s"Attack failed. Enemy took $tookDamage damage."
    else
      val tookDamage = max(0, player.damage - enemy.defence)
      inflictDamage(tookDamage)
      s"Attack was successfull! Enemy took $tookDamage damage."


  private def inflictDamage(damage: Int) = this.enemy.takeDamage(damage)


  //Command: "identify". Returns the enemy stats each turn after this is called.
  def identify: String =
    this.advanceTurn()
    enemyIsIdentified = true
    s"You are battling: ${enemy.name}, ${enemy.description}\nEnemy HP: ${enemy.hpRemaining}/${enemy.maxHP}\nAttack: ${this.enemy.damage}\nDefence: ${this.enemy.defence}"


  def combatIsOver: Boolean = this.player.isDefeated || this.enemy.isDefeated || playerRanAway //Checks if the combat should end.

  def removeEnemy = this.player.location.removeNPC(enemy) //Removes the enemy from the Area

  //Command "quit". Ends the game.
  def quit: String =
    this.advanceTurn()
    this.playerRanAway = true
    this.player.quit


