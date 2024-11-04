package pl.edu.mimuw

import pl.edu.mimuw.blitz.Blitz
import pl.edu.mimuw.blitz.Player
import pl.edu.mimuw.blitz.Role
import pl.edu.mimuw.dice.Die

const val NUMBER_OF_SIMULATIONS = 10
const val MAX_POINTS = 7

class Simulation(private val die: Die, private val firstPlayer: Player, private val secondPlayer: Player) {
    private var attackerScore = 0
    private var defenderScore = 0

    fun simulate() {
        printIntroduction()

        for (i in 1..NUMBER_OF_SIMULATIONS) {
            when (Blitz(firstPlayer, secondPlayer, die, MAX_POINTS).start()) {
                Role.ATTACKER -> attackerScore++
                else -> defenderScore++
            }
            when (Blitz(secondPlayer, firstPlayer, die, MAX_POINTS).start()) {
                Role.ATTACKER -> attackerScore++
                else -> defenderScore++
            }
        }

        printSummary()
    }

    private fun printIntroduction() {
        println("   =========================================")
        println("   Starting simulation for ${die.faces} face dice")
        println("   =========================================")
    }

    private fun printSummary() {
        println()
        println("   =========================================")
        println("   Simulation ended. Results:")
        println()
        println("   Attacker won ->$attackerScore<- number of times.")
        println("   Defender won ->$defenderScore<- number of times.")
        println("   First player won ->${firstPlayer.score}<- number of times.")
        println("   Second player won ->${secondPlayer.score}<- number of times.")
        println()
        println("   --- Thank you for using Blitz Simulation Tool™ ---")
        println("   =========================================")
    }
}