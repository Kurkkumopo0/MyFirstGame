package GGame.ui




import scala.swing.*
import scala.swing.event.*
import javax.swing.UIManager
import GGame.classes.*

import java.awt.{Dimension, Insets, Point}
import scala.language.adhocExtensions // enable extension of Swing classes


object GameGUI extends SimpleSwingApplication:
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)


  def top = new MainFrame:

    // Access to the application’s internal logic:
    val game = Game()
    val player = game.player
    this.game.setupGame()

    // Components:
    val locationInfo = new TextArea(7, 80):
      editable = false
      wordWrap = true
      lineWrap = true
    val turnOutput = new TextArea(7, 80):
      editable = false
      wordWrap = true
      lineWrap = true
    val input = new TextField(40):
      minimumSize = preferredSize
    this.listenTo(input.keys)


    // Events:

    this.reactions += {
      case keyEvent: KeyPressed =>
        if keyEvent.source == this.input && keyEvent.key == Key.Enter && !this.game.isOver then
          val command = this.input.text.trim
          if command.trim.toLowerCase == "quit" then this.playTurn(command)
          else if !this.nameSet then this.setName(command)
          else if !difficultySet then this.setDifficulty(command)
          else if welcomePhase != -1 then welcome()
          else if command.nonEmpty || pressEnter then
            this.playTurn(command)
          this.input.text = ""
    }

    // Layout:

    this.contents = new GridBagPanel:
      import scala.swing.GridBagPanel.Anchor.*
      import scala.swing.GridBagPanel.Fill
      layout += Label("Info:") -> Constraints(0, 0, 1, 1, 0, 1, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += Label("Command:")  -> Constraints(0, 1, 1, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += Label("Events:")   -> Constraints(0, 2, 1, 1, 0, 0, NorthWest.id, Fill.None.id, Insets(8, 5, 5, 5), 0, 0)
      layout += locationInfo       -> Constraints(1, 0, 1, 1, 1, 1, NorthWest.id, Fill.Both.id, Insets(5, 5, 5, 5), 0, 0)
      layout += input              -> Constraints(1, 1, 1, 1, 1, 0, NorthWest.id, Fill.None.id, Insets(5, 5, 5, 5), 0, 0)
      layout += turnOutput         -> Constraints(1, 2, 1, 1, 1, 1, SouthWest.id, Fill.Both.id, Insets(5, 5, 5, 5), 0, 0)

    // Menu:
    this.menuBar = new MenuBar:
      contents += new Menu("Program"):
        val quitAction = swing.Action("Quit")( dispose() )
        contents += MenuItem(quitAction)

    // Set up the GUI’s initial state:
    this.title = game.title
    this.location = Point(50, 50)
    this.minimumSize = Dimension(200, 200)
    this.pack()
    this.updateUpper("Enter your name: ")
    this.input.requestFocusInWindow()

    var welcomePhase = 0
    var nameSet = false
    var difficultySet = false
    var pressEnter = false

    def updateUpper(info: String) = this.locationInfo.text = info
    def updateLower(info: String) = this.turnOutput.text = info


    def updateInfo(info: String) =
      if !this.game.isOver then this.updateLower(info)
      else updateLower(info + "\n\n" + this.game.goodbyeMessage)
      val upper =
        if !this.player.isInCombat then this.player.location.fullDescription + s"\n\n\n${player.bagCondition} ${player.hpRemaining} / ${player.maxHP} HP.\nYou have ${player.silver} Silver Coins and ${this.player.gold} Goldcoins left.\n"
        else if enterCombat then this.player.currentCombat.welcomeCombat
        else combat.combatInfo
      updateUpper(upper)

    def setName(command: String) =
      val name = command.trim
      if name.nonEmpty then this.player.changeName(name)
      updateLower("Name set.")
      updateUpper("Please select difficulty:\n\n1 - Cheat | 2 - Normal | 3 - GOBLINDIEFAST")
      nameSet = true

    def setDifficulty(command: String) =
      val select = command.toIntOption.getOrElse(0)
      if select >= 1 && select <= 3 then
        this.player.setDifficulty(select)
        updateLower("Difficulty set: " + select + "\n\n\n[Press Enter]")
        updateUpper(this.game.title + "!!")
        difficultySet = true
      else updateLower("Invalid input. Choose difficulty:")

    def welcome() = welcomePhase match
      case 0 =>
        updateUpper("Welcome to Saving the Grove! You play as a goblin and you have just waken up to terrible news. The Grove's entire treasury has been robbed!\n" +
          "The robbers are are the infamous group: Crussadders. These ill wanting rabble-rousers have pillaged the world in search for an holy artifact. \nThe one my ancestor sealed inside the Gobbo Gauntlet.\n" +
          "With this artifact, the Crussadders are trying to summon a demonic being and possess the whole of earth.")
        updateLower("[Press Enter]")
        welcomePhase = 1
      case 1 =>
        updateUpper("You take it on yourself to figure this whole ordeal but don't worry you don't have to do it alone! \nYou'll face tough enemies, but with enough skill and luck you can do it!")
        updateLower("[Press Enter]")
        welcomePhase = 2
      case 2 =>
        updateUpper("The Crussadders have started their ritual already so be quick!. A curse is spreading around the world and ordinary creatures are being posessed.\n" +
          "The only way to keep the curse at bay is carrying silver metal! So if you don't want to succumb to the dark forces make sure to always carry it!")
        updateLower("[Press Enter]")
        welcomePhase = 3
      case 3 =>
        updateUpper("In short your \"HP\" is the amount of Silver coins you have. \nHP of your bag — where you carry your silver — gives an indication of how much silver you are losing every turn.\n" +
        "So keep your bag intact and If you're ever running low on silver you can buy it in change for gold from shops across the world!")
        updateLower("[Press Enter]")
        welcomePhase = 4
      case 4 =>
        updateUpper("One more thing. You can see the list of available commands by typing \"help\". \nAfter entering a command you get an action report telling you what happened.")
        updateLower("[Press Enter]")
        welcomePhase = 5
      case 5 =>
        updateInfo("")
        welcomePhase = -1



    def playTurn(command: String) = // "divider"
      if !this.player.hasQuit then
        if this.player.isBossRoom && bossPhase != -1 then bossBattle(command: String)
        else if this.player.isInCombat then this.runCombat(command) // if player started combat in previous turn plays combat minigame.
        else this.runNormalTurn(command)
        this.input.enabled = !this.game.isOver
      if this.player.hasQuit then this.dispose()



    var combat: Combat = null
    var enterCombat = true
    var timer: Long = 0
    var confirm = false

    def runCombat(command: String) =
      combat = this.player.currentCombat
      if enterCombat then this.game.combatPrimer()
      enterCombat = false
      val pTurn: String =
        if this.player.isImmobilized then
          combat.advanceTurn()
          "You are stunned."
        else if combat.playerIsAttacking then
          if !confirm then
            confirm = command == "y"
            if confirm then "Your combo is: " + combat.combo else combat.cancelAttack
          else
            confirm = false
            combat.attackPhase2(command.toUpperCase, timer)
        else
         val turn = game.combatPlayerTurn(command, combat)
         val attackYN = if combat.playerIsAttacking then "\n\nProceed to attack? [y/n]" else ""
         turn + attackYN
      val eTurn =
        if !combat.isEnemyTurn then ""
        else if !combat.combatIsOver then this.game.combatEnemyTurn(combat)
        else game.combatEnd(combat)
      updateInfo(pTurn + "\n\n" + eTurn)
      timer = System.currentTimeMillis()



    // turn logic
    def runNormalTurn(command: String) =
      enterCombat = true
      if !this.player.isDefeated then
        if !this.player.isBuying then
          val oldLocation = this.player.location
          val action = GGame.classes.Action(command)
          val outcomeReport = action.execute(this.player)
          val shopping = if this.player.isBuying then "How many?" else ""
          updateInfo(outcomeReport.getOrElse(s"""Unknown command: "$command". Try "help" if you're unsure what to do.""") + shopping + "\n\n" + this.game.runNormalEnd(oldLocation))
        else
          updateInfo(this.player.buyXMany(scala.math.max(0, command.toIntOption.getOrElse(0))))


    var bossPhase = 0
    def bossBattle(command: String) = bossPhase match
      case 0 =>
        updateInfo(s"""Unknown command: "$command". Try "help" if you're unsure what to do.""")
        pressEnter = true
        bossPhase = 1
      case 1 =>
        updateInfo("...\n\n\n\n[Press Enter]")
        bossPhase = 2
      case 2 =>
        updateInfo("LoOkInG fOr mE?\n\nThe Dark Emperor: So, you've arrived... I've heard a lot of rumors about you from my... servants.\nI don't understand what exactly was your plan but I DON'T CARE!\nIM GONNA ENJOY DESTROYING YOU!!\n\n\n[Press Enter]")
        bossPhase = 3
      case 3 =>
        pressEnter = false
        this.player.forceBossBattle()
        updateInfo("The Emperor approaches you.\n\n[You cant run away from this battle]")
        bossPhase = -1






  end top

  // Enable this code to work even under the -language:strictEquality compiler option:
  private given CanEqual[Component, Component] = CanEqual.derived
  private given CanEqual[Key.Value, Key.Value] = CanEqual.derived








end GameGUI





