# My Personal Project

## A Single-Panel Game Survival Game

### What will the application do?
This application is a Zelda inspired survival-type game where the player is placed in a single panel setting, 
and you must destroy incoming enemies. Enemies are basic—only approach you and can damage you upon contact.
The player has a health bar, and everytime the enemy contacts the player, the player's health bar decreases.
Game is over when player’s health bar is at zero.

The player is a wizard who can move in 2-dimensions (in all four directions). The player can also throw blasts to 
damage incoming zombies.

### Who will use it?
As described, this application is a game, thus will be played by anyone that is interested in retro-style games
with a survival component.

### Why is this project of interest to you?
Although I have not been programming for long, everytime I think of a project to do, my mind always gravitates towards 
designing a game. For example, during CPSC 110 I would program games in my spare time and in the summer I programmed 
snake in JS + HTML + CSS. I want to challenge myself by making a game I would personally enjoy, thus why my game has 
many interactive components.

## User Stories

- As a user, I want to be able to have a designated button for a blast, which adds to all the blasts in the game
- As a user, I want to be able to move in all four directions
- As a user, I want zombies to spawn from random positions
- As a user, I want to be able to see my health bar
- As a user, whenever I quit the game, I want the game state to be automatically saved in a file
- As a user, whenever I start the game, I want to be given the option of starting from a previously saved state (or not)

## Instructions for Grader
- You can generate the first required event relating to shooting a blast into the game by pressing the "k" key
- You can generate the second required event relating to shooting a blast into the game by clicking the "shoot" button
- You can always see my visual component while in the game screen, which will show you the current game state
- You can save and quit the game by pressing "b" which will take you to the main menu (automatically saves)
- While in the main menu, you can choose to choose a new game or load the previously saved game by pressing the 
  corresponding buttons

## Phase 4

### Task 2

#### Sample:

    Wed Nov 30 18:33:41 PST 2022
    Wizard threw a blast! Number of blasts: 1
    Wed Nov 30 18:33:43 PST 2022
    Wizard threw a blast! Number of blasts: 2
    Wed Nov 30 18:33:44 PST 2022
    Wizard threw a blast! Number of blasts: 3
    Wed Nov 30 18:33:45 PST 2022
    Blast absorbed by zombie! Number of blasts: 2
    Wed Nov 30 18:33:46 PST 2022
    Wizard threw a blast! Number of blasts: 1

### Task 4

- Implement a Singleton design pattern for wizard so that there can only be one instance of wizard
- Some of my methods, such as run() or deleteZombies() are responsible for too many things
  - It would be improved if they were composed of multiple helper methods
- Entity class and its effect on its subclasses could be better implemented
  - Some methods of the class, such as handleBoundary(), only applies for wizards
  - Blast fields `direction` and `horizontal` could be removed after the addition of the enum `DIRECTION`
