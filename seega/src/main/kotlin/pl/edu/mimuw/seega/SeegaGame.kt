package pl.edu.mimuw.seega

import pl.edu.mimuw.seega.exceptions.CommandSyntaxException
import pl.edu.mimuw.seega.exceptions.WrongMoveException
import java.util.*

class SeegaGame(private val board: SeegaBoard) {

    private val commandInterpreter = CommandInterpreter()

    fun startGame() {
        println("WELCOME TO SEEGA GAME!")
        repeat(4) { println() }

        deployPhase()
        movePhase()

        board.printBoard()
        println(board.winnerMessage())
        println("GAME OVER! Thank you for playing Seega Kotlin Edition")
    }

    private fun deployPhase() {
        var turn = 1
        for (i in 1..((board.size * board.size) - 1) / 2) {
            val currentPlayerColor = if (turn == 1) Color.WHITE else Color.BLACK

            repeat(2) {
                board.printBoard()
                println("Player playing ${currentPlayerColor.name} please put Piece on board")
                deployPiece(currentPlayerColor)
            }

            turn = (turn + 1) % 2
        }
    }

    private fun movePhase() {
        var turn = 1
        while (!board.isGameOver) {
            val currentPlayerColor = if (turn == 1) Color.WHITE else Color.BLACK

            if (board.hasNextPlayerAvailableMove) {
                while (!board.isNextPlayerMove) {
                    board.printBoard()
                    println("Player playing ${currentPlayerColor.name} please make move: \n")
                    movePiece(currentPlayerColor)
                }
            } else {
                println("Player playing ${currentPlayerColor.name} has no moves.\n")
                board.hasNextPlayerAvailableMove = true
            }

            board.isNextPlayerMove = false
            turn = (turn + 1) % 2
        }
    }


    private fun movePiece(color: Color) {
        val reader = Scanner(System.`in`)
        while (true) {
            try {
                board.movePiece(commandInterpreter.moveCommand(reader.nextLine(), color))
                break
            } catch (e: CommandSyntaxException) {
                board.printBoard()
                println("Wrong syntax! The syntax for move command is: move {column}{row} {direction}")
                println("Player playing ${color.name} please make move")
            } catch (e: WrongMoveException) {
                board.printBoard()
                println("Wrong move! Reason: ${e.message}")
                println("Player playing ${color.name} please make move")
            }
        }

    }

    private fun deployPiece(color: Color) {
        val reader = Scanner(System.`in`)
        while (true) {
            try {
                board.deployPieces(commandInterpreter.deployCommand(reader.nextLine(), color))
                break
            } catch (e: CommandSyntaxException) {
                board.printBoard()
                println("Wrong syntax! The syntax for deploy command is: deploy {column}{row}")
                println("Player playing ${color.name} please put Pieces on board")
            } catch (e: WrongMoveException) {
                board.printBoard()
                println("Wrong move! Reason: ${e.message}")
                println("Player playing ${color.name} please deploy a Piece")
            }
        }
    }

}