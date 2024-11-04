package pl.edu.mimuw.blitz

import pl.edu.mimuw.strategies.Strategy

class Player(private val strategy: Strategy) {

    var score = 0
    fun shouldRollAgain(blitzGame: Blitz, role: Role): Boolean =
        strategy.shouldRollAgain(blitzGame, role)

}