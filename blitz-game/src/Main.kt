package pl.edu.mimuw

import pl.edu.mimuw.blitz.Player
import pl.edu.mimuw.dice.Die
import pl.edu.mimuw.strategies.RandomStrategy
import pl.edu.mimuw.strategies.StandardStrategy

fun main() {
    val firstPlayer = Player(RandomStrategy())
    val secondPlayer = Player(StandardStrategy())

    Simulation(Die(6), firstPlayer, secondPlayer).simulate()

    Simulation(Die(10), firstPlayer, secondPlayer).simulate()
}
