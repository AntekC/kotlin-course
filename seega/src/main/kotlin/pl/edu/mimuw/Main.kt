package pl.edu.mimuw

import pl.edu.mimuw.seega.SeegaBoard
import pl.edu.mimuw.seega.SeegaGame

fun main() {
    val game = SeegaGame(SeegaBoard(size = 5))
    game.startGame()
}
