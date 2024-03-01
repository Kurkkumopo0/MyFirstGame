package GGame.classes

import scala.collection.mutable.Map
import scala.util.Random
import scala.util.Random.*

//An NPC object represents a character in the game. Can be hostile or friendly.
trait NPC(val name: String, val description: String, line: Option[String]):

  def isEnemy: Boolean // All NPC can determine if they're hostile.

  def lineOrRandom: String // All NPCs have lines. Some don't have unique ones, so they'll say a random generic line.

  override def toString = if this.isEnemy then "Enemy: " + name else "Friendly: " + name
  
end NPC

// A friendly character. Can drop items and give useful information.
class FriendlyNPC(name: String, description: String, line: Option[String]) extends NPC(name, description, line):

  // Holds the Item the NPC gifts the player when talked to
  private var npcsItem: Option[Item] = None
  
  // All NPCs have lines. Some don't have unique ones, so they'll say a random generic line.
  private val randomLines = Vector("dumdum dumdum HAPPY DAYS!", "My socks are wet...", "What a beautiful dark day!")

  // Adds an item that the NPC can give to the player
  def npcAddItem(item: Item) = npcsItem = Some(item) 

  // Called when the NPC gives the item it holds. Removes the item from the NPC.
  def giveItem: Option[Item] =
    val item = this.npcsItem
    this.npcsItem = None
    item


  def isEnemy = false // no friend is an enemy :)

  // Gives a random line from the pool if parameter line is not defined
  def lineOrRandom: String =
    val lineNumber = Random.nextInt(randomLines.size)
    name + ": " + line.getOrElse(randomLines.apply(lineNumber))

end FriendlyNPC

//All the hostile characters that you can battle. Has HP, attack and defence values for combat.
class EnemyNPC(name: String, description: String, line: Option[String], hp: Int, attackPOW: Int, defencePOW: Int) extends NPC(name, description, line) with Clashers(name, hp, attackPOW, defencePOW):

  //All NPCs have lines. Some don't have unique ones, so they'll say a random generic line.
  private val randomLines = Vector("This won't end well for you my friend.", "Who dares to challenge me?!", s"I, The mighty $name, am going to end you!")

  //Gives a random line from the pool if parameter line is not defined
  def lineOrRandom: String =
    val lineNumber = Random.nextInt(randomLines.size)
    name + ": " + line.getOrElse(randomLines.apply(lineNumber))

  def isEnemy = true //all enemies are enemies :(



end EnemyNPC


