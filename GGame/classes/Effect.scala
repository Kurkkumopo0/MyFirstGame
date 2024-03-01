package GGame.classes

import GGame.classes.Clashers

// Effects change the state of some entity. 
// For "effects" of Effect see trait Clashers.
class Effect(val effectName: String, howMuch: Option[Int], howLong: Option[Int]):

  def applyEffect(entity: Clashers): String =
    effectName match
      case "rep"    => entity.repair(); "Your moneybag is now repaired"    // always called for the player
      case "pow"    => entity.changeStrength(howMuch.getOrElse(0), howLong)
      case "def"    => entity.changeDefence(howMuch.getOrElse(0), howLong)
      case "stn"    => entity.stun(howLong)
      case "dmg"    => entity.changeHP(howMuch.getOrElse(0))
      case other    => ""

end Effect

  

      