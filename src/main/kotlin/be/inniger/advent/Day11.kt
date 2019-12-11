package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day11 {

    fun solveFirst(program: List<Long>): Int {
        val computer = IntComputer(program)
        val paintedPanels = mutableSetOf<Coordinate>()
        val whitePanels = mutableSetOf<Coordinate>()

        var colour: Int
        var coordinate = Coordinate(0, 0)
        var direction = Direction.NORTH

        while (true) {
            colour = if (whitePanels.contains(coordinate)) 1 else 0

            val state = computer.runProgram(colour)
            if (state.halted) return paintedPanels.size

            direction = turn(direction, computer.runProgram(-1).output.toInt())

            paintedPanels.add(coordinate)
            if (state.output.toInt() == 0) {
                whitePanels.remove(coordinate)
            } else {
                whitePanels.add(coordinate)
            }

            coordinate = move(coordinate, direction)
        }
    }

    private fun turn(direction: Direction, turnSignal: Int) =
        when (turnSignal) {
            0 -> when (direction) {
                Direction.NORTH -> Direction.WEST
                Direction.EAST -> Direction.NORTH
                Direction.SOUTH -> Direction.EAST
                Direction.WEST -> Direction.SOUTH
            }
            1 -> when (direction) {
                Direction.NORTH -> Direction.EAST
                Direction.EAST -> Direction.SOUTH
                Direction.SOUTH -> Direction.WEST
                Direction.WEST -> Direction.NORTH
            }
            else -> error("Cannot turn on signal: $turnSignal")
        }

    private fun move(position: Coordinate, direction: Direction) =
        when (direction) {
            Direction.NORTH -> Coordinate(position.x, position.y + 1)
            Direction.EAST -> Coordinate(position.x + 1, position.y)
            Direction.SOUTH -> Coordinate(position.x, position.y - 1)
            Direction.WEST -> Coordinate(position.x - 1, position.y)
        }

    private data class Coordinate(val x: Int, val y: Int)

    private enum class Colour {
        BLACK, WHITE
    }

    private enum class Direction {
        NORTH, EAST, SOUTH, WEST
    }
}
