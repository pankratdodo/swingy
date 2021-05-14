# Swingy
### GUI programming in Java
## Goals
- First phase for hardcore hipsters that will be console based
- Second phase for regular hipsters, that will also have a simple GUI for taking user
input.
- Respect the `Model-View-Controller` design pattern. (Also using a `Builder` pattern.)
- Automated build with `Maven`.
- Annotation based user input validation.

## Mandatory part
### Gameplay
A player can have multiple heroes of different types. We leave it at you to name the hero types and fine tune the different starting stats between them. When the player starts the game he has 2 options:
- Create a hero
- Select a previously created hero.

In either case, the player can see the hero stats:
- Hero name
- Hero class
- Level
- Experience
- Attack
- Defense
- Hit Points

Hero stats are affected by the hero level and artifacts. There are 3 types of artefacs:
- Weapon - increases the attack
- Armor - increases defense
- Helm - increases hit points

After choosing a hero the actual game begins.

The hero needs to navigate a square map with the size calculated by the formula `(level-1)*5+10` Hero of level 7 will be placed on a 39X39 map.
The initial position of the hero is in the center of the map. He wins the game if he reaches on of the borders of the map. Each turn he can move one position in one of the 4 four directions:
- North
- East
- South
- West

When a map is generated, villains of varying power will be spread randomly over the map. When a hero moves to a position occupied by a villain, the hero has 2 options:
- Fight, which engages him in a battle with the villain
- Run, which gives him a 50 points of damage.

You will need to simulate the battle between the hero and monster and present the
user the outcome of the battle. We leave it at you to find a nice simulation algorythm
that decides based on the hero and monster stats, who will win.

If a hero looses a battle, he dies and also looses the mission.

If a hero wins a battle, he gains:
- Experience points, based on the villain power. Of course, he will level up if he
reaches the next level experience.
- An artifact, which he can keep or leave. Of course, winning a battle doesn’t guarantee that an artefact will be droped and the quality of the artefact also varies
depending on the villain’s strenght.

Leveling up is based on the following formula `level*1000+(level-1)ˆ2 * 450`. So
the necessary experience to level up will follow this pattern:
- Level 1 - 1000 XP
- Level 2 - 2450 XP
- Level 3 - 4800 XP
- Level 4 - 8050 XP
- Level 5 - 12200 XP

### Features
The game can be launched in 2 modes as described below:
- GUI
- Console

A user’s heroes and their state will be preserved, when the user exits the game, in a
text file. When starting the game, your program will load the heroes from this file.

### Validation
Any abnormal user input is not allow to disrupt the game behaviour. Validation
failure will be highlighted to the user.

## Bonus part
- Game persist the user’s heroes in a relational database, instead of a text file.
- Type `i` in gui mode to show info.
- User can see a easter eggs at the end of game.
