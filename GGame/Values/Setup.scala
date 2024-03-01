package GGame.Values

//This singleton object is used to set up the map of the game.
object Setup:

  def setup() =



    //The Goblin Grove
    //Neighbors


    gobboSuburbs.setTrueNeighbor(dwellings)
    dwellings.setTrueNeighbor(gobboSuburbs)
    suToDo.setTrueNeighbor(dojoE)
    doToSu.setTrueNeighbor(gobboSuburbs)

    suToCe.setTrueNeighbor(centrum)
    ceToSu.setTrueNeighbor(gobboSuburbs)

    dojoE.setLockedNeighbor(Vector(dojo), dojokey)
    dojo.setTrueNeighbor(dojoE)

    centrum.setTrueNeighbors(Vector(market, gauntlet, goblinGroveGate))
    goblinGroveGate.setTrueNeighbors(Vector(centrum, outsideGoblinGrove))
    gauntlet.setTrueNeighbor(centrum)

    outsideGoblinGrove.setTrueNeighbor(goblinGroveGate)

    market.setTrueNeighbors(Vector(centrum, kampus))
    kampus.setTrueNeighbor(market)

    outsideGoblinGrove.setLockedNeighbor(Vector(eternumForest,toxicMarshlands), map)


    //offers
    grill.addOffer(lortsyOffer)
    gobboShop.addOffers(Vector(ironrapierOffer, porridgeOffer, stitchesOffer, woodshieldOffer, poisonarrowOffer, voltcakeOffer, boltstaffOffer, slingshotOffer))

    //items
    dweeb.npcAddItem(trainingsword)
    dTreasure.addItem(trainingwear)
    dojoMaster.npcAddItem(dojokey)
    librarian.npcAddItem(map)
    broglin.npcAddItem(sewingkit)
    vault.addItems(Vector(powerpotion, spear))
    cafeteria.addItems(Vector(porridge, boltstaff))
    neighbor.addItem(porridge)


    //npc
    //friendly

    neighbor.addNPC(broglin)
    dojoE.addNPC(dojoMaster)
    dTreasure.addNPC(dweeb)
    dwellings.addNPC(vuhgu)
    gobboShop.addNPC(grablin)
    grill.addNPC(griller)
    centrum.addNPCs(Vector(garwin, general1, gontus))
    market.addNPCs(Vector(borgen, general3))
    outsideGoblinGrove.addNPC(general2)
    gobboSuburbs.addNPCs(Vector(lanky, scareddweller))
    gobboShop.addNPC(general4)
    library.addNPC(librarian)

    //enemy

    doToSu.addNPC(rat)
    suToCe.addNPC(blocker)
    cafeteria.addNPC(rat)
    goblinGroveGate.addNPCs(Vector(weakenedSadder, crussadderLeft))
    gauntlet.addNPC(weakenedSadder)
    dojo.addNPC(training)




    //Toxic Marshlands

    toxicMarshlands.setTrueNeighbors(Vector(goblinGroveGate, eternumForest))

    //Enemies
    catfishCave.addNPC(catfish)
    toadPond.addNPC(bat)
    contaminatedSwamp.addNPC(huylem)

    //Friendlies
    witchHut.addNPC(witch)
    toadPond.addNPC(toadini)
    contaminatedSwamp.addNPCs(Vector(urgui, general2))
    leakingCave.addNPC(phullir)
    toxicMarshlands.addNPC(raslei)
    moonfallRiver.addNPC(fishergoblin)

    //Items
    fishergoblin.npcAddItem(icepick)
    contaminatedSwamp.addItem(crussaddervest)
    leakingCave.addItems(Vector(weakacid, weakacid, weakacid, weakacid, sulfuricacid))



    //Offers
    witchHut.addOffers(Vector(protectingclawOffer, boltstaffOffer, powerpotionOffer, earthquakeOffer, stitchesOffer, sewingkitOffer))

    //Neighborships
    catfishCave.setTrueNeighbor(frozenCanyon)
    moonfallRiver.setLockedNeighbor(Vector(skullShores), beaverflute)
    moonfallRiver.setTrueNeighbor(contaminatedSwamp)
    contaminatedSwamp.setTrueNeighbor(moonfallRiver)


    //Eternum Forest

    // Neighbors
    eternumForest.setTrueNeighbors(Vector(goblinGroveGate, forestEntrance, toxicMarshlands))
    forestEntrance.setTrueNeighbor(toxicMarshlands)
    forestEntrance.setTrueNeighbor(pathToMountains)
    pathToMountains.setTrueNeighbor(forestEntrance)
    pathToMountains.setLockedNeighbor(Vector(descentToSummit, hiddenStash), axe)
    descentToSummit.setTrueNeighbor(pathToMountains)
    descentToSummit.setLockedNeighbor(Vector(heavensFalls), icepick)

    //maze
    forestEntrance.setTrueNeighbor(deepforest)
    deepforest.setTrueNeighbors(Vector(rightPath1,middlePath1,leftPath1, goBack))
    rightPath1.setTrueNeighbors(Vector(rightPath2, wrongMiddle2, leftPath2, goBack))
    middlePath1.setTrueNeighbors(Vector(lostBoyarea, wrongMiddle2, leftPath2, goBack))
    leftPath1.setTrueNeighbors(Vector(wrongRightPath2, middlePath2, leftPath2, goBack))

    wrongRightPath2.setTrueNeighbor(backToStart)
    leftPath2.setTrueNeighbor(backToStart)
    wrongMiddle2.setTrueNeighbor(backToStart)
    middlePath2.setTrueNeighbor(backToStart)
    shovelClear.setTrueNeighbors(Vector(goBack, backToStart))
    lostBoyarea.setTrueNeighbors(Vector(goBack, backToStart))

    backToStart.setTrueNeighbor(forestEntrance)
    goBack.setTrueNeighbor(forestEntrance)

    rightPath2.setTrueNeighbor(goBack)
    leftPath2.setTrueNeighbor(goBack)
    middlePath2.setTrueNeighbor(goBack)
    wrongMiddle2.setTrueNeighbor(goBack)
    rightPath2.setTrueNeighbor(goBack)
    wrongRightPath2.setTrueNeighbor(goBack)

    middlePath2.setTrueNeighbor(middle2clear)
    middlePath2.setTrueNeighbor(goBack)
    rightPath2.setTrueNeighbor(shovelClear)
    rightPath2.setTrueNeighbor(goBack)

    middle2clear.setTrueNeighbors(Vector(goBack, backToStart))


    //npc
    //friendly
    shackShop.addNPC(eternumMerchant)
    shopStash.addNPC(eternumMer2)
    lostBoyarea.addNPC(lostBoy)
    hiddenStash.addNPC(lostFather)

    //enemy
    rightPath1.addNPC(corruptedspirit)
    leftPath1.addNPC(cursedspider)
    middlePath1.addNPC(growler)
    middlePath2.addNPC(growler)
    wrongRightPath2.addNPCs(Vector(corruptedspirit, crossOutcast))
    wrongMiddle2.addNPCs(Vector(corruptedspirit, growler))
    rightPath2.addNPCs(Vector(hardwad, cursedspider))
    
    
    //items
    pathToMountains.addItem(nunchucks)
    eternumMer2.npcAddItem(shovel)
    bush.addItem(plushie)
    snowbootStash.addItem(snowshoes)
    lostBoy.npcAddItem(pervitin)
    hiddenStash.addItems(Vector(sewingkit, spinach))
    leftPath1.addItems(Vector(sewingkit, porridge))
    rightPath1.addItems(Vector(silveramulet))
    middlePath1.addItem(poisonarrow)
    rightPath2.addItem(sewingkit)
    leftPath2.addItem(earthquake)
    hiddenStash.addItem(chainmailarmor)

    //Offers
    shackShop.addOffers(Vector(axeOffer, spinachOffer, fragilerOffer, sewingkitOffer, holystoneOffer))
    
    
    
    //Snowy Summits
    //Enemies
    heavensFalls.addNPC(bear)
    eagleMountain.addNPC(wolf)

    //Friendlies
    yetisHideout.addNPC(yeti)
    heavensFalls.addNPC(penguin)
    climbersCabin.addNPC(plorel)
    eaglesNest.addNPC(eagle)
    frozenCanyon.addNPCs(Vector(ergus, general2))
    snowySummits.addNPC(goat)

    //Items
    yetisHideout.addItems(Vector(holystone, steelshield))
    eagleMountain.addItem(silveramulet)
    eaglesNest.addItems(Vector(bone, silveramulet, spinach))
    eagle.npcAddItem(umbrella)
    yeti.npcAddItem(beaverflute)



    //Offers
    climbersCabin.addOffers(Vector(stitchesOffer, slingshotOffer, porridgeOffer, spinachOffer))

    //Neighborships
    frozenCanyon.setTrueNeighbor(catfishCave)


    //The Shadow Cicadel
    //Enemies
    crussaddersArmory.addNPCs(Vector(guard, armoryMaster))
    pathOfLanterns.addNPCs(Vector(guard, crussadderLancer))
    throneRoomEntrance.addNPC(crussadderKnight)
    dungeonsOfHell.addNPCs(Vector(dungeonWarden, bat))

    //Friendlies
    moleHoleMarket.addNPC(mole)
    prisonCell.addNPC(prisoner)



    //Items
    crussaddersArmory.addItems(Vector(obsidianlongsword, goldenKey))
    pathOfLanterns.addItems(Vector(spinach, spinach, stitches))
    dungeonsOfHell.addItems(Vector(slimysock, silverKey))
    mole.npcAddItem(morphine)
    shadowCitadel.addItems(Vector(earthquake, sewingkit))
    prisonCell.addItems(Vector(sewingkit, porridge))
    prisoner.npcAddItem(lortsymeal)

    //Offers
    moleHoleMarket.addOffers(Vector(stitchesOffer, sewingkitOffer, platecarrierOffer, earthquakeOffer, silveramuletOffer, vinespellOffer))

    //Neighborships
    skullShores.setTrueNeighbors(Vector(moonfallRiver, pathOfLanterns))
    pathOfLanterns.setTrueNeighbors(Vector(skullShores, shadowCitadel))

    shadowCitadel.setTrueNeighbors(Vector(pathOfLanterns, throneRoomEntrance))
    throneRoomEntrance.setTrueNeighbor(shadowCitadel)

    throneRoomEntrance.setLockedNeighbor(Vector(pillarsOfOrigin), silverKey)
    pillarsOfOrigin.setTrueNeighbor(throneRoomEntrance)

    pillarsOfOrigin.setLockedNeighbor(Vector(emperorsThrone), goldenKey)




end Setup
