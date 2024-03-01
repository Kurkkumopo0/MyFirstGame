package GGame.Values

import GGame.classes.{Area, ChokePoint, LockedSubArea, Shop, SubArea}

// Goblin Grove

val dwellings = Area("Gobbo Dwellings","Gobbo dwellings are sorta rough neighborhood. Nevertheless me and my pals live here so its close to my heart.\nI should go say hi to Broglin.")
val home = SubArea("Sweet Home","This is my home. A sweet hut in the Gobbo Dwellings -district. What?! My fridge is empty! I should really GO out shopping.", dwellings)
val neighbor = SubArea("Broglins shack","This is my brother's home. AH-CHOO, for the love of... [Its such a mess here! How can he live here?]", dwellings)


val gobboSuburbs = Area("Gobbo suburbs","Nothing much going on here. A local Dojo is running nearby.")
val suToDo = SubArea("Path to the Dojo","an Overgrown trail between the Gobbo suburbs and the dojo", gobboSuburbs)
val suToCe = SubArea("Path to the Goblin Grove Centrum", "a Path between the Gobbo suburbs and The Goblin Grove centrum", gobboSuburbs)

val dojoE = ChokePoint("Dojo entrance", "What a divine landscape. The door to the Dojo seems to be locked. I should TALKTO the Dojo master, shouldn't I?", Some("What a divine landscape."))
val doToSu = SubArea("Path to the Gobbo suburbs","an Overgrown trail between the Gobbo suburbs and the dojo", dojoE)


val dojo = Area("Dojo","So this is what the dojo looks like inside. So... Lets BATTLE and spar a little! Maybe I can get some rewards?")
val dTreasure = SubArea("Dojo Treasure Room","Wow! I should really GET this new stuff.", dojo)

val centrum = Area("The Goblin Grove centrum", "This is the Center of our home Grove. Almost as busy as the central market!\nThere are the gates! Now I can really start my adventure.")
val ceToSu = SubArea("Path to the Gobbo suburbs","a Path between the Gobbo suburbs and The Goblin Grove centrum", centrum)

val market = Area("Plaza square", "Mmmmm... All those delicious smells... I wish I was hungry.")
val gobboShop = Shop("Grablin's Shop", "This is Grablins shop. All sorts of goods here!", market)
val grill = Shop("Lörtsy Grill", "Wow this legendary grill is again in town! I love it!", market)

val kampus = Area("Gobbo Kampus", "I used to study here! Good times.")
val library = SubArea("Library", "Shhhh... all kinds of books and maps here.", kampus)
val cafeteria = SubArea("Cafeteria", "It's closed. They used to sell bomb cinnamon rolls.", kampus)

val gauntlet = Area("The Gobbo gauntlet","This is the vault where all our bounty were stored. AND THEY TOOK IT ALL!")
val vault = SubArea("Vault", "Its full of... Nothing. Even our ancient holy relic was stolen.", gauntlet)

val goblinGroveGate = Area("The Goblin Grove gate", "The gate to your home Grove, packed with coin hungry goblins.\nYou can faintly smell the delicious foods sold in the marketplace.")

val outsideGoblinGrove = ChokePoint("Outside Goblin Grove", "Well where do I go now? I failed geography at school... I shouldn't aimlessly wander onwards... \nNOT without USING a MAP! ", Some("I've never wandered further from home grove. I heard that the crussadders left to the Toxic Marshlands!"))





//Toxic Marshlands
val toxicMarshlands = Area("Toxic Marshlands", "Formerly known as Boggy Marshlands. Now almost all life here is dead.")

val contaminatedSwamp = SubArea("Contaminated Swamp", "This swamp used to be known for its wide variety of insects and snakes.\nYou see a large roach climbing a dead tree. The smell is atrocious, even for a goblin.", toxicMarshlands)
val moonfallRiver = ChokePoint("Moonfall River", "A wide river, serving as a natural barrier between two worlds. On the horizon, you see darkness and a faint skyline.\nA small poisonous river from the Marshlands adjoins it", Some("The beavers built a damn to block the source of the poison. Crossing the river is possible again. \nThe other side of the river suddenly got more darker. \nThe Crussadders must be hiding there! No time to Waste!"))
val catfishCave = SubArea("Catfish Cave", "This cave is dark but very wide. There seems to be a light source on the other side...", toxicMarshlands)
val witchHut = Shop("Witch's hut", "The home of a renowned witch, guardian of the Marshlands.\nThe witch is here. You can buy all kinds of stuff here.", toxicMarshlands)
val toadPond = SubArea("Toad Pond", "Used to live up to its name being full of toads. Now you can see almost none. Where have they gone?", contaminatedSwamp)
val leakingCave = SubArea("The Leaking Cave", "Formerly known as the Cave of the Young.\nThere were rumours that the water from the source inside the cave could stop aging.\nWater is flowing in the middle of the cave into a pit.", toadPond)



//Eternum Forest
val eternumForest = Area("The Eternum Forest","This Forest is like a maze! I heard that a lot of monsters lurk here!")
val forestEntrance = Area("The Eternum Forest Entrance", "I should keep track of where im heading. Oh a shop! Better restock on items and Silver! \nWould love to not succumb to this cursed place!")
val shackShop = Shop("Shop Infinitum", "Creepy place! a Lot of monsters await inside the forest. Better restock on some repair kits!", forestEntrance)
val pathToMountains = ChokePoint("Path to mountains", "What an odd place! So dark.\nThere seems to be a path leading somewhere but it's blocked by dense bushes!", Some("What an odd place! So dark.\nWhacking those bushes was a breeze! A new path has appeared!"))
val hiddenStash = LockedSubArea("a Hidden stash behind the fallen trees.", "Wow! Who left these items here?", pathToMountains, axe)
val descentToSummit = ChokePoint("Dead end by an icy mountain", "Odd, this path didn't take me anywhere. There is an icy descent though... I could be able to climb... Crazy idea!", Some("Here I can climb to the Snowy peaks! Such a handy shortcut!"))
val shopStash = LockedSubArea("Shop Infinitum bedroom", "My first time in a girls bedroom! Do I have any chances?... FOCUS NOW!", shackShop, plushie)
val deepforest = Area("Deep inside forest", "Eeerie... NOTHING TO BE SCARED AT!")

//Eternum Forest — maze
val leftPath1 = Area("Left Path", "Better move forwards!")
val rightPath1 = Area("Right Path", "Better move forwards!")
val middlePath1 = Area("Middle Path", "Better move forwards!")
val wrongMiddle2 = Area("Middle jagged trail", "Better move forwards!")
val leftPath2 = Area("Left jagded trail", "Better move forwards!")
val wrongRightPath2 = Area("Right jagged trail", "Better move forwards!")
val middlePath2 = Area("Middle jagged trail", "Better move forwards!")
val middle2clear = ChokePoint("Dense bushes", "I see something behind the bushes! Too bad I can't reach It. If only I could get rid of these...", Some("No more bushes... But still eerie..."))
val bush = LockedSubArea("Inside the bush", "A less dense bush...Bush wookie!", middle2clear, axe)
val lostBoyarea = Area("Right jagged trail", "Better move forwards!")
val goBack = Area("Leave", "Too scary, I'm gonna leave...")
val backToStart = Area("a Path leading somewhere", "I'm at the start again?, I should keep track on where I went... \nOr ask for help? There was a merchant nearby.")
val rightPath2 = Area("Right jagged trail", "Better move forwards!")
val shovelClear = ChokePoint("a small clearing", "Better move forwards", Some("Nice! Digging revealed a secret stash!"))
val snowbootStash = LockedSubArea("a Secret Stash", "This must be the merchants secret stash!", shovelClear, shovel)





//Snowy Summits — Aaro
val snowySummits = Area("Snowy Summits", "A mountain range thousand of meters above sea level.\nNot many goblins have visited this place, even fewer came back alive.\nYou can see the top of Eagle Mountain, the highest peak in the world. The path to Eagle Mountain is covered in deep snow.")

val frozenCanyon = LockedSubArea("The Frozen Canyon", "This canyon is hundreds of meters deep. Miraculously, the creek in the middle isn't frozen.\nThe walls are mostly ice and feel extremely slippery.", snowySummits, icepick)
val eagleMountain = LockedSubArea("Eagle Mountain", "The highest mountain known to goblin. Always covered in deep snow.\nWalking in snow that deep seems impossible.", snowySummits, snowshoes)
val eaglesNest = SubArea("Eagle's Nest", "A massive eagle's nest on the side of the mountain.\nHome to the eagle ruling over most of the Summits. The eagle seems calm.", eagleMountain)
val heavensFalls = SubArea("Heavens' Falls", "Few have ever seen waterfalls as beautiful as these. You feel extremely cold around here.", snowySummits)
val yetisHideout = LockedSubArea("Yeti's Hideout", "The yeti's hideout is hidden behind a freezing waterfall. The yeti seems happy seeing a goblin in here.", heavensFalls, umbrella)
val climbersCabin = Shop("Climbers' Cabin", "An old log cabin located in the middle of the summits. A retired mountain climber is keeping the tavern.", snowySummits)





//Shadow Citadel — Yhdessä
val pathOfLanterns = Area("Path of Lanterns", "a Rocky path from the river to the citadel, lit only by lanterns few and far between. It is darker than a moonless night here.")
val skullShores = Area("Skull Shores", "The dark side of Moonfall river. Feels like the only light in here comes from the other side of the river.")
val moleHoleMarket = Shop("Mole Hole Market", "The moles have dugouts everywhere. Even in the land of the darkness. They seem to also sell goods here.", pathOfLanterns)

val shadowCitadel = Area("The Shadow Citadel", "The citadel of the crussadders")
val dungeonsOfHell = SubArea("Dungeons of Hell", "Crussadders throw their prisoner in these dungeons for eternal punishment. No prisoner ever escaped from here.", shadowCitadel)
val prisonCell = SubArea("Pricon cell", "Wold love to not be locked here... EEEEKK! A SPIDER!", dungeonsOfHell)
val crussaddersArmory = SubArea("Crussadders' Armory", "Massive piles of scary looking weapons and armor locked with huge locks. Oh hey! They forgot the key into one of the locks!", shadowCitadel)

val throneRoomEntrance = ChokePoint("The Shadow Citadel - The Entrance to Throne room", "This wall is covered with images of past emperors and portraits of... WHAT IS THAT THING?! Blue flamed torches light up the hallway.\na Door with a silver keyhole blocks your way.", Some("The door opened!\nThis wall is covered with images of past emperors and portraits of... WHAT IS THAT THING?! Blue flamed torches light up the hallway."))
val pillarsOfOrigin = ChokePoint("The Shadow Citadel - Pillars of Origin", "Enormous pillars bear the weight of the roof of a huge hall, which is so high you can't even see it.\nan Enormous gate with a golden keyhole blocks your way to the Throne room.", Some("The gate to the Throne room opened!\nEnormous pillars bear the weight of the roof of a huge hall, which is so high you can't even see it. They got all kinds of drawings on them.\nMaybe I should restock at Mole Hole Market, I feel like the end is near..."))
val emperorsThrone = Area("Emperor's Throne", "The throne of the Crussadder emperor. Everything here is made out of gold and silver. You see massive piles of coins along the walls.\nWHAT! The gate slammed shut behind me! I guess this is the point of no return.\n\nThe emperor steps onto a circle in front of the Throne.")
val circleOfEternity = SubArea("The Circle of Eternity", "A small circle in front of the Throne, right under the highest peak of the Citadel.\nStrange markings are painted onto the circle. The paint seems to glow dim purple light.", emperorsThrone)
