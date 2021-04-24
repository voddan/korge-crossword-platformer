# Crossword Platformer

This game is a 2D platformer that requires solving simple crosswords to complete.

## Running

The project has been tested on JVM, JavaScript and Linux x64.

To run on JVM (no prerequisites):

    ./gradlew runJvm
   
To run on JavaScript (requires a web browser):

    ./gradlew runJs
    
To run natively on Windows, Linux or MacOS follow those 
[instructions](https://korlibs.soywiz.com/korge/deployment/desktop/).

## Implementation Plan

 - [x] 0.1: Game runs on JVM, JS and natively.
 - [x] 0.2: Player moves left-right and interacts with platforms.
 - [ ] 0.3: Player can collect letters. Collected letters are displayed in UI.
 - [ ] 0.4: Objects with letters disappear as their letters get collected.
 - [ ] 0.5: Objects reappear if correct letters are put into their slots.
 - [ ] 0.6: Player can interact with an appeared object  
 - [ ] 0.7: One game level with 3 objects and 2-3 obstacles
 - [ ] **1.0**: 1st game level is deployed to [itch.io](https://itch.io/) to get feedback
 - 1.+: menu, 2nd level, basic animations
 - 2.+: 3rd level, detailed assets, sounds, polished animations
 
 ## Design Ideas
 
 Player moves through a left-to-right scroll platformer. 
 Player is able to collect letters of the English alphabet and 
 materialise objects by combining collected letters into words.
 The materialised objects interact with the environment and 
 help the Player to overcome obstacles.
 
 ### Naming
 
 - Working name: Crossword Platformer
 - Suggested name: Alice in the Word-land
 
 ### Obstacles and Interactions
 
 - Get up or down a cliff
   - ROPE
   - TREE
   - LADDER
   - STAIRS
 - Cross a gap 
   - BRIDGE
   - LOG
   - WATER
 - Pass an animal
   - SWORD
   - MEAT
   - FOOD
   - GUN
 - Capture an animal
   - MEAT
   - FOOD
   - NUT, NUTS
   - TRAP
   - HOOK
   - NET
 - Build a structure
   - BRIDGE
   - STAIRS
   - LADDER
   - HOUSE
   - CAR
   - BOAT
   - LAMP
 - Interact with an NPC
   - GOLD
   - FOOD
   - Capturing an animal
   - Defeating an animal
   - Building a structure