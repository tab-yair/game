Ass5 Game Project

This repository contains the source code for a Java-based game project. It demonstrates object-oriented design, event handling, and basic game programming concepts.

Project Architecture
          +-------------------+
          |      Game.java     |  <-- Main entry point, initializes game loop
          +-------------------+
                     |
                     v
        +----------------------+
        |  GameEnvironment      |  <-- Holds all Collidable objects
        +----------------------+
           |                 |
           v                 v
     +----------+       +-----------+
     |  SpriteCollection |  |  Collidable objects (Blocks, Paddle) |
     +----------+       +-----------+
           |                 |
           v                 v
  +----------------+   +----------------+
  |    Entities    |   |  Listeners     |
  |  Ball, Block,  |   | BallRemover,   |
  |  Paddle, etc.  |   | BlockRemover,  |
  +----------------+   | ScoreTracking  |
                        +----------------+
                              ^
                              |
                        +-------------+
                        | Geometry    |
                        | Point, Line,|
                        | Rectangle   |
                        +-------------+

Explanation of interactions:

Game.java initializes the game window, loads all sprites and collidable objects, and runs the main game loop.

GameEnvironment manages all Collidable objects and handles collision detection.

SpriteCollection updates and draws all sprites each frame.

Entities (Ball, Block, Paddle) are the interactive objects in the game.

Listeners respond to game events, like hits or score changes.

Geometry classes provide support for collision calculations.

                        
Project Structure
/src
  Game.java
  /colilision
    Collidable.java
    CollisionInfo.java
  /collections
    GameEnvironment.java
    SpriteCollection.java
  /entities
    Ball.java
    Block.java
    Paddle.java
    Sprite.java
    Velocity.java
  /game
    Game.java
  /geometry
    Line.java
    Point.java
    Rectangle.java
  /listeners
    BallRemover.java
    BlockRemover.java
    HitListener.java
    HitNotifier.java
    ScoreTrackingListener.java
  /ui
    ScoreIndicator.java
  /utils
    Counter.java

    
How to Run
1. Clone the repository:
git clone https://github.com/tab-yair/game.git
cd game
2. Compile all Java files:
      javac src/**/*.java
3. Run the main game:
   java -cp src Game

Requirements

Java Development Kit (JDK) 8 or higher.

IDE recommended (IntelliJ, Eclipse, VS Code).

License

This project is licensed under the MIT License (if applicable).   
