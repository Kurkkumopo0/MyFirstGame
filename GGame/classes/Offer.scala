package GGame.classes


//An Offer object contains a price and an item. Used in the Shop subclass of Area.
class Offer(val price: Int, val item: Item):

  val name = item.name
  
  override def toString = this.name + ", " + this.price + " gold coins"

end Offer



object SilverOffer extends Offer(4, SilverCoins):

  override val name = "Silver coins"

  override def toString = this.name + ", " + this.price + " gold coins"
  
end SilverOffer
  


