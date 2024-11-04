package seega

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pl.edu.mimuw.seega.Color
import pl.edu.mimuw.seega.CommandInterpreter
import pl.edu.mimuw.seega.Direction
import pl.edu.mimuw.seega.exceptions.CommandSyntaxException
import kotlin.test.assertFailsWith

class CommandInterpreterTest {

    val commandInterpreter = CommandInterpreter()

    @Test
    fun `should return correct deploy command`() {
        val command = "deploy b1"

        val result = commandInterpreter.deployCommand(command, Color.WHITE)

        assertEquals(result, CommandInterpreter.DeployCommand(1, 2, Color.WHITE))
    }

    @Test
    fun `should return correct move command`() {
        val command = "move b1 down"

        val result = commandInterpreter.moveCommand(command, Color.WHITE)

        assertEquals(result, CommandInterpreter.MoveCommand(1, 2, Direction.DOWN, Color.WHITE))
    }

    @Test
    fun `should throw exception when wrong syntax of move command`() {
        val command = "move 13as"

        assertFailsWith<CommandSyntaxException> { commandInterpreter.moveCommand(command, Color.WHITE) }
    }

    @Test
    fun `should throw exception when wrong syntax of deploy command`() {
        val command = "deaaploy a1"

        assertFailsWith<CommandSyntaxException> { commandInterpreter.deployCommand(command, Color.WHITE) }
    }

}