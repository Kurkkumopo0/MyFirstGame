package GGame.classes

import scala.math.*

// Clashers are entities that can partake in combat. Consist of class player and enemyNPC
// Trait contains all the methods that change status of this entity

trait Clashers(var clasherName: String, val maxHP: Int, private var attackPOW: Int, private var defencePOW: Int):

  private var hp = maxHP // Hp of entity. Cannot exceed max value.

  //Amount of turns entity is under effect, is reduced by 1 every turn; enemy or player. Min value = 0
  private var isImmobilizedForXTurns = 0
  private var strengthBuffedForXTurns = 0
  private var defenceBuffedForXturns = 0

  //Possible buffs/debuffs from effects
  private var strengthBuff = 0
  private var defenceBuff = 0

  //Returns remaining hp
  def hpRemaining: Int = hp


  // changes entities hp value. Hp cannot exceed maxHP. Returns String for Effects class method.
  def changeHP(howMuch: Int): String =
    hp = max(0, min(this.maxHP, hp + howMuch))
    if howMuch < 0 then s"${this.clasherName} took ${howMuch * -1} damage."
    else s"${this.clasherName} repaired some holes in the bag. Bag hp increased by $howMuch."

  // Called by combat. Calculation logic, min max etc. is defined in Combat class.
  def takeDamage(howMuch: Int): Unit =
    hp = max(0, hp - max(howMuch, 0))

  def repair() = 
    hp = maxHP

  // Changes entities strength for howLong turns. If howLong is not defined, changes defencePOW permanently.
  // Returns String for Effects class method.
  def changeStrength(power: Int, howLong: Option[Int]): String =
    howLong match
      case None => attackPOW += power; s"Permanently boosted ${this.clasherName}'s damage by $power"
      case Some(duration) =>
        strengthBuff = power
        strengthBuffedForXTurns = duration * 2  // Calculated this way because how advanceCombat() is called twice every full turn.
        if power > 0 then s"${this.clasherName} buffed up. Damage increased by $power for $duration turns."
        else s"${this.clasherName} weakened. Damage decreased by ${power * -1} for $duration turns."

  // changes entities defence for howLong turns. If not defined, changes defencePOW permanently.
  def changeDefence(power: Int, howLong: Option[Int]) =
    howLong match
      case None => defencePOW += power; s"Permanently boosted ${this.clasherName}'s defence by $power"
      case Some(duration) =>
        defenceBuff = power
        defenceBuffedForXturns = duration * 2
        if power > 0 then s"${this.clasherName} toughened up. Defence increased by $power for $duration turns."
        else s"${this.clasherName} got vulnerable. Defence decreased by ${power * -1} for $duration turns."

  // Determines the entities current raw strength/defence taking into account its buffs/debuffs.
  // Damage and defence methods use this.
  private def strenght = 
    val buff = if strengthBuffedForXTurns != 0 then strengthBuff else 0
    max(0, attackPOW + buff) // can't be negative

  private def toughness = 
    val buff = if defenceBuffedForXturns != 0 then defenceBuff else 0
    max(0, defencePOW + buff) // can't be negative

  // Determines the damage this entity deals and the damage it receives. Default definition.
  // Overridden in class Player
  def damage: Int = this.strenght
  def defence: Int = this.toughness

  //Stuns the entity, rendering it unable to attack/use a command.
  def stun(howLong: Option[Int]) = howLong match
    case None => ""
    case Some(duration) => isImmobilizedForXTurns = duration * 2 //Each entity uses a turn in combat, so 1 full turn = 2 turns
      s"${this.clasherName} is now stunned for $duration turns."

  //Called every combat turn. Reduces the time remaining on effects.
  def reduceStatusEffectCounter() =
    isImmobilizedForXTurns = max(0, isImmobilizedForXTurns - 1)
    defenceBuffedForXturns = max(0, defenceBuffedForXturns - 1)
    strengthBuffedForXTurns = max(0, strengthBuffedForXTurns - 1)

  //Clear all effects affecting the entity. Called for both combatants at the start of combat.
  def clearEffects() =
    isImmobilizedForXTurns = 0
    defenceBuffedForXturns = 0
    strengthBuffedForXTurns = 0

  // if entity is immobilized/stunned it cannot play a turn.
  def isImmobilized = isImmobilizedForXTurns != 0
  
  // Determines if this entity is defeated in combat.
  // Overridden in class Player
  def isDefeated = hp <= 0

  def changeName(name: String) = clasherName = name
  
end Clashers






  
  
  


