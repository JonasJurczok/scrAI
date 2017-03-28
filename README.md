screepsAI
===================

Based on scrAI by NickToony

About
-------------
TODO: expand


Dependencies
----------

**Not Provided**

The following are external dependencies you must install to get the most out of this setup

 - Maven
	 - (optional) with M2_HOME environment variable defined

**Provided**

The following are provided by the Maven pom.xml file.

 - ST-JS
	 - http://st-js.github.io/
 - ST-JS Javascript Bridge
 - Google's maven-replacer-plugin
	 - https://code.google.com/p/maven-replacer-plugin/
 - YUI Compressor Maven plugin
	- http://davidb.github.io/yuicompressor-maven-plugin/


Setup
-------------------

Be advised that this setup is intended for single player games only.

To run the project first navigate to the pom and replace the `game.directory` property with the
path to your local javascript files.

Now execute `mvn clean compile verify` to run all required steps. This command will
* Compile your java code
* Run the st-js javascript compiler
* Compress all your files into one single file
* Minify your javascript to make it run even quicker
* Delete all existing javascript files in your game directory
* Copy your new main.js file into the game folder.

This will replace your whole logic with the next tick.


Modifiying
-------
**Module Importing, Exporting and STJS**

Since I changed the setup to instead combine all scripts into one single javascript file, you no longer need to import/export modules. This means the previous dirty hacks to implement the unsupported Javascript syntax in Java is not necessary.

STJS still requires its methods though, so a custom STJS implementation is provided that will work when included inside the final script.

The advantages of using a single script are:
- Importing/Exporting not necessary, don't need to create dirty hacks in Java to implement them
- Requiring a module in screeps could take an unpredictable amount of time. Performance is a lot better if you just have one script.

**Lodash**

Screeps likes to use javascript which is just plain difficult for ST-JS to bridge. An example of this is the *Game.rooms* hashmap - which doesn't bridge at all well! To get around this, you can import the Lodash module, and use that to iterate over Javascript collections.

Architecture
-------

**GlobalController**

The GlobalController looks at the map as a whole. It doesn't worry about managing each rooms contents, but the interactions between rooms. It decides where to reinforce, where to expand (or assault) and the parameters affecting each room (such as alert status).

**RoomController**

A layer below GlobalController, this controller is given the responsibility of managing one specific room. It relies on Managers (which organise and act upon room contents) and Advisors (which take the organised data of the Managers and suggests the next move). Which advisor it listens to is decided by the RoomController's threat and alert levels.

**MilitaryAdvisor**

Decides on military matters; units to produce, where to position and defensive structures.

**EconomyAdvisor**

Decides on economy matters: units to produce, which sources to gather, buildings to maintain/build.

**SourcesManager**

Stores all Sources in the room, and determines which are safe to gather from.

**SpawnsManager**

Stores all Spawns in the room, and determines which are available to spawn from.

**TasksManager**

The task manager tracks the memory of each Task currently in the room. Tasks are distributed to Worker creeps in an intelligent manner, which ensures the most important tasks are prioritised and carried out efficiently.

**PopulationManager**

Stores all creeps, and sorts them by type into an array. It creates the correct CreepWrapper class for each, which is useful for allowing the creep to perform its designated function.

**CreepWrapper**

A wrapper for the built-in creep object. Each creep type extends from this class, which tracks the creeps memory and status. It contains the creeps logic.
