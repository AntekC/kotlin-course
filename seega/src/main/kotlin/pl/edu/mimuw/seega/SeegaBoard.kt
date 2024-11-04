package pl.edu.mimuw.seega

import pl.edu.mimuw.seega.exceptions.WrongMoveException

class SeegaBoard(val size: Int) {

    val Pieces: MutableList<Piece> = mutableListOf()
    var isNextPlayerMove: Boolean = false
    var hasNextPlayerAvailableMove: Boolean = true
    var isGameOver: Boolean = false
    var movesSinceCapture: Int = 0

    fun printBoard() {
        print("    ")
        for (j in 1..size) {
            print(" ${(j + 96).toChar()}  ")

        }
        println("")
        for (i in 1..size) {
            print("   +")
            for (j in 1..size) {
                print("---+")

            }
            println("")
            print("$i  |")
            for (j in 1..size) {
                if (Pieces.contains(Piece(i, j, Color.WHITE))) {
                    print(" W |")
                } else if (Pieces.contains(Piece(i, j, Color.BLACK))) {
                    print(" B |")
                } else if (i == size / 2 + 1 && j == size / 2 + 1) {
                    print(" * |")
                } else {
                    print("   |")
                }
            }
            println("")
        }
        print("   +")
        for (j in 1..size) {
            print("---+")

        }
        println()
    }

    fun deployPieces(command: CommandInterpreter.DeployCommand) {
        checkIfCorrectDeploy(command)
        Pieces.add(Piece(command.x, command.y, command.color))
    }


    fun movePiece(command: CommandInterpreter.MoveCommand) {
        checkIfCorrectMove(command)
        Pieces.remove(Piece(command.x, command.y, command.color))
        isNextPlayerMove = true
        movesSinceCapture++

        val newCoordinates: Pair<Int, Int> = directionToCoordinates(command.x, command.y, command.direction)
        Pieces.add(Piece(newCoordinates.first, newCoordinates.second, command.color))
        checkForCapture(newCoordinates.first, newCoordinates.second, command.color)
        checkIfAvailableMovesForEnemy(command.color)
        checkIfEndOfGame()
    }

    fun winnerMessage(): String {
        val colorCount = Pieces.groupingBy { it.color }.eachCount()
        val whitePieces = colorCount.getOrDefault(Color.WHITE, 0)
        val blackPieces = colorCount.getOrDefault(Color.BLACK, 0)

        return if (whitePieces > blackPieces) {
            "White wins!"
        } else if (whitePieces < blackPieces) {
            "Black wins!"
        } else {
            "Tie!"
        }
    }

    private fun checkIfEndOfGame() {
        if (movesSinceCapture > 20 || Pieces.none { it.color == Color.WHITE } || Pieces.none { it.color == Color.BLACK }) {
            isNextPlayerMove = true
            isGameOver = true
        }
    }

    private fun checkIfAvailableMovesForEnemy(currentPlayerColor: Color) {
        val enemyColor = if (currentPlayerColor == Color.WHITE) Color.BLACK else Color.WHITE
        hasNextPlayerAvailableMove = false
        Pieces.filter { it.color == enemyColor }.forEach { Piece ->
            checkIfPieceHasMove(Piece)
        }
    }

    private fun checkIfPieceHasMove(Piece: Piece) {
        if (checkIfEmptyField(Piece.x - 1, Piece.y)
            || checkIfEmptyField(Piece.x + 1, Piece.y)
            || checkIfEmptyField(Piece.x, Piece.y - 1)
            || checkIfEmptyField(Piece.x, Piece.y + 1)
        ) {
            hasNextPlayerAvailableMove = true
        }
    }

    private fun checkIfEmptyField(x: Int, y: Int): Boolean {
        if (x in 1..size && y in 1..size) {
            return !Pieces.any { it.x == x && it.y == y }
        }
        return false
    }


    private fun checkForCapture(x: Int, y: Int, currentPlayerColor: Color) {
        checkIfSurrounded(x + 1, y, x, y, currentPlayerColor)
        checkIfSurrounded(x - 1, y, x, y, currentPlayerColor)
        checkIfSurrounded(x, y + 1, x, y, currentPlayerColor)
        checkIfSurrounded(x, y - 1, x, y, currentPlayerColor)
    }


    private fun isCurrentPieceSurrounding(
        firstX: Int,
        firstY: Int,
        secondX: Int,
        secondY: Int,
        currentX: Int,
        currentY: Int,
        currentPlayerColor: Color
    ): Boolean {
        return Pieces.contains(Piece(firstX, firstY, currentPlayerColor)) && Pieces.contains(
            Piece(
                secondX,
                secondY,
                currentPlayerColor
            )
        )
                && ((firstX == currentX && firstY == currentY) || (secondX == currentX && secondY == currentY))
    }

    private fun checkIfSurrounded(
        checkingX: Int,
        checkingY: Int,
        currentX: Int,
        currentY: Int,
        currentPlayerColor: Color
    ) {
        val enemyColor = if (currentPlayerColor == Color.WHITE) Color.BLACK else Color.WHITE
        if (checkingX in 1..size && checkingY in 1..size && !(checkingX == size / 2 + 1 && checkingY == size / 2 + 1)) {
            if (Pieces.contains(Piece(checkingX, checkingY, enemyColor))) {
                if (isCurrentPieceSurrounding(
                        checkingX + 1,
                        checkingY,
                        checkingX - 1,
                        checkingY,
                        currentX,
                        currentY,
                        currentPlayerColor
                    )
                ) {
                    Pieces.remove(Piece(checkingX, checkingY, enemyColor))
                    isNextPlayerMove = false
                    movesSinceCapture = 0
                }

                if (isCurrentPieceSurrounding(
                        checkingX,
                        checkingY + 1,
                        checkingX,
                        checkingY - 1,
                        currentX,
                        currentY,
                        currentPlayerColor
                    )
                ) {
                    Pieces.remove(Piece(checkingX, checkingY, enemyColor))
                    isNextPlayerMove = false
                    movesSinceCapture = 0
                }
            }
        }
    }

    private fun checkIfCorrectMove(command: CommandInterpreter.MoveCommand) {
        if (!Pieces.contains(Piece(command.x, command.y, command.color))) {
            throw WrongMoveException("There is no Piece of your color on this field")
        }
        val newCoordinates = directionToCoordinates(command.x, command.y, command.direction)

        checkIfOutOfBoundry(newCoordinates.first, newCoordinates.second)
        checkIfNoPieces(newCoordinates.first, newCoordinates.second)
    }

    private fun checkIfCorrectDeploy(command: CommandInterpreter.DeployCommand) {
        if (command.x == size / 2 + 1 && command.y == size / 2 + 1) {
            throw WrongMoveException("Can't put Piece on star field")
        }
        checkIfOutOfBoundry(command.x, command.y)
        checkIfNoPieces(command.x, command.y)
    }

    private fun checkIfOutOfBoundry(x: Int, y: Int) {
        if (x !in 1..size || y !in 1..size) {
            throw WrongMoveException("Out of boundary $x $y")
        }
    }

    private fun checkIfNoPieces(x: Int, y: Int) {
        if (Pieces.any { it.x == x && it.y == y }) {
            throw WrongMoveException("There is a Piece on this field")
        }
    }

    private fun directionToCoordinates(x: Int, y: Int, direction: Direction): Pair<Int, Int> =
        when (direction) {
            Direction.RIGHT -> {
                Pair(x, y + 1)
            }

            Direction.LEFT -> {
                Pair(x, y - 1)
            }

            Direction.UP -> {
                Pair(x - 1, y)
            }

            Direction.DOWN -> {
                Pair(x + 1, y)
            }
        }

}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT

}

enum class Color {
    WHITE,
    BLACK
}
