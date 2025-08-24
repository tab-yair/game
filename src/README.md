# Ass5 Game Project - Arkanoid

This repository contains the source code for a Java-based Arkanoid-style game project. The game features a player-controlled paddle, bouncing balls, and breakable blocks. It demonstrates object-oriented design, event handling, and basic game programming concepts.

---

## Project Overview

The project is an **Arkanoid**, where the player controls a paddle to bounce balls and break all the blocks on the screen. It demonstrates the use of object-oriented programming principles, event listeners, and game loops.

### Key Features

* Player-controlled paddle movement.
* Balls that bounce and interact with blocks and the paddle.
* Block removal and score tracking.
* Event-driven architecture using listeners.
* Organized structure with clear separation of responsibilities.

---

## Project Architecture

```
          +-------------------+
          |      Game.java     |  <-- Main entry point, initializes game loop
          +-------------------+
                     |
                     v
        +----------------------+
        |   GameEnvironment    |  <-- Holds all Collidable objects
        +----------------------+
           |                 |
           v                 v
  +----------------+   +----------------+
  | SpriteCollection | | Collidable objects |
  |                  | | (Blocks, Paddle)  |
  +----------------+   +----------------+
           |                 |
           v                 v
     +-------------+     +----------------+
     |  Entities   |     |   Listeners    |
     | Ball, Block,|     | BallRemover,   |
     | Paddle, etc.|     | BlockRemover,  |
     +-------------+     | ScoreTracking  |
                         +----------------+
                              ^
                              |
                        +-------------+
                        |  Geometry   |
                        | Point, Line,|
                        | Rectangle   |
                        +-------------+
```

### Explanation of interactions

* **Game.java**: Initializes the game window, loads sprites and collidable objects, and runs the main game loop.
* **GameEnvironment**: Manages all `Collidable` objects and handles collision detection.
* **SpriteCollection**: Updates and draws all sprites each frame.
* **Entities**: Interactive objects such as Ball, Block, and Paddle.
* **Listeners**: Handle game events (hits, score updates, ball removal).
* **Geometry**: Provides basic geometric classes for collision calculations.

---

## Project Structure

```
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
```

---

## Class Descriptions

**Entities:**

* **Ball.java**: Handles movement, velocity, and collision with blocks/paddle.
* **Block.java**: Represents blocks that can be hit and removed.
* **Paddle.java**: Player-controlled paddle; interacts with balls.
* **Sprite.java**: Interface for drawable and updatable objects.
* **Velocity.java**: Encapsulates speed and direction for moving objects.

**Collision:**

* **Collidable.java**: Interface for objects that can be collided with.
* **CollisionInfo.java**: Stores collision details (point, object hit).

**Collections:**

* **GameEnvironment.java**: Holds and manages all collidable objects.
* **SpriteCollection.java**: Holds all sprites and updates/draws them each frame.

**Listeners:**

* **HitListener.java / HitNotifier.java**: Observer pattern for hit events.
* **BallRemover.java / BlockRemover.java**: Remove balls or blocks on certain events.
* **ScoreTrackingListener.java**: Updates the score when blocks are hit.

**UI & Utils:**

* **ScoreIndicator.java**: Displays score on the screen.
* **Counter.java**: Utility for tracking counts like score or remaining balls.

**Geometry:**

* **Point.java, Line.java, Rectangle.java**: Basic geometric classes for collision calculations.

---

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/tab-yair/game.git
cd game
```

2. Compile all Java files:

```bash
javac src/**/*.java
```

> On Windows, compile subdirectories individually:

```bash
javac src\*.java src\colilision\*.java src\collections\*.java src\entities\*.java src\game\*.java src\geometry\*.java src\listeners\*.java src\ui\*.java src\utils\*.java
```

3. Run the main game:

```bash
java -cp src Game
```

---

## Requirements

* Java Development Kit (JDK) 8 or higher.
* IDE recommended (IntelliJ, Eclipse, VS Code).

---

## Optional Enhancements

* Add more block types or power-ups.
* Extend UI elements for levels or player lives.
* Add sound effects and animations.

---

## License

This project is licensed under the MIT License (if applicable).
