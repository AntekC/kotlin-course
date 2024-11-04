package pl.edu.mimuw.strategies

import pl.edu.mimuw.blitz.Blitz
import pl.edu.mimuw.blitz.Role
import kotlin.random.Random

class RandomStrategy : Strategy {
    override fun shouldRollAgain(blitzGame: Blitz, role: Role): Boolean = Random.nextBoolean()

}