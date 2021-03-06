package y2016

import core.AbstractDay
import core.extensions.collect
import core.extensions.toIndex

class Day8(input: List<String>) : AbstractDay(input) {

    private companion object {
        private val MAX_COLUMNS = 50
        private val LAST_COLUMN = MAX_COLUMNS.toIndex()
        private val MAX_ROWS = 6
        private val LAST_ROW = MAX_ROWS.toIndex()
    }

    override fun calculate(): String = input.map { it.toCommand() }
        .collect(newScreen(), { screen, command -> command.exec(screen) })
        .toList()
        .flatMap { it.toList() }
        .filter { it }
        .count()
        .toString()

    override fun calculateAdvanced(): String {
        val screen = input
            .map { it.toCommand() }
            .collect(newScreen(), { screen, command -> command.exec(screen) })

        (0..LAST_ROW).forEach { r ->
            (0..LAST_COLUMN).forEach { c -> print(if (screen[c][r]) "\u2588" else " ") }
            println()
        }
        // To get answer to this one look at console output of the advanced test
        // I am too lazy to parse output
        return "-1"
    }

    private fun newScreen() = Array(MAX_COLUMNS, { Array(MAX_ROWS, { false }) })

    private fun String.toCommand(): Command {
        val tokens = this.split(" ")
        return if (tokens[0] == "rect") {
            val values = tokens[1].split("x")
            Command.TurnOn(values[1].toIndex(), values[0].toIndex())
        } else {
            val value = tokens[2].split("=")[1].toInt()
            val by = tokens[4].toInt()

            if (tokens[1] == "row") {
                Command.RotateRow(value, by)
            } else {
                Command.RotateColumn(value, by)
            }
        }
    }

    private sealed class Command {
        abstract fun exec(screen: Array<Array<Boolean>>)

        class TurnOn(val rows: Int, val cols: Int) : Command() {
            override fun exec(screen: Array<Array<Boolean>>) {
                (0..rows).forEach { r ->
                    (0..cols).forEach { c -> screen[c][r] = true }
                }
            }
        }

        class RotateRow(val row: Int, val by: Int) : Command() {
            override fun exec(screen: Array<Array<Boolean>>) {
                (0..(by.toIndex())).forEach {
                    val lastValue = screen[LAST_COLUMN][row]
                    (LAST_COLUMN downTo 1).forEach { c -> screen[c][row] = screen[c.toIndex()][row] }
                    screen[0][row] = lastValue
                }
            }
        }

        class RotateColumn(val column: Int, val by: Int) : Command() {
            override fun exec(screen: Array<Array<Boolean>>) {
                (0..(by.toIndex())).forEach {
                    val lastValue = screen[column][LAST_ROW]
                    (LAST_ROW downTo 1).forEach { r -> screen[column][r] = screen[column][r.toIndex()] }
                    screen[column][0] = lastValue
                }
            }
        }
    }
}

