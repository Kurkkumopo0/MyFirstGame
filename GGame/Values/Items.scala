package GGame.Values

import GGame.Values.*
import GGame.classes.{Armor, Consumable, Effect, KeyItem, Weapon}
//Armor(name: String, description: String, val defence: Int)
//Weapon(name: String, description: String, val attack: Int, val attackMove: String, val skillRating: Int)
//KeyItem(name: String, description: String, reusable: Boolean, val usage: Vector[Area])
//Consumable(name: String, description: String, val effect: Effect, val combatOnly: Boolean, val targetSelf: Boolean)


// Consumables:
// keyword stun:
val voltcake = Consumable("Volt cake", "Consumable\nShockingly good delight. Unfortunately stuns the user for 2 turns.", stunnerS, true, true)
val boltstaff = Consumable("Boltstaff", "Consumable\nA magical staff that shoots lightning bolts at the enemy. \nStuns target for 2 turns.", stunnerS, true, false)
val vinespell = Consumable("Vine spell", "Consumable\nThis spell makes vines grow from the ground and stuns your enemy for 3 turns.", stunnerM, true, false)
val carbattery = Consumable("Car battery", "Consumable\nA car battery? How did it even get here? Extremely rare.\nStuns your enemy for 4 turns.", stunnerL, true, false)

// keyword heal

val stitches = Consumable("Stitches", "Consumable\nA few stitches to sew few holes in your bag.\nHeals 10hp", healM, false, true)
val sewingkit = Consumable("Sewing kit", "Consumable\nWith these I can completely repair my bag. Reminds me of my uni -years.", repair, false, true)

// keyword damage

val slingshot = Consumable("Slingshot", "Consumable\nLay a rock shower upon your enemies.\nDamages your enemy by 3hp and bypasses armor", damageS, true, false)
val earthquake = Consumable("Earthquake", "Consumable\nThis spell makes the earth shake.\nDamages your enemy by 6hp and bypasses armor", damageM, true, false)
val killdozer = Consumable("Killdozer", "Consumable\nLocal authority really got in your head. Run over your enemy. Extremely rare.\nDeals 20 damage and bypasses armor.", damageL, true, false)


// keyword strength+

val porridge = Consumable("Porridge", "Consumable\nDelicious porridge! Just like mama's.\nIncreases damage by 1 for 3 turns if in combat.", strengthS, false, true)
val spinach = Consumable("Spinach", "Consumable\nSpinach makes you string... STRONG.\nIncreases damage by 2 for 2 turns if in combat.", strengthM, false, true)
val pervitin = Consumable("Pervitin", "Consumable\nI'm not sure if I should use that. Extremely rare.\nIncreases damage by 10 for 3 turns if in combat.", strengthL, false, true)
val powerpotion = Consumable("Power potion", "Consumable\nIcky tasting potion but permanently boosts your damage by 1.", permaPow, false, true)

// keyword def+

val woodshield = Consumable("Wood shield", "Consumable\nA fragile shield that blocks some damage.\nIncreases defence by 2 for 3 turns if in combat.", defenceS, false, true)
val protectingclaw = Consumable("Protecting claw", "Consumable\nA totem that increases your defence by 3 for 3 turns if in combat.", defenceM, false, true)
val morphine = Consumable("Morphine", "Consumable\nEnemies can't deal damage if i can't feel the pain. Right? Extremely rare.\nIncreases defence by 10 for 3 turns.", defenceL, false, true)
val holystone = Consumable("Holy stone", "Consumable\nLooks just like an ordinary rock would but has magical properties.\nPermanently increases defence by 1.", permaDef, false, true)

// keyword strength-

val poisonarrow = Consumable("Poison arrow", "Consumable\nDo not touch the tip! It's poisoned with Surströmming.\nDecreases enemy's damage by 2 for 3 turns.", weakS, true, false)
val silveramulet = Consumable("Silver amulet", "Consumable\nThis silver amulet weakens your demonic enemies.\nDecreases enemy's damage by 3 for 3 turns.", weakM, true, false)
val slimysock = Consumable("Slimy sock", "Consumable\nIt moved! Something is growing inside it! Extremely rare.\nIts stench decreases enemy's damage by 10 for 3 turns.", weakL, true, false)

// keyword defence-

val weakacid = Consumable("Weak acid", "Consumable\nThis weak acid will dissolve a layer of your enemy's armor.\nDecreases enemy's defence by 2 for 3 turns.", defMinusS, true, false)
val fragiler = Consumable("Fragiler", "Consumable\nThis spell makes your enemy's armor as fragile as gobnagers self esteem.\nDecreases enemy's defence by 3 for 3 turns.", defMinusS, true, false)
val sulfuricacid = Consumable("Sulfuric acid", "Consumable\nSurprise sulfuric acid! Extremely rare. \nCompletely dissolves enemy's armor.", defMinusL, true, false)

// misc

val lortsymeal = Consumable("Lortsymeal", "Consumable\nLörtsys are epic. Though a bit on the heavier side.", doNothing, false, true)



//Armor(name: String, description: String, val defence: Int)

val trainingwear = Armor("Training wear", "Armor\nA suit made out of wooden plates. Made for training at the gobbo-dojo. \nIncreases defence by 1.", 1)
val crussaddervest = Armor("Crussadder vest", "Armor\nA vest left behind by the Crussadders. Seems old but the smell is still there. Icky! \nIncreases defence by 2.", 2)
val chainmailarmor = Armor("Chainmail armor", "Armor\nNow this is epic. Younger me always dreamt of becoming a knight. \nIncreases defence by 3.", 3)
val steelshield = Armor("Steel shield", "Armor\nWith this i'm going to be properly protected. Seems very sturdy. \nIncreases defence by 4.", 4)
val platecarrier = Armor("M17 platecarrier", "Armor\nIs this an M17 plate carrier from the Finnish military? Wow this is the real stuff. Well... it's not like i'm going to get shot at but... \nIncreases defence by 6.", 6)

val godlyarmor = Armor("godly armor", "Armor\nThis armor should not exist. Yet it does! \nYou cannot receive damage.", 999) // cheat(trivial difficulty)

//Weapon(name: String, description: String, val attack: Int, val attackMove: String, val skillRating: Int)
//strike
val trainingsword = Weapon("Training sword", "Weapon\nA wooden sword made for training at the gobbo-dojo. Easy to use but deals little damage.\nAttack move: Strike, repeat the combo that is shown on screen.\nAttack power: 1", 1, "strike", 1)
val ironrapier = Weapon("Iron rapier", "Weapon\nRapier made out of iron. Way more sturdy than a wooden one but harder to yield.\nAttack move: Strike, repeat the combo that is shown on screen.\nAttack power: 3", 3, "strike", 4)
val nunchucks = Weapon("Nunchucks", "Weapon\nThese are very hard to use but I feel like a ninja while using these.\nAttack move: Strike, repeat the combo that is shown on screen.\nAttack power: 5", 6, "strike", 5)
val obsidianlongsword = Weapon("Obsidian longsword", "Weapon\nThis thing is HEAVY. The obsidian edges are sharp as shark teeth! I'm not worthy!\nAttack move: Strike, repeat the combo that is shown on screen.\nAttack power: 8", 8, "strike", 9)

//rattle
val spear = Weapon("Spear", "Weapon\nA spear! Not so sharp but wouldn't like to get impaled by it.\nAttack move: Rattle, repeatedly press Enter.\nAttack power: 2", 2, "rattle", 3)
val bone = Weapon("Bone", "Weapon\nSurprisingly dense. This thing packs a punch... and it is pretty easy to use!\nAttack move: Rattle, repeatedly press Enter.\nAttack power: 4", 4, "rattle", 3)

val godlyweapon = Weapon("Godly weapon", "Weapon\nThis weapon should not exist. Yet it does! \nOneshot every enemy.", 999, "strike", 1) // cheat(trivial difficulty)



//KeyItem(name: String, description: String, reusable: Boolean])
val icepick = KeyItem("Icepick", "Key Item\nAn ice pick used for mountain climbing. Could be useful.", true) // frozenCanyon, descentToSummit
val beaverflute = KeyItem("Beaver flute", "Key Item\nA beaver flute, formerly possessed by a yeti. Attracts beavers, who are known for building dams and having thick fur.", false) //moonfallRiver
val snowshoes = KeyItem("Snowshoes", "Key Item\nSnowshoes can be used to walk on snow without falling into it. Could be useful.", true) //snowySummits, icyPath
val umbrella = KeyItem("Umbrella", "Key Item\nA very sturdy umbrella made out of steel. This can take any amount of water.",true) //heavensFalls


val dojokey = KeyItem("Dojo key", "Key Item\n", false) // dojo
val map = KeyItem("Map", "KeyItem\nWith this map I can find my way to Toxic Marshlands! The world is bigger than I thought.\nHope I can read it right.", false)
val axe = KeyItem("Axe", "KeyItem\nWhat a gnarly looking rustic axe! Chopping trees or bushes is no problemo!", true)

val shovel = KeyItem("Shovel", "Keyitem\nA shovel... a bit rusty maybe... hope it won't break if I use it!", true)
val plushie = KeyItem("Plushie", "A soft plushie. Belongs to that old merchant at Eternum Forest", false)

val silverKey = KeyItem("Silver key", "A Silver key! Wonder where this goes", false)
val goldenKey = KeyItem("Golden key", "A golden key! Wonder where this goes", false)




















