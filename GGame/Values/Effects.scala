package GGame.Values

import GGame.classes.Effect

//Effect(val effectName: String, howMuch: Option[Int], howLong: Option[Int])
// stn, rep, pow, def, heal, dmg

val doNothing = Effect("", None, None)

// buffs
val strengthS = Effect("pow", Some(1), Some(3))
val strengthM = Effect("pow", Some(2), Some(2))
val strengthL = Effect("pow", Some(10), Some(3))
val permaPow = Effect("pow", Some(1), None)

val defenceS = Effect("def", Some(2), Some(3))
val defenceM = Effect("def", Some(3), Some(3))
val defenceL = Effect("def", Some(10), Some(3))
val permaDef = Effect("def", Some(1), None)

val repair = Effect("rep", None, None)
val healS = Effect("heal", Some(5), None)
val healM = Effect("dmg", Some(10), None)
val healL = Effect("dmg", Some(30), None)


// deBuffs:
val stunnerS = Effect("stn", None, Some(2))
val stunnerM = Effect("stn", None, Some(3))
val stunnerL = Effect("stn", None, Some(6))

val weakS = Effect("pow", Some(-2), Some(3))
val weakM = Effect("pow", Some(-3), Some(3))
val weakL = Effect("pow", Some(-10), Some(3))

val defMinusS = Effect("def", Some(-2), Some(3))
val defMinusM = Effect("def", Some(-3), Some(3))
val defMinusL = Effect("def", Some(-999), None)

val damageS = Effect("dmg", Some(-3), None)
val damageM = Effect("dmg", Some(-6), None)
val damageL = Effect("dmg", Some(-20), None)
