package GGame.classes

import GGame.Values.*
import GGame.classes.{Action, Area, Combat}


class Game:


  //The player of the game. Starting stats are defined here.
  val player = Player(home)
  val title = "Saving the Grove"
  //Checks if the player has won the game
  def isComplete = emperor.isDefeated

  //Checks whether the game is over or not. Doesn't tell if it was won or lost.
  def isOver = this.player.isDefeated || this.player.hasQuit || this.isComplete

  //Sets the difficulty of the game. Called once at the start of the game.
  def setDifficulty(set: Int) = this.player.setDifficulty(set)

  //Final message displayed at the end of the game. The message depends on the outcome.
  def goodbyeMessage =
    if !this.player.hasQuit then
      if this.player.isDefeated then "You have lost all your silver coins. Eternal darkness surrounds you as fade into unconsciousness. Game over."
      else "You did it! You defeated the Crussadders and got the treasure back!.\nNow it's time to go clean up Boggy Marsh to their former glory!\n\nThank you for playing Save the Grove! Created by: Kasper Tulonen and Aaro Lamberg"
    else "You quit the game."

  //Calls the singleton object Setup. Its only method sets up the game. Adds NPCs, items, neighborships between Areas and offers for Shops.
  def setupGame() = Setup.setup()



  def runNormalEnd(oldLocation: Area) = if this.player.location != oldLocation then this.player.dropCoins + this.player.randomGold else "\n"

  def combatPrimer() =
    val combat = this.player.currentCombat
    this.player.clearEffects() //
    combat.enemy.clearEffects() // combat primer
    combat.enemy.repair() //


  def combatEnemyTurn(combat: Combat) =
    combat.advanceTurn()
    val eTurn = if !combat.enemy.isImmobilized then combat.enemyTurn
    else s"${combat.enemy.name} is stunned!"
    eTurn + "\n" + combat.player.dropCoins


  def combatEnd(combat: Combat) =
    this.player.exitCombat()
    if combat.enemy.isDefeated then
      combat.removeEnemy
      this.player.receiveGold(10) //reward. Note: not println since goodbyeCombat includes mention of reward
    combat.goodbyeCombat

  def combatWelcome(combat: Combat) = combat.welcomeCombat

  def combatPlayerTurn(command: String, combat: Combat) =
    val action = Action(command)
    val outcomeReport = action.combatExecute(combat) // Most commands will advanceTurn() => makes it enemy's turn. Note: not all commands call advance turn.
    outcomeReport.getOrElse(s"""Unknown command: "$command". Try "help" if you're unsure what to do.""".stripMargin)




end Game

