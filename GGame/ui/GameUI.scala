package GGame.ui

import GGame.*
import GGame.classes.*

import scala.io.StdIn.*

object GameUI extends App:

  private val game = Game()
  private val player = this.game.player
  this.run()


  private def run() =
    println()
    changeName()
    println()
    this.setDifficulty()
    this.welcome()
    this.game.setupGame()
    while !this.game.isOver do // plays turns as long as
      if !this.player.isBossRoom then this.playTurn()
      else this.runBossBattle()
    nextPage()
    println(this.game.goodbyeMessage)



  private def nextPage() = println("\n" * 15)



  private def changeName() =
    val name = readLine("Enter your characters name: ").trim
    if name.nonEmpty then this.player.changeName(name)



  private def setDifficulty() =
    var select = readLine("Please select difficulty.\n1 - Cheat | 2 - Normal | 3 - GOBLINDIEFAST\nSelect: ").toIntOption.getOrElse(0)
    while select < 1 || select > 3 do
      select = readLine("\nInvalid input.\n\nPlease select difficulty.\n1 - Cheat | 2 - Normal | 3 - GOBLINDIEFAST\nSelect: ").toIntOption.getOrElse(0)
    this.player.setDifficulty(select)



  private def playTurn() = // "divider"
    if this.player.isInCombat then this.runCombat() // if player started combat in previous turn plays combat minigame.
    else this.runNormalTurn()

  

  private def runBossBattle() =
    nextPage()
    this.printAreaInfo()
    val command = readLine("\nCommand: ")
    println(s"""Unknown command: "$command". Try "help" if you're unsure what to do.""")
    readLine("\nPress Enter: ")
    nextPage()
    readLine("...\n\n\n\n\n\n\n\n\n\n\n\nPress Enter: ")
    println("\nLoOkInG fOr mE?")
    readLine("\nPress Enter: ")
    println("\nThe Dark Emperor: So, you've arrived... I've heard a lot of rumors about you from my... servants.")
    println("I don't understand what exactly was your plan but I DON'T CARE!")
    println("IM GONNA ENJOY DESTROYING YOU!!")
    readLine("\nPress Enter: ")
    println("\nThe Emperor approaches you.")
    readLine("\n\n[You cant run away from this battle]\n\nPress Enter: ")
    nextPage()
    this.player.forceBossBattle()
    this.runCombat()




  private def welcome() =
    println()
    println(this.game.title + "\n" + "-" * this.game.title.length)
    readLine("Press Enter: ")
    println("\nWelcome to Saving the Grove! You play as a goblin and you have just waken up to terrible news. The Grove's entire treasury has been robbed!")
    println("The robbers are are the infamous group: Crussadders. These ill wanting rabble-rousers have pillaged the world in search for an holy artifact. \nThe one my ancestor sealed inside the Gobbo Gauntlet.")
    println("With this artifact, the Crussadders are trying to summon a demonic being and possess the whole of earth.")
    readLine("Press Enter: ")
    println()
    println("You take it on yourself to figure this whole ordeal but don't worry you don't have to do it alone! \nYou'll face tough enemies, but with enough skill and luck you can do it!")
    readLine("Press Enter: ")
    println()
    println("The Crussadders have started their ritual already so be quick!. A curse is spreading around the world and ordinary creatures are being posessed.")
    println("The only way to keep the curse at bay is carrying silver metal! So if you don't want to succumb to the dark forces make sure to always carry it!")
    readLine("Press Enter: ")
    println()
    println("In short your \"HP\" is the amount of Silver coins you have. \nHP of your bag — where you carry your silver — gives an indication of how much silver you are losing every turn.")
    println("So keep your bag intact and If you're ever running low on silver you can buy it in change for gold from shops across the world!")
    readLine("Press Enter: ")
    println()
    println("One more thing. You can see the list of available commands by typing \"help\". \nAfter entering a command you get an action report telling you what happened.")
    readLine("Press Enter: ")


  def printAreaInfo() =
    val area = this.player.location
    println("\n\n" + area.name)
    println("-" * area.name.length)
    println(area.fullDescription + "\n\n" + this.player.playerInfo + "\n" + "-" * this.player.playerInfo.length)


  // combat logic
  def runCombat() =
    val combat = this.player.currentCombat
    nextPage()
    this.game.combatPrimer()
    println(this.game.combatWelcome(combat))
    while this.player.isInCombat && !player.isDefeated do // This runs it
      if combat.isEnemyTurn then
        readLine(s"\n\n${combat.enemy.name} seethes in anger: Enemy is about to strike.\nPress Enter: ")
        println(this.game.combatEnemyTurn(combat))
        readLine("\nPress enter: ")
        nextPage()
      println()
      println(combat.combatInfo)
      while !combat.isEnemyTurn && !this.player.isImmobilized do
        if combat.playerIsAttacking then
          readLine("Press Enter: ")
          val timer = System.currentTimeMillis()
          println(s"\nYour combo is: ${combat.combo}")
          val input = readLine("Input combo: ").toUpperCase
          println(combat.attackPhase2(input, timer))
        else
          val command = readLine("Command: ")
          println()
          println(this.game.combatPlayerTurn(command, combat))
      end while
      if this.player.isImmobilized then
        combat.advanceTurn()
        readLine("You are stunned. Press Enter: ")
    end while
    println()
    println(this.game.combatEnd(combat)) // combat over
    readLine("Press Enter: ")
    nextPage()


  // turn logic
  def runNormalTurn() =
    if !this.player.isDefeated then
      nextPage()
      this.printAreaInfo()
      val oldLocation = this.player.location
      var validCommand = false
      while !validCommand do
        println()
        val command = readLine("Command: ")
        val action = Action(command)
        val outcomeReport = action.execute(this.player)
        validCommand = outcomeReport.isDefined
        println("\n" + outcomeReport.getOrElse(s"""Unknown command: "$command". Try "help" if you're unsure what to do."""))
        if this.player.isBuying then println(this.player.buyXMany(scala.math.max(0, readLine("How many?: ").toIntOption.getOrElse(0))))
      end while
      println(this.game.runNormalEnd(oldLocation)) // Only if player played "go" command, drops coins and rolls for randomGOld.
      readLine("\nPress Enter: ") // after this, goes back to GameUI.run() while loop beginning (If player doesn't die/quit in this turn)



  def attackRattle(timer: Long, combat: Combat) =
    val skill = this.player.weapon match
      case None => 1
      case Some(weapon) => weapon.skillRating
    var counter = 0
    while (System.currentTimeMillis - timer) < skill * 500 do
      readLine("Charge attack: ")
      counter += 1
    var ok = ""
    while ok != "ok" do
      ok = readLine("Attack over! Type \"ok\": ").trim.toLowerCase
    combat.attackRattle(counter, skill)

end GameUI

