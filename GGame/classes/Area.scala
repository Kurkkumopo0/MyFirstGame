package GGame.classes

import GGame.*

import scala.collection.mutable
import scala.math.*


//One area of the game - mostly used as a main area. Has no predetermined neighbors.
class Area(var name: String, var description: String):

  //Most-recent holder. Stores the area the player was in before moving to current area.
  private var wherePlayerCameFrom: Option[(String, Area)] = None

  //Holds the neighbors of the area that are locked with a key item.
  private val lockedNeighbors = mutable.Map[KeyItem, Vector[Area]]()

  //One-way flag telling whether or not locked areas have been unlocked yet
  private var locked = true

  //True neighbors. Available only if all enemies have been defeated in the area.
  private val trueNeighbors = mutable.Map[String, Area]()

  //Holds the items that can be picked up by the player.
  private val items = mutable.Map[String, Vector[Item]]()

  //Holds the NPCs currently alive in the area.
  private val npcs = mutable.Map[String, NPC]()

  //Determines if area contains this item or not.
  private def areaContains(itemName: String): Boolean = allItems.contains(itemName)

  //Retuns the map of items in the area.
  def allItems = this.items

  //This method returns the currently available neighbors. Logic: If there are alive enemies, only the place the player came from.
  //Otherwise, all the available exist that are not currently locked.
  def neighbors: mutable.Map[String, Area] =
    val cameFrom = wherePlayerCameFrom match
      case Some(pair) => mutable.Map(pair)
      case None => mutable.Map()
    if this.enemyNPC.isEmpty then this.trueNeighbors
    else cameFrom

  //This method sorts the neigboring areas by name length and assigns a letter to them.
  //Reduces the amount of letters needing to be typed.
  def neighborSelection: Map[String, Area] =
    val alphabet = ('A' to 'Z').map(_.toString)
    val newMap = mutable.Map[String, Area]()
    val sorted = this.neighbors.values.toVector.sortBy(_.name.length * -1)
    val limit = min(alphabet.size - 1, this.neighbors.size - 1)
    (0 to limit).foreach(number => newMap += alphabet(number) -> sorted(number))
    newMap.toMap


  def setNeighbor(neighbor: Area) = // Special case. Only used by player class. Sets where player came from.
    this.wherePlayerCameFrom = Some(neighbor.name -> neighbor)

  def setTrueNeighbor(exit: Area) = this.trueNeighbors += exit.name -> exit //Sets an unlocked neighbor

  def setTrueNeighbors(exits: Vector[Area]) = exits.foreach(area => this.setTrueNeighbor(area)) //Sets multiple unlocked neighbors

  // One key item can reveal multiple locked areas. Each key corresponds to a list of locked areas.
  // lockedAreas is Vector parameter so that a single keyItem can reveal multiple areas.
  def setLockedNeighbor(lockedAreas: Vector[Area], keyItem: KeyItem): Unit = this.lockedNeighbors += keyItem -> lockedAreas

  // Unlocks a hidden area. Unlocks it both ways.
  def unlockAreaWithItem(keyItem: KeyItem): Option[String] =
    this.lockedNeighbors.get(keyItem) match               // Checks is there list of areas corresponding to that keyItem
      case None => None
      case Some(areas) =>
        val unlocks = mutable.Buffer[String]()            // Collects all unlocked area names
        for area <- areas do
          if !this.trueNeighbors.contains(area.name) then // Checks if these areas were revealed previously. Stops infinite recursion.
            this.trueNeighbors += area.name -> area       // If not, adds that area to trueNeighbors
            area.unlockAreaWithItem(keyItem)              // This makes the path open both ways. Relevant for some cases.
            unlocks += area.name                          // Also counts that area to unlocks
          end if
        if unlocks.nonEmpty then
          this.locked = false                             // In current implementation we assume that one area has max 1 locked neighbors. No technical limitation though.
          Some("You unlocked: " + unlocks.mkString(", ") + "!")
        else None

  //Checks if the locked areas are currently locked.
  def isBlocked: Boolean = locked

  //Returns a Map of all NPCs alive in the area.
  def allNPC = this.npcs

  //Returns a Map of all alive enemies in the area.
  def enemyNPC: Map[String, EnemyNPC] =
    val enemies = mutable.Map[String, EnemyNPC]()
    for npc <- allNPC.values.toVector do
      npc match
        case npc: EnemyNPC => enemies += npc.name -> npc
        case other =>
    enemies.toMap

  //Returns a Map of all alive friendlies in the area.
  def friendlyNPC: Map[String, FriendlyNPC] =
    val friendlies = mutable.Map[String, FriendlyNPC]()
    for npc <- allNPC.values.toVector do
      npc match
        case npc: FriendlyNPC => friendlies += npc.name -> npc
        case other =>
    friendlies.toMap


  // adds item to this area
  def addItem(item: Item): Unit =
    if this.areaContains(item.name) then
      items(item.name) = items(item.name) ++ Vector(item)
    else items += item.name -> Vector(item)

  // adds multiple items to this area
  def addItems(newItems: Vector[Item]): Unit = newItems.foreach(item => this.addItem(item))

  // removes an item from this area.
  def removeItem(itemName: String): Option[Item] =
    items.get(itemName) match
      case None => None
      case Some(item) =>
        if item.size > 1 then
          items(itemName) = items(itemName).drop(1)
        else items -= itemName
        Some(item.head)


  // Adds an NPC to the area. Assumes that the npc is viable and is not a duplicate. No duplicates allowed.
  def addNPC(newNPC: NPC) = this.npcs += newNPC.name -> newNPC

  //Same, but multiple NPCs
  def addNPCs(npcVector: Vector[NPC]) = npcVector.foreach(npc => this.addNPC(npc))

  // Removes an NPC from the area. Returns the NPC wrapped in an option.
  def removeNPC(npc: NPC): Option[NPC] = this.npcs.remove(npc.name)

  //Returns the area info the player sees at the start of each turn.
  def areaInfo: String =
    val item = if this.allItems.nonEmpty && this.enemyNPC.isEmpty then "\n\nYou see here: " + allItems.values.flatten.mkString(", ") else ""
    val NPCs =
      if this.allNPC.isEmpty then ""
      else
        val enemy: String = if enemyNPC.nonEmpty then "\nEnemies block yor way. Here lurks: " + enemyNPC.keys.mkString(", ") else ""
        val friendly: String = if friendlyNPC.nonEmpty then "\nFriendlies fiddle here: " + friendlyNPC.keys.mkString(", ") else ""
        val underlines = if friendly.nonEmpty then "\n" + "-" * friendly.length else "\n" + "-" * enemy.length
        "\n\n" + enemy + friendly + underlines
    item + NPCs

  //Returns all available exits in a String format
  def exitlist: String =
    val sorted = this.neighborSelection.toVector.sortBy(_._1).map(_ + ") " + _.name).mkString("\n")
    if this.enemyNPC.isEmpty then "\n\nExits available: \n" + sorted
    else "\n\nExits available: \nA) Go back" //Special case when there are enemies in the area. Then the player can only go back or fight.

  //The full String shown to player at the beginning of a normal turn.
  def fullDescription: String =
    this.description + this.areaInfo + this.exitlist


  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)

end Area

//An area "inside" another area. Has a predetermined neighbor, the area it is in. Can be chained to generate neighboring areas easier.
class SubArea(subName: String, description: String, val partOf: Area) extends Area(subName, description):

  //Sets the subarea to neighbor the area it's a part of and vice versa.
  this.setNeighborships()

  //Returns the name of the main area this subarea belongs to.
  def mainAreaName: String =
    this.partOf match
      case subArea: SubArea => subArea.mainAreaName
      case lockedSubArea: LockedSubArea => lockedSubArea.mainAreaName
      case area: Area => partOf.name

  //Subarea names contain the main area they are a part of, even if chained.
  this.name = this.mainAreaName + " - " + this.subName

  //Sets the subarea to neighbor the area it's a part of and vice versa.
  private def setNeighborships() =
    partOf.setTrueNeighbors(Vector(this))
    this.setTrueNeighbors(Vector(partOf))


end SubArea






//An area where the player can buy items.
class Shop(name: String, description: String, partOf: Area) extends SubArea(name, description, partOf):



  //Holds the Offers the shop sells.
  private val selection = mutable.Map[String, Offer](SilverOffer.name -> SilverOffer)

  //Returns a Map of Items on sale in the Shop. Class Offer contains a price and an item.
  def itemsOnSale = this.selection.values.map(offer => offer.item.name -> offer.item).toMap

  //These methods are used to change the selection of the shop.
  def addOffers(offers: Vector[Offer]): Unit = offers.foreach(off => this.addOffer(off))
  def addOffer(offer: Offer): Unit = selection += offer.name -> offer
  def removeOffer(offer: Offer): Unit = selection -= offer.name

  //Returns an offer wrapped in an Option when called with the Offers name.
  def getOffer(offerName: String): Option[Offer] = selection.get(offerName)

  //Adds the shops offers and tells the player that they're currently in a shop to the area info.
  override def fullDescription: String =
    val offers = this.selection.values.mkString("\n")
    this.description + super.areaInfo + "\n\nThere's a merchant here! Maybe buy something?\n" + offers + super.exitlist

end Shop





// Typically an area where keyItems are used. Using keyItem changes the fullDescription.
class ChokePoint(name: String, description: String, altDescr: Option[String]) extends Area(name, description):

  override def fullDescription: String = // Changes the description.
    val thisDescription = if this.altDescr.isDefined && !this.isBlocked then altDescr.head else description
    thisDescription + super.areaInfo + super.exitlist

end ChokePoint



//A subarea that is locked with a key item. Same methods, only locks itself both ways with a key item.
class LockedSubArea(subName: String, description: String, val partOf: Area, keyItem: KeyItem) extends Area(subName, description):

  this.setNeighborships()

  def mainAreaName: String =
    this.partOf match
      case subArea: SubArea => subArea.mainAreaName
      case lockedSubArea: LockedSubArea => lockedSubArea.mainAreaName
      case area: Area => partOf.name

  this.name = this.mainAreaName + " - " + this.subName

  private def setNeighborships() =
    partOf.setLockedNeighbor(Vector(this), keyItem)
    this.setLockedNeighbor(Vector(partOf), keyItem)

end LockedSubArea

