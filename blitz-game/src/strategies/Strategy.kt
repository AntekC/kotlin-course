package pl.edu.mimuw.strategies

import pl.edu.mimuw.blitz.Blitz
import pl.edu.mimuw.blitz.Role

interface Strategy {
    fun shouldRollAgain(blitzGame: Blitz, role: Role): Boolean
}