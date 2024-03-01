package GGame.Values

import GGame.classes.{EnemyNPC, FriendlyNPC}

//FriendlyNPC(name: String, description: String, line: Option[String]) extends NPC(name, description, line)
//EnemyNPC(name: String, description: String, line: Option[String], hp: Int, attackPOW: Int, defencePOW: Int)

// general npcs

val general1 = FriendlyNPC("Villager", "Just an ordinary villager", None)
val general2 = FriendlyNPC("Traveling goblin", "a Restless soul abroad. I've always dreamt of traveling.", None)
val general3 = FriendlyNPC("Drunk", "Based on the smell, this individual is high on some sea weed.", None)
val general4 = FriendlyNPC("Citizen", "Some boring gal. Lets move on!", None)


//The Goblin Grove

//Friendlies
val garwin = FriendlyNPC("Garwin", "An old goblin luring people into the marketplace", Some("You should try out the porridge at the market!"))
val grablin = FriendlyNPC("Grablin", "Used to be a shaman. Now he sells al kinds of goods in the plaza. Sells the best porridge in the grove.", Some("My porridge is the bomb..."))
val borgen = FriendlyNPC("Borgen", "The most infamous goblin on the market. Always on seaweed.", Some("BRRGGL... Backk to the tavern i ggooo!"))
val vuhgu = FriendlyNPC("Vuhgu", "One of your neighbors. A bit loud and often zoned out.", Some("Remember to keep your Silver safe!"))
val griller = FriendlyNPC("Grillmaster", "He is the man behind these Lörtsys. The world owes him.", Some("Buy some lörtsys before you go!"))
val scareddweller = FriendlyNPC("Scared dweller", "Poor thing! So scared of the whole ordeal", Some("I can't get to the Centrum because those Crussadders might still be around. Im hungry... :("))
val gontus = FriendlyNPC("Gontus", "One of the elders. He is so wise!", Some("Those pesky Crussadders! They have something on their minds...\nBy the way, they seemed to be heading to Toxic Marshlands! Like outside the Grove..."))
val lanky = FriendlyNPC("Lanky", "He is so tall! Some tattle that he's a crossbreed... a goblin that tall?", Some("All my savings... ROBBED! The Crussadders headed out to Toxic Marshlands. What are they upto?"))

// Important
val librarian = FriendlyNPC("Librarian", "The librarian of the campus, Marga", Some("If you're looking for gold, you won't find it here. Heh.\n\nOh! You're looking for a Map? I've got the one you're looking for! Here."))
val dojoMaster = FriendlyNPC("Dojomaster", "The owner of this dojo. I could learn a move or two from him!", Some("So you want to know how to BATTLE? So here's the deal. \nIf you encounter an enemy on your journey you cannot bypass it.\nYou need to defeat it first to move forwards. To challenge an enemy you just type \"battle [enemy name]\".\n\nCombat works as follows: First its your turn to do something. Heres what you can do:\n\"IDENTIFY\": Permanently shows your enemy's stats. Counts a turn.\n\"ATTACK\": Follow your action command and strike with full power! Counts a Turn.\n\"USE [item name]\": Uses one of your items. Counts a turn.\n\"INVENTORY\": Checks your belongings. Doesn't count a turn\n\"EXAMINE [item name]\": Examine an item. Doesn't count a turn.\n\"RUN\": Try fleeing from battle. Based on luck. Counts a turn.\n\nThen it's your foe's turn...\nThats all. If you forget something you can always use \"HELP\".\nAnd oh here's the key to the dojo. You should practise!" ))
val dweeb = FriendlyNPC("Lonely dweeb", "a Student at the dojo. Doesn't get along with others sadly.", Some("Nobody appreciates me :(\nOh, you found some nice stuff!\nDon't forget to equip your newly found gear. Equip them by typing \"Use [item name]\".\nOh, and before you do that remember to \"EXAMINE\" all your items to fully understand what they do! To get a list of all your items type \"INVENTORY\".\nYou can also \"EXAMINE\" people and item offers at shops. \n\nTalking to someone really made me feel good! Thank you for listening!\nHere's my training sword! Now you can get through some tougher enemies. Good luck!"))
val broglin = FriendlyNPC("Broglin", "My dear younger brother. He should get his life together... Like look at this place!", Some("So you really are after those Crussadders huh? I could never... I mean good luck!\nThose crussadders headed out to Toxic Marshlands. You know the way out of town right? \nWell you can see the gates from the Centrum... You'll figure it out."))


//Enemies
val blocker = EnemyNPC("Blonkus", "That is an ally of the Crussadders. This blocky fella is hard as a brick. My punches won't do much...", Some("You can't touch me! Better RUN away!!"), 2, 1, 1)
val rat = EnemyNPC("Rat", "It's just an aggressive rat.", Some("Meep... Meep MEEP!"), 2, 1, 0)
val training = EnemyNPC("Whitebelt", "a Student at the dojo. Lets spar!", Some("Let's spar!"), 3, 0, 0)
val weakenedSadder = EnemyNPC("Weak crussadder", "a Crussadder left behind! The heist didn't end well fore some... This should be easy.", Some("I hate this place, I HATE GOBLINS!\nSoon there will be nothing left when the dark lord emerges!"), 2, 3, 0)
val crussadderLeft = EnemyNPC("Crussadder", "a Crussadder left behind! He's trying to help his injured pal... SHAME HAHAHHA!\nThough he is really tough! Maybe I should use some items or get a new Weapon.", Some("I HATE GOBLINS!\nJust wait for the arrival of our true king the dark lord!"), 8, 4, 1)

//Toxic Marshlands

//Friendlies
val witch = FriendlyNPC("Witch", "Renowned witch who's lived in the Marshlands their entire life.", Some("The Marshlands became contaminated a couple days ago.\nI need help finding the source of the contamination."))
val toadini = FriendlyNPC("Toadini", "The elder of the toads.", Some("Almost all of us are dead... How will we ever recover?"))
val urgui = FriendlyNPC("Urgui", "A lonely beaver. Decided to stay behind to warn others.", Some("If only the others would come back, we could build a dam to save the river..."))
val phullir = FriendlyNPC("Phullir", "Nomad living in the contaminated cave. Seems peaceful.", Some("The Crussadders came here and cursed the water source in the back... I couldn't stop it.\nAll this poison is leaking to the Moonfall River... \nI've heard rumors that the Crussadders have set camp to the other side of the river.\nMaybe that's why the cursed this source?"))
val raslei = FriendlyNPC("Raslei", "A goblin waddling towards the Grove, seemingly out of their mind.", None)
val fishergoblin = FriendlyNPC("Fisher goblin", "An old fisher living in hut on the riverbed.", Some("After the Crussadders crossed, nothing has come out the river... A lynx drank from it and died in seconds...\nI could barely save my boat before it dissolved.\nThe poison seems to come from the small river joining Moonfall River coming from the Marshlands.\nIf only there was a way to block it... Would need to be someone with a thick waterproof skin and building skills. I can think of no such being.\n\nAnyway, if I were you I would check out the Snowy Summits. The path to there is icy, so you might need this."))

//Enemies
val catfish = EnemyNPC("Catfish", "A giant catfish occupying a cave", Some("GET OUT OF MY CAVE"), 14, 5, 1)
val bat = EnemyNPC("Bat", "A mutated bat with razor sharp fangs", Some("BBLLLOOOODDD"), 5, 11, 0)
val huylem = EnemyNPC("Huylem", "A wounded Crussadder left to die in the Leaking Cave", Some("I'll take you with meeee..."), 2, 1, 0)


//Eternum Forest

// Friendly
val eternumMerchant = FriendlyNPC("Paranoid merchant", "Creepy looking hag... I mean she must be lovely once you get to know her!", Some("What in the world are you doing here! A silly little goblin like you will get lost!\nYou don't think about going deeper inside the forest, do you?... \n\nWell you won't find what your looking for... I might be able to help... But I need a favor.\nYou see I lost my favourite PLUSHIE. Retrieve that and I'll spill some inside information. \nI Lost it while going Left and then... I don't remember... Somewhere around dense bushes...\n\nAnyway, when you have retrieved it remember to [USE PLUSHIE] inside my shop!\n\nOh, and be sure to check my offers! I might have some VERY USEFUL stuff."))
val eternumMer2 = FriendlyNPC("Paranoid merchant", "Creepy looking hag... I mean she must be lovely once you get to know her!", Some("OOHMYGOSH!! Thank you!! Now as i promised... First go RIGHT then RIGHT again then go to the little CLEARING. When you get there USE this!"))
val lostBoy = FriendlyNPC("Lost boy", "Poor thing is lost! Glad we found him.", Some("I'm lost and scared!! OHMYGOD SOMEBODY CAME! You my... goblinsir? are my saviour.\nTo show my gratitude here's a reward."))
val lostFather = FriendlyNPC("Lost father", "How did he end up here?", Some("Have you seen my son? My son ran away to the forest. Oh what a mess.\nPlease help me find my son!"))

// Enemy

val cursedspider = EnemyNPC("Cursed spider", "HOW IS THIS SPIDER SO BIG?", Some("My string will end you ;)"), 9, 6, 1)
val corruptedspirit = EnemyNPC("Corrupted spirit", "Forest spirits used to be nice but after the Crussadders appeared they went insane", None, 15, 5, 1)
val crossOutcast = EnemyNPC("Crosscast", "One of those pesky Crussadders. This one seems to be an outcast since he's alone.", Some("MORE GOBLINS? I hate them... HATE, HATE, HATE!"), 4, 7, 2)
val hardwad = EnemyNPC("Hardwad", "Thats a serious fella! Skin made out of hard wood! I need some Items to beat that", None, 6, 5, 6)
val growler = EnemyNPC("Growler", "Those teeth are as sharp as razors. They even glimpse in sunlight... If sunlight existed here... Their growls are funny.", Some("GROIOIOIOIWLLLL..."), 10, 5, 0)

//Snowy Summits

//Friendlies
val yeti = FriendlyNPC("Yeti", "A lonely yeti living in a hideout in the Snowy Summits. Seems friendly.", Some("A goblin! I haven't seen one in 150 years! Have a gift from me!\nIf you're ever lonely, just play this flute and you'll get some flat-tailed friends!"))
val penguin = FriendlyNPC("Penguin", "It's just a penguin. Somehow hasn't been eaten by bears in the Falls area.", Some("Watch out, the mineral water in the falls will freeze you over in a second!\nYou need something to block water with if you want to meet the ye... I mean go fish.\nThe eagle has all kinds of stuff in its nest, but you to have ascend the Eagle Mountain to get there."))
val plorel = FriendlyNPC("Plorel", "A retired climber keeping a tavern in the Summits.", Some("A customer! First one in months! Everything is super cheap, as you can see!"))
val eagle = FriendlyNPC("Eagle", "The mighty eagle ruling most of the Summits. Generous, but better not to anger it.", Some("You've come all the way here, little goblin. That means you need something. What is it?\nA way to pass the Falls? Here, I have just the thing."))
val ergus = FriendlyNPC("Ergus", "Goblin tasked with guarding the entrance to the Marshlands. Been here for over a hundred years.", Some("You came to climb to the Summits? To even get up from here, you need a pick or awls."))
val goat = FriendlyNPC("Goat", "A mountain goat traversing the Summits for fun.", Some("Wanna climb the Eagle Mountain? Ain't happenin' with your... vertical limitations. You'll sink in the snow in no time.\nI've seen some goblins travel there using snowshoes, but the last pair i saw were with the paranoid merchant in Eternum Forest decades ago..."))

//Enemies
val wolf = EnemyNPC("Wolf", "A lone wolf hunting for food", Some("I LOVE the taste of goblins!"), 12, 6, 1)
val bear = EnemyNPC("Polar bear", "Just a bear trying to fish at the Falls. It is in your way, though...", Some("This my fishing spot. I won't leave without a fight."), 14, 5, 3)



//Shadow Citadel

//Friendlies
val prisoner = FriendlyNPC("Prisoner", "A very old and weak goblin. Been here at least a couple decades.", Some("Did you come to save us? I've been waiting half a century! Cant wait to see my son Borgen again!\nHave my meal, you need energy!"))
val mole = FriendlyNPC("Mole", "Mole keeping a secret shop in the land of the Darkness", Some("Welcome to Mole Hole Market! As a welcome gift, have this medicine! It'll ease your pains."))
//Enemies
val guard = EnemyNPC("Guard", "A well trained crussadder guarding the citadel", None, 10, 9, 2)
val crussadderKnight = EnemyNPC("Crussadder knight", "An elite crussadder soldier. Hits hard. Guards only the most holy places.", None, 22, 10, 2)
val crussadderLancer = EnemyNPC("Crussadder lancer", "Regular crussadder soldier. Well trained, but cant make miracles happen.", None, 15, 10, 1)

val armoryMaster = EnemyNPC("Armory master", "The weapon smith of the crussadders. Tasked with creating, maintaining and guarding the arsenal fo the crussadders.\nThis crussadder is huge, but can't handle any the weapons he made. It's gonna take a minute to defeat this thing.", Some("I've made all these weapon over millenia and you're not getting ANY OF THEM!"), 30, 9, 0)
val dungeonWarden = EnemyNPC("Dungeon warden", "This beast is made out of solid rock. Impenetrable armor! I need to use damage items to damage that thing!\nRun away?", None, 6, 11, 100)

val emperor = EnemyNPC("The Dark Emperor", "Cursed Emperor, the ruler of the wicked. The Crussaders summoned this behemoth.\nIT is behind this terrible curse. Lets end this here!", Some("You won't stop us now! You've hit the end of your road."), 50, 15, 2)
