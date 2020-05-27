swe-project
=============
Roguelike in developement

## Requirements
This project requires JDK 11 or later and [Apache Maven](https://maven.apache.org/).

#### Feature list
* procedurally generated levels using tunneling algorithm
* save/load
* A* search algorithm

After the game starts the player can move by clicking on the grid. Pressing R regenerates
the level. F5 saves and F6 loads the last save. The player is represented with the yellow block.
The blue and the red blocks would represent the exits of the level. However at the moment
if I let them do their things the load function would cause a minor bug. So right now their final code is note commited. 