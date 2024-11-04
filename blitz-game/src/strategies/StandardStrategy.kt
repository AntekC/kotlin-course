package pl.edu.mimuw.strategies

import pl.edu.mimuw.blitz.Blitz
import pl.edu.mimuw.blitz.Role

class StandardStrategy : Strategy {
    override fun shouldRollAgain(blitzGame: Blitz, role: Role): Boolean =
        if (role == Role.ATTACKER) {
            blitzGame.currentDefendingPlayerThrow > blitzGame.currentAttackingPlayerThrow
        } else {
            blitzGame.currentDefendingPlayerThrow <= blitzGame.currentAttackingPlayerThrow
        }
}
