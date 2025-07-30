# These are projects I made during a Kotlin course at the MIM Faculty of the University of Warsaw.

## SMD

Simple Markdown (SMD) is a simplified subset of Markdown with essential syntax elements, including headers, paragraphs,
line breaks, lists, horizontal lines, emphasis (italic, bold, bold-italic), inline code, and code blocks.

This Kotlin mini-library provides a DSL to create SMD documents. Running the program with a file path as an argument
will generate an SMD document matching the content in example.md and save it to the specified path.

## Blitz

Blitz is a two-player dice game where one player is the attacker, and the other is the defender, using a single
polyhedral die (e.g., six-sided, twenty-sided). Each round, players roll the die in turn—first the attacker, then the
defender. The attacker may choose to re-roll once if desired, followed by the defender. The player with the higher roll
wins the round; ties go to the attacker. The first player to reach a predetermined number of points (e.g., 3) wins the
game. Roles do not change during a game, but switch in subsequent games for simulation purposes.

The game is implemented in Kotlin, supporting different dice types and allowing players to strategize based on their
role, the current score, and the number of points needed to win. Run simulations for different dice types and display
logs of game progress and end-of-game statistics, including win percentages by role and player.

## Seega

Seega is a traditional two-player board game from Egypt, played on a square grid with an odd number of spaces. The
game's rules can be found [here](http://www.cyningstan.com/game/120/seega).

This project implements Seega in Kotlin with a console-based text interface, allowing two players to play on a selected
board size (5x5, 7x7, or 9x9). The game does not include AI or online play.

Players take turns deploying or moving pieces, with the board displayed in the console after each move. Commands
include:

Deploy Phase: deploy <square> to place a piece (e.g., deploy b3)
Move Phase: move <square> <direction> to move a piece (e.g., move d2 up)
The program provides relevant game state messages, turn information, and end-game results.
