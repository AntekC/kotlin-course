package seega

import pl.edu.mimuw.seega.exceptions.WrongMoveException
import org.junit.jupiter.api.Assertions.assertFalse
import pl.edu.mimuw.seega.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertFailsWith

class SeegaBoardTest {

    private val testBoard = SeegaBoard(7)

    @Test
    fun `should correctly deploy Piece on the board`() {
        val deployCommand = CommandInterpreter.DeployCommand(1, 2, Color.WHITE)

        testBoard.deployPieces(deployCommand)

        assertContains(testBoard.Pieces, Piece(1, 2, Color.WHITE))
    }

    @Test
    fun `should correctly move Piece on the board`() {
        val moveCommand = CommandInterpreter.MoveCommand(1, 2, Direction.DOWN, Color.WHITE)
        testBoard.Pieces.add(Piece(1, 2, Color.WHITE))

        testBoard.movePiece(moveCommand)

        assertContains(testBoard.Pieces, Piece(2, 2, Color.WHITE))
    }

    @Test
    fun `should correctly remove Piece after capture`() {
        val moveCommand1 = CommandInterpreter.MoveCommand(2, 4, Direction.UP, Color.WHITE)
        testBoard.Pieces.add(Piece(1, 2, Color.WHITE))
        testBoard.Pieces.add(Piece(1, 3, Color.BLACK))
        testBoard.Pieces.add(Piece(2, 4, Color.WHITE))

        val moveCommand2 = CommandInterpreter.MoveCommand(5, 3, Direction.LEFT, Color.WHITE)
        testBoard.Pieces.add(Piece(3, 2, Color.WHITE))
        testBoard.Pieces.add(Piece(4, 2, Color.BLACK))
        testBoard.Pieces.add(Piece(5, 3, Color.WHITE))

        testBoard.movePiece(moveCommand1)
        testBoard.movePiece(moveCommand2)

        assertFalse(testBoard.Pieces.contains(Piece(1, 3, Color.BLACK)))
        assertFalse(testBoard.Pieces.contains(Piece(4, 2, Color.BLACK)))
    }


    @Test
    fun `should throw exception when deploy on star field`() {
        val deployCommand = CommandInterpreter.DeployCommand(4, 4, Color.WHITE)

        assertFailsWith<WrongMoveException> { testBoard.deployPieces(deployCommand) }
    }

    @Test
    fun `should throw exception when deploy on not empty field`() {
        val deployCommand = CommandInterpreter.DeployCommand(1, 2, Color.WHITE)
        testBoard.Pieces.add(Piece(1, 2, Color.BLACK))

        assertFailsWith<WrongMoveException> { testBoard.deployPieces(deployCommand) }
    }

    @Test
    fun `should throw exception when deploy on out of boundaries field`() {
        val deployCommand1 = CommandInterpreter.DeployCommand(0, 0, Color.WHITE)
        val deployCommand2 = CommandInterpreter.DeployCommand(10, 10, Color.WHITE)

        assertFailsWith<WrongMoveException> { testBoard.deployPieces(deployCommand1) }
        assertFailsWith<WrongMoveException> { testBoard.deployPieces(deployCommand2) }
    }

    @Test
    fun `should throw exception when no Piece on selected field`() {
        val moveCommand = CommandInterpreter.MoveCommand(2, 2, Direction.DOWN, Color.WHITE)
        testBoard.Pieces.add(Piece(1, 2, Color.WHITE))

        assertFailsWith<WrongMoveException> { testBoard.movePiece(moveCommand) }
    }

    @Test
    fun `should throw exception when out of boundaries move`() {
        val moveCommand = CommandInterpreter.MoveCommand(1, 1, Direction.UP, Color.WHITE)
        testBoard.Pieces.add(Piece(1, 1, Color.WHITE))

        assertFailsWith<WrongMoveException> { testBoard.movePiece(moveCommand) }
    }

    @Test
    fun `should throw exception when move to not empty field`() {
        val moveCommand = CommandInterpreter.MoveCommand(1, 1, Direction.DOWN, Color.WHITE)
        testBoard.Pieces.add(Piece(1, 1, Color.WHITE))
        testBoard.Pieces.add(Piece(2, 1, Color.WHITE))

        assertFailsWith<WrongMoveException> { testBoard.movePiece(moveCommand) }
    }

}