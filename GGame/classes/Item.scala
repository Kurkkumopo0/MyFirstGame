package GGame.classes



// player collects items and these items can be used
sealed trait Item(val name: String, val description: String):

  override def toString: String = this.name

end Item


// Single use. Makes changes to the player or the enemy (see Effect, Clashers).
// If consumable is not combat only, it should only target the player.
class Consumable(name: String, description: String, val effect: Effect, val combatOnly: Boolean, val targetSelf: Boolean) extends Item(name, description):
  
end Consumable


// KeyItems are items that are relevant to advancing in the game.
class KeyItem(name: String, description: String, reusable: Boolean) extends Item(name, description):

  def isReusable = reusable   // determines if the item should be removed from player after using it successfully.

end KeyItem


// Consists of armors and weapons.
sealed trait Equipment extends Item:

end Equipment


// Type of equipment. Different weapons do different amount of damage and have different action commands.
class Weapon(name: String, description: String, val attack: Int, val attackMove: String, val skillRating: Int) extends Equipment with Item(name, description):

  def comboLength: Int = skillRating
  
end Weapon


// Type of equipment. Different armors have different defence values.
class Armor(name: String, description: String, val defence: Int) extends Equipment with Item(name, description):
  
end Armor


object SilverCoins extends Item("","") // for Offer

end SilverCoins  


