# My Personal Project

## A Single-Panel Game Survival Game

### What will the application do?
This application is a Zelda inspired survival-type game where the player is placed in a single panel setting, 
and you must destroy incoming enemies. Enemies are basic—only approach you and can damage you upon contact.
The player has a health bar, and everytime the enemy contacts the player, the player's health bar decreases.
After each interval of time, the enemies come faster, making it harder to survive. 
Game is over when player’s health bar is at zero.

Two types of classes the player can pick, each which has a basic and super attack. Basic attacks can be used whenever
the user presses the designated key, but super attacks have a cool down period until the user can use it again
(eg: at the start of the game user has to wait 10 seconds to use super attack, and must wait 10 seconds after each
subsequent use)

- **Melee fighter** (Warrior)
   - Basic attack: short ranged slice that destroys enemies on impact
   - Super attack: teleports a certain distance away and destroys enemies within the teleportation path
- **Long-ranged fighter** (Wizard)
    - Basic attack: long ranged blast that destroys enemies on impact
    - Super attack: energy blast that flies in all directions and destroys all enemies in viscinity

### Who will use it?
As described, this application is a game, thus will be played by anyone that is interested in retro-style games
with a survival component.

### Why is this project of interest to you?
Although I have not been programming for long, everytime I think of a project to do, my mind always gravitates towards 
designing a game. For example, during CPSC 110 I would program games in my spare time and in the summer I programmed 
snake in JS + HTML + CSS. I want to challenge myself by making a game I would personally enjoy, thus why my game has 
many interactive components.

## User Stories

- As a user, I want to have designated buttons that correspond to basic and super attacks
- As a user, I want to be able to choose an easy, medium and hard mode that changes the rate to which enemies increase their speed
- As a user, I want to be able to see the time remaining until I can use the super attack
- As a user, I want to be able to see my health bar