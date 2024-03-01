package GGame.classes

class Action(input: String):

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim.capitalize

  //Executes a command in a normal turn. Returns the result as a String wrapped in an Option.
  def execute(actor: Player): Option[String] = this.verb match
    case "go"        => Some(actor.goto(this.modifiers))
    case "quit"      => Some(actor.quit)
    case "get"       => Some(actor.get(this.modifiers))
    case "drop"      => Some(actor.drop(this.modifiers))
    case "inventory" => Some(actor.inventory)
    case "examine"   => Some(actor.examine(this.modifiers))
    case "use"       => Some(actor.use(this.modifiers))
    case "battle"    => Some(actor.battle(this.modifiers))
    case "talkto"    => Some(actor.talkto(this.modifiers))
    case "buy"       => Some(actor.buy(this.modifiers))
    case "help"      => Some(this.help(this.modifiers))
    case other       => None

  //Executes a command in a combat turn. Returns the result as a String wrapped in an Option.
  def combatExecute(combat: Combat): Option[String] =
    this.verb match
      case "help"      => Some(this.combatHelp(this.modifiers))
      case "run"       => Some(combat.flee)
      case "attack"    => Some(combat.attack)
      case "identify"  => Some(combat.identify)
      case "inventory" => Some(combat.player.inventory)
      case "examine"   => Some(combat.player.examine(this.modifiers))
      case "quit"      => Some(combat.quit)
      case "use"       => Some(combat.player.use(this.modifiers))
      case other => None


  
  
  
  //The help command. Returns a string explaining the command or all available commands.
  private def help(command: String) = command match
    case "Go" => "You have a list of available exits starting from A. Example: \"go a\""
    case "Quit" => "Quit the game! No confirmations asked."
    case "Get" => "If there is an Item in your area you can pick it up by typing \"get [item name]\"."
    case "Drop" => "Drop an Item from your inventory. Example: \"drop [item name]\""
    case "Inventory" => "Get a list of all your items"
    case "Examine" => "Examine everything in your area. Your items, shop offers, friendlies and enemies. Example \"Examine [x name]\"."
    case "Use" => "Use an Item inside your inventory. Very Important for the game! Example: \"use [item name]\"."
    case "Battle" => "Challenge an Enemy that is blocking your way. Example: \"Battle [enemy name]\"."
    case "Talkto" => "Talk to friendlies. Some NPCs might share valuable information. Example: \"talkto [npc name]\"."
    case "Buy" => "Buy from a shop. Example: \"buy [item name]\"."
    case other => "\nAvailable commands: \ninventory \nexamine \nget \ndrop \nuse \ntalkto \ngo \nbattle \nquit\n\nFor more information type \"help [command name]\"."   
      
  
      
  //The help command but for combat. Returns a string explaining the command or all available commands.
  private def combatHelp(command: String) = command match
    case "Quit" => "Quit the game! No confirmations asked."
    case "Inventory" => "Get a list of all your items. Doesn't count a turn."
    case "Examine" => "Examine one of your items. Doesn't count a turn. Example \"Examine [item name]\"."
    case "Use" => "Use an Item inside your inventory. Example: \"use [item name]\"."
    case "Run" => "Flee the battle. Based on chance. Counts a turn."
    case "Attack" => "Attack the enemy! Follow your action command and land a full powered strike!"
    case other => "\nAvailable commands: \nattack \nuse \ninventory \nidentify \nrun \nexamine \nquit\n\nFor more information type \"help [command name]\"."



  override def toString = s"$verb (modifiers: $modifiers)"



end Action

