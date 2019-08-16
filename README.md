# mario-style-game
Created using MVC architecture. 

## Requirements 
* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven 3](https://maven.apache.org/download.cgi)

## Setup
Clone this repo to your machine and execute the following Maven command:
```
mvn clean install
```
Execute the __my-mario-game-1.0-SNAPSHOT-jar-with-dependencies.jar__ file located in /target to start the game. 

## Controls
* Left and right arrow keys to walk.
* Space bar to jump.
* Press 'L' to load the previously saved map/restart the game.

## How to Create a Map
1) Start the game without loading the previous map.
2) Draw bricks by left clicking and dragging to draw a box. 
3) Draw Sprites:
    * Hold one of the following characters while right clicking to draw its corresponding sprite:
        * 'T' - turtle
        * 'G' - goomba
        * 'B' - coin block
4) Press 'S' to save your current map.
5) Press 'L' to load your new map.