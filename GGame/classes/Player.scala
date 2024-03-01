package GGame.classes

import GGame.Values.*
import GGame.classes.{Area, Armor, Clashers, Combat, Consumable, Equipment, Item, KeyItem, Offer, Shop, SilverOffer, Weapon}

import scala.math.*
import scala.util.Random
import scala.util.Random.*

class Player(startingArea: Area) extends Clashers("You", 30, 1, 0):

  private var currentLocation = startingArea           // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false                 // one-way flag
  private var combatSituation: Option[Combat] = None   // Stores the current combat situation.
  private var difficultyLevel = 1                      // Affects combat hardness

  def setDifficulty(set: Int) =
    this.difficultyLevel = set
    if difficultyLevel == 1 then home.addItems(Vector(godlyarmor, godlyweapon)) // cheat mode

  ////////////////
  //   Combat   //
  ////////////////

  // determines whether the player is in combat with an enemy
  def isInCombat: Boolean = this.combatSituation.isDefined

  // this method assumes it's only called when the player is in combat
  def currentCombat: Combat = this.combatSituation.head

  def exitCombat() = this.combatSituation = None

  // Called when player arrives at the final area of the game
  def forceBossBattle() = this.combatSituation = Some(Combat(this, emperor))

  def weapon: Option[Weapon] = this.playerWeapon

  /////////////////////////
  //       Inventory     //
  /////////////////////////

  // List of all the items in players inventory. Vector contains all items with the same names.
  // Items with same names are clones of the same item

  private val playerInventory = scala.collection.mutable.Map[String, Vector[Item]]()
  private var goldCoins = 10                        // the players money
  private var silverCoins = 25                      // the players "life", game over at 0

  private var playerWeapon: Option[Weapon] = None   // the players weapon slot
  private var playerArmor: Option[Armor] = None     // the players armor slot

  // finds an item from players inventory
  private def findItem(itemName: String): Option[Vector[Item]] = playerInventory.get(itemName)
  private def inventoryContains(itemName: String): Boolean = playerInventory.contains(itemName)

  // checks if player has clones of an item
  private def hasDuplicates(itemName: String): Boolean = findItem(itemName) match
    case None => false
    case Some(list) => list.size > 1

  // Returns the amount of silver/gold the player has.
  def silver: Int = this.silverCoins
  def gold = this.goldCoins



  // Despite the name, can also drop gold.
  def receiveGold(amount: Int): String =
    val gold1 = this.goldCoins
    this.goldCoins = max(0, this.goldCoins + amount)
    val howMany = this.goldCoins - gold1
    if howMany > 0 then s"\nYou found $howMany gold coins on the ground!"
    else if howMany < 0 then s"${howMany * -1} gold coins leaked from your bag."
    else ""

  //The player has a 10% chance of receiving ("finding") 3-8 gold coins each turn
  def randomGold: String =
    val randomizer = scala.util.Random
    val luck = randomizer.nextInt(10)
    val foundCoins = if luck == 0 then randomizer.nextInt(6) + 3 else 0
    this.receiveGold(foundCoins)


  private def dropSilver(howMany: Int): String =
    this.silverCoins = max(0, this.silverCoins - howMany)
    if howMany != 0 then s"$howMany silver coins leaked from your bag."
    else ""

  def dropCoins: String =
    this.condition match
      case 5 => receiveGold(0) + "\n" +  dropSilver(0)
      case 4 => receiveGold(0) + "\n" +  dropSilver(0)
      case 3 => receiveGold(-1) + "\n" +  dropSilver(1)
      case 2 => receiveGold(-2) + "\n" +  dropSilver(2)
      case 1 => receiveGold(-4) + "\n" +  dropSilver(4)
      case 0 => receiveGold(-6) + "\n" +  dropSilver(6)


  // adds an item to players inventory
  private def addToInventory(item: Item) =
    if inventoryContains(item.name) then
      playerInventory(item.name) = playerInventory(item.name) ++ Vector(item)
    else
      playerInventory += item.name -> Vector(item)

  // removes an item from players inventory
  // !!! does not check if item exists in inventory
  private def removeFromInventory(item: Item) =
    if hasDuplicates(item.name) then
      playerInventory(item.name) = playerInventory(item.name).drop(1)
    else  playerInventory -= item.name


//////////////////////
//     commands     //
//////////////////////


  // Drops an item and leaves it to a location
  def drop(itemName: String): String =
    findItem(itemName) match
      case None => "You don't have that!"
      case Some(item) =>
        removeFromInventory(item.head)
        this.location.addItem(item.head)
        s"You drop a $itemName."

  // Examine the area around you.
  def examine(name: String): String =
    findItem(name) match // first go through items
      case Some(item) =>
        s"You look closely at the ${item.head.name}." + s"\n${item.head.description}"
      case None =>
        if this.isInCombat then s"""Can't find "$name" to examine it.""" // blocks unwanted outcomes.
        else this.location.allNPC.get(name) match // then NPC:s
          case Some(npc) => s"This is ${npc.name}: \n${npc.description}"
          case None => this.location match
            case shop: Shop => shop.itemsOnSale.get(name) match // then Offers.
              case Some(item) => s"You look closely at the ${item.name}." + s"\n${item.description}"
              case None => s"""Can't find "$name" to examine it."""
            case other => s"""Can't find "$name" to examine it.""" // case closed ;)



  // Pick up an item from the world
  def get(itemName: String): String =
    if this.location.enemyNPC.nonEmpty then s"There is no $itemName here to pick up."
    else this.location.removeItem(itemName) match
      case None => s"There is no $itemName here to pick up."
      case Some(item) => addToInventory(item)
        s"You pick up the ${item.name}"

  // Move inside the world
  def goto(option: String): String = // Option as in some letter. nothing to do with scala Option[A]
    val oldArea = this.location
    this.location.neighborSelection.get(option) match
      case None => "That is not a valid option."
      case Some(area) =>
        this.currentLocation = area
        this.currentLocation.setNeighbor(oldArea) // Determines Area class' "wherePlayerCameFrom"
        "You went to: " + area.name + "."



  def quit: String =
    this.quitCommandGiven = true
    "Only ogres give up!"

  // Creates combat => Changes combatStatus => player.isInCombat = true => UI object runs combat.
  def battle(enemy: String): String =
    this.location.enemyNPC.get(enemy.capitalize) match
      case Some(enemy) =>
        this.combatSituation = Some(Combat(this, enemy))
        "You approach " + enemy.name + "."
      case None => "That enemy doesn't exist!"

  // Talk to NPC:s. NPC:s might give you an item.
  def talkto(npcName: String): String =
    this.location.friendlyNPC.get(npcName) match
      case None => "Who are you talking to? Ghosts?"
      case Some(npc) =>
        val line = npc.lineOrRandom
        npc.giveItem match
          case None => line
          case Some(item) => this.addToInventory(item)
            line + s"\n${npc.name} gave you an item: ${item.name}."

  private var buying = false
  private var currentOffer: Offer = null

  def isBuying = buying

  // Buy items from a shop.
  def buy(itemName: String): String = this.location match // Checks if player is in a shop
    case shop: Shop => shop.getOffer(itemName) match
      case None => "There is no such item available."
      case Some(offer) =>
        currentOffer = offer
        offer.item match
        case item: KeyItem =>
          shop.removeOffer(offer)
          buyXMany(1)
        case other =>
          buying = true
          ""
    case other => "You are not in a shop!"


  def buyXMany(howMany: Int): String =
    buying = false
    val price = currentOffer.price * howMany
    if price > this.goldCoins then s"You don't have enough gold coins for that." // Enough gold?
    else 
      this.goldCoins -= currentOffer.price * howMany // "Pay"
      currentOffer match
        case SilverOffer => // Singleton Object special case
          if howMany == 0 then "The merchant already got excited :C"
          else 
            this.silverCoins += howMany 
            s"You traded $howMany Silver Coins for $price Gold coins."
        case other =>
          (1 to howMany).foreach(ignore => addToInventory(currentOffer.item)) // Adds "howMany" of that item to inventory.
          if howMany != 0 then s"You bought $howMany x ${currentOffer.name} for $price gold coins!"
          else "The merchant already got excited :C"
    


  private def advanceCombatIfInCombat() = if this.isInCombat then this.currentCombat.advanceTurn()
  
  def use(itemName: String): String = findItem(itemName) match // determines if the player has that item
      case None => "You don't have that!"
      case Some(item) => item.head match
        case item: Consumable =>
          this.advanceCombatIfInCombat()
          this.useConsumable(item)
        case item: Equipment =>
          this.advanceCombatIfInCombat()
          this.wearEquipment(item)
        case item: KeyItem => 
          if !this.isInCombat then this.useKeyItem(item)
          else s"You can't use ${item.name} here."
        case other => ""

  private def useConsumable(item: Consumable): String =
    if !item.combatOnly then // If item not exclusive to combat, can always be used
      this.removeFromInventory(item) // Consumables are non reusable
      item.effect.applyEffect(this) // Effect class' method makes the changes to the player or the enemy
    else if this.isInCombat then
      this.removeFromInventory(item)
      if item.targetSelf then item.effect.applyEffect(this)
      else item.effect.applyEffect(currentCombat.enemy) 
    else "You can't use this item while not in combat."
      


  private def useKeyItem(item: KeyItem): String =
    this.location.unlockAreaWithItem(item) match
      case None => s"Can't use $item here."
      case Some(string) =>
        if !item.isReusable then this.removeFromInventory(item)
        string


  // adds equipment to weapon/armor slot and removes them from players inventory.
  // if player previously had equipment on, adds that equipment back to players inventory
  private def wearEquipment(equipment: Equipment): String =
    equipment match
      case equipment: Armor =>
        if playerArmor.isDefined then addToInventory(playerArmor.get)
        playerArmor = Some(equipment)
        removeFromInventory(equipment)
      case equipment: Weapon =>
        if playerWeapon.isDefined then addToInventory(playerWeapon.get)
        playerWeapon = Some(equipment)
        removeFromInventory(equipment)
    s"You equipped ${equipment.name}."

  def inventory: String =
    if playerInventory.isEmpty then "You are empty-handed."
    else "You are carrying:\n" + playerInventory.map(key => key._1 + " x " + key._2.size.toString + "\n").mkString

  //////////////////////
  //       Stats      //
  //////////////////////


  // determines how much damage player inflicts in combat.
  override def damage: Int =
    val weaponDamage = this.weapon match
      case Some(weapon) => weapon.attack
      case None => 0
    super.damage + weaponDamage

  // determines how much incoming damage is reduced.
  override def defence: Int =
    val armorDefence = this.playerArmor match
      case Some(armor) => armor.defence
      case None => 0
    super.defence + armorDefence


  def hasQuit: Boolean = this.quitCommandGiven

  def location: Area = this.currentLocation

  // This formula outputs always one of these: 0, 1, 2, 3, 4, 5.
  private def condition: Int =
    ((this.hpRemaining - 2) / ((this.maxHP - 2) / 4.0) + 1).toInt   // toInt for floor.

  def bagCondition: String =
    this.condition match
      case 5 => "Your bag is in mint condition."   // 100% hp
      case 4 => "Your bag is in good condition."   // 76% - 99% hp
      case 3 => "Your bag has a few holes. Some coins are leaking out."   // 51% - 75% hp
      case 2 => "Your bag is in rough shape. Coins are steadily pouring from all the holes."   // 27% - 50% hp
      case 1 => "There are massive holes in your bag. You should patch them as soon as possible."   // 1% - 26% hp
      case 0 => "Your bag is totally in pieces. Patch your bag up immediately!"   // 0% hp


  def difficulty: Int = this.difficultyLevel

  override def isDefeated: Boolean = silver <= 0

  override def toString: String = "Now at: " + this.location.name

  def playerInfo: String = this.bagCondition + " You have " + this.silver +" silver coins and " + this.goldCoins + " gold coins."

  def isBossRoom: Boolean = this.location == circleOfEternity

end Player

