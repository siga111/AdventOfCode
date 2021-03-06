package y2015

import core.AbstractDay

class Day6(input: List<String>) : AbstractDay(input) {

    override fun calculate(): String {
        val bulbGrid = Grid();

        input.forEach { str ->
            val command = str.toCommand()
            when (command.action) {
                Action.ON -> bulbGrid.turnOn(command.range)
                Action.OFF -> bulbGrid.turnOff(command.range)
                Action.TOGGLE -> bulbGrid.toggle(command.range)
            }
        }
        return bulbGrid.grid.count { it > 0 }.toString()
    }

    override fun calculateAdvanced(): String {
        val bulbGrid = Grid();

        input.forEach { str ->
            val command = str.toCommand()
            when (command.action) {
                Action.ON -> bulbGrid.turnUp(command.range)
                Action.OFF -> bulbGrid.turnDown(command.range)
                Action.TOGGLE -> bulbGrid.turnUpDouble(command.range)
            }
        }
        return bulbGrid.grid.sum().toString()
    }


    private enum class Action { ON, OFF, TOGGLE }

    private class Grid {

        var grid: Array<Int> = Array(1000 * 1000, { 0 })

        fun turnOn(range: BulbRange) {
            applyToRange(range) { grid[it] = 1 }
        }

        fun turnOff(range: BulbRange) {
            applyToRange(range) { grid[it] = 0 }
        }

        fun toggle(range: BulbRange) {
            applyToRange(range) { if (grid[it] == 1) grid[it] = 0 else grid[it] = 1 }
        }

        fun turnUp(range: BulbRange) {
            applyToRange(range) { grid[it]++ }
        }

        fun turnDown(range: BulbRange) {
            applyToRange(range) { if (grid[it] > 0) grid[it]-- else grid[it] = 0 }
        }

        fun turnUpDouble(range: BulbRange) {
            applyToRange(range) { grid[it] = grid[it] + 2 }
        }

        fun applyToRange(range: BulbRange, func: (index: Int) -> Unit) {
            for (currentY in range.startY..range.endY)
                for (currentX in range.startX..range.endX)
                    func(currentY * 1000 + currentX)
        }
    }


    private data class Command(val action: Action, val range: BulbRange)

    private fun String.toCommand(): Command = when {
        startsWith("toggle") -> {
            val range = this.substring(6).toBulbRange()
            Command(Action.TOGGLE, range)
        }
        startsWith("turn on") -> {
            val range = this.substring(7).toBulbRange()
            Command(Action.ON, range)
        }
        else -> {
            val range = this.substring(8).toBulbRange()
            Command(Action.OFF, range)
        }
    }


    private data class BulbRange(val startX: Int, val endX: Int, val startY: Int, val endY: Int)

    private fun String.toBulbRange(): BulbRange {
        val items = this.trim().split(' ')
        val start = items[0].split(',').map { s -> s.toInt() }
        val end = items[2].split(',').map { s -> s.toInt() }

        return BulbRange(start[0], end[0], start[1], end[1])
    }

}