package pl.edu.mimuw.seega

import pl.edu.mimuw.seega.exceptions.CommandSyntaxException

class CommandInterpreter {
    private val deployRegex = Regex("deploy (\\w)(\\d)")
    private val moveRegex = Regex("move (\\w)(\\d) (up|down|right|left)")
    fun deployCommand(command: String, color: Color): DeployCommand {
        val matchResult = deployRegex.find(command)
        if (matchResult != null) {
            println(matchResult)
            val (letter, number) = matchResult.destructured
            return DeployCommand(number.toInt(), (letter[0].code - 96), color)
        } else {
            throw CommandSyntaxException("Error in command syntax (deploy {columnLetter}{rowNumber})")
        }
    }

    fun moveCommand(command: String, color: Color): MoveCommand {
        val matchResult = moveRegex.find(command)
        if (matchResult != null) {
            val (character, number, side) = matchResult.destructured
            return MoveCommand(number.toInt(), (character[0].code - 96), Direction.valueOf(side.uppercase()), color)
        } else {
            throw CommandSyntaxException("Error in command syntax (move {columnLetter}{rowNumber} {direction})")
        }
    }

    data class MoveCommand(val x: Int, val y: Int, val direction: Direction, val color: Color)
    data class DeployCommand(val x: Int, val y: Int, val color: Color)

}