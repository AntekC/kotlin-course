package pl.edu.mimuw.blitz

import pl.edu.mimuw.dice.Die

class Blitz(
    private val attackingPlayer: Player,
    private val defendingPlayer: Player,
    private val dice: Die,
    private val maxPoints: Int = 3
) {

    private var roundCounter: Int = 1
    private var attackingPlayerScore: Int = 0
    private var defendingPlayerScore: Int = 0
    var currentDefendingPlayerThrow: Int = 0
    var currentAttackingPlayerThrow: Int = 0


    fun start(): Role {
        println()
        println("========= New Blitz Game Started ===========")
        while (attackingPlayerScore != maxPoints && defendingPlayerScore != maxPoints) {
            currentAttackingPlayerThrow = dice.roll()
            currentDefendingPlayerThrow = dice.roll()
            if (attackingPlayer.shouldRollAgain(this, Role.ATTACKER)) {
                currentAttackingPlayerThrow = dice.roll()
            }
            if (defendingPlayer.shouldRollAgain(this, Role.DEFENDER)) {
                currentDefendingPlayerThrow = dice.roll()
            }

            println("========= Round ${roundCounter++} ended. ===========")

            if (currentAttackingPlayerThrow >= currentDefendingPlayerThrow) {
                attackingPlayerScore++
                println("Attacking player won round.")
            } else {
                defendingPlayerScore++
                println("Defending player won round.")
            }

            println("Current score:")
            println("   Attacking player: $attackingPlayerScore")
            println("   Defending player: $defendingPlayerScore")
            println("====================================")
            println()
        }

        println("============= GAME OVER ====================")
        return if (attackingPlayerScore > defendingPlayerScore) {
            println("   Attacking player won!")
            println("============================================")

            attackingPlayer.score++
            Role.ATTACKER
        } else {
            println("   Defending player won!")
            println("============================================")

            defendingPlayer.score++
            Role.DEFENDER
        }
    }

}