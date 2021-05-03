# Crossword Platformer

This game is a 2D platformer that requires solving simple crosswords to complete.

Play it online on [**itch.io**](https://voddan.itch.io/crossword-hero)

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
 - [x] 0.3: Player can collect letters. Collected letters are displayed in UI.
 - [x] 0.4: Objects with letters disappear as their letters get collected.
 - [x] 0.5: Objects reappear if correct letters are put into their slots.
 - [x] 0.6: Objects have sprites; there are 2 types of objects
 - [x] 0.8: One game level with 3 types of objects
 - [x] 0.9: Player has a sprite
 - [x] **1.0**: 1st game level is deployed to [itch.io](https://itch.io/) to get feedback
 - 1.+: menu, 2nd level, basic animations
 - [ ] 1.1: Player has movement animation
 - [ ] 1.2: Backpack shows letters in a sorted order
 - [ ] 1.3: Game world scrolls with the player movement
 - [ ] 1.4: Animation plays when a word is filled in
 - [ ] 1.5: There is a 2nd level after the first one
 - [ ] 1.6: Start menu allows to choose a level
 - [ ] 1.7: Player moves on mouse clicks
 - [ ] 1.8: Letters can be drag-and-dropped
 - [ ] 1.9: JS version is monkey-tested and has no visible bugs
 - [ ] **2.0** Scroller-like version is playable and published to forums 
 - 2.+: convert to top-down view
 - [ ] 2.1: Background is a tile map
 - [ ] 2.2: Player moves in 4 directions using keys
 - [ ] 2.3: Player animation changes depending on movement direction
 - [ ] 2.4: Player moves in 4 directions on mouse click
 - [ ] 2.5: Collecting letters works
 - [ ] 2.6: Inserting letters into objects works
 - [ ] 2.7: Objects appear when filled
 - [ ] 2.8: Camera follows player
 - [ ] 2.9: There is one top-down level with 3 kinds of objects
 - [ ] **3.0** Top-down version is playable
 - 3.+: tutorial, control hints, backstory
 - [ ] 3.1: Control hints when player doesn't move for a long time
 - [ ] 3.2: Control hints when player is about to do something for the 1st time (move, collect letter, etc) 
 - [ ] 3.2: Tutorial level that shows all controls
 - [ ] 3.3: Backstory sequence inside the tutorial level
 - [ ] **4.0** Game can be play-tested with children
 - 4.+: 3rd level, detailed assets, sounds, polished animations
 - [ ] 4.1: Voice reads words when they are correctly filled in
 - [ ] 4.2: Player can interact with an appeared object 
 - [ ] **5.0** Game is polished and released
 
 ## Design Ideas
 
 ### Scroller
 
 Player moves through a left-to-right scroll platformer. 
 Player is able to collect letters of the English alphabet and 
 materialise objects by combining collected letters into words.
 The materialised objects interact with the environment and 
 help the Player to overcome obstacles.
 
 ### Top-down RPG
 
 An wizard stoll names of all objects on Alice's village, 
 and all objects and animals disappeared. 
 Alice needs to collect letters scattered through the village 
 to fill in the names and bring everyone back to existence.
 
 Alice's village include:
  - field
  - farm
  - garden
  - forest
  - house
  - street
 
 ### Naming
 
 - Working name: Crossword Platformer
 - Suggested name: 
   - Alice in the Word-land
   - Word-well
   - No-name village
   - Nameless
 
 ### Objects
 
- Field
  - wheat
  - corn
  - cow
  - horse
  - sheep
- Farm
  - pig
  - piglet
  - chicken
  - duck
  - rabbit
- Garden
  - apple (tree)
- Forest
  - hare
  - wolf
  - maple (tree)
- House
  - table
  - chair
- Street
  - post
  - church
  - car
  - fountain
  - policeman
  - seller
