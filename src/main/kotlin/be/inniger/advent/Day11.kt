package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day11 {

    fun solveFirst(program: List<Long>) = solve(program).paintedPanels.size

    fun solveSecond(program: List<Long>) = printRegistration(solve(program, mutableSetOf(Coordinate(0, 0))).whitePanels)

    private fun solve(program: List<Long>, whitePanels: MutableSet<Coordinate> = mutableSetOf()): PaintJob {
        val computer = IntComputer(program)
        val paintedPanels = mutableSetOf<Coordinate>()

        var colour: Colour
        var coordinate = Coordinate(0, 0)
        var direction = Direction.NORTH

        while (true) {
            colour = readColour(coordinate, whitePanels)

            val state = computer.runProgram(colour.number)
            if (state.halted) return PaintJob(paintedPanels, whitePanels)

            direction = turn(direction, computer.runProgram(-1).output.toInt())

            paintedPanels.add(coordinate)
            if (readColour(state) == Colour.BLACK) whitePanels.remove(coordinate)
            else whitePanels.add(coordinate)

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

    private fun move(coordinate: Coordinate, direction: Direction) =
        when (direction) {
            Direction.NORTH -> Coordinate(coordinate.x, coordinate.y - 1)
            Direction.EAST -> Coordinate(coordinate.x + 1, coordinate.y)
            Direction.SOUTH -> Coordinate(coordinate.x, coordinate.y + 1)
            Direction.WEST -> Coordinate(coordinate.x - 1, coordinate.y)
        }

    private fun readColour(coordinate: Coordinate, whitePanels: Set<Coordinate>) =
        if (whitePanels.contains(coordinate)) Colour.WHITE
        else Colour.BLACK

    private fun readColour(state: IntComputer.State) =
        if (state.output == 0L) Colour.BLACK
        else Colour.WHITE

    private fun printRegistration(whitePanels: Set<Coordinate>): String {
        val minX = whitePanels.minBy { it.x }!!.x
        val maxX = whitePanels.maxBy { it.x }!!.x
        val minY = whitePanels.minBy { it.y }!!.y
        val maxY = whitePanels.maxBy { it.y }!!.y

        return (minY..maxY).joinToString("\n") { y ->
            (minX..maxX).map { x -> if (whitePanels.contains(Coordinate(x, y))) '#' else ' ' }.joinToString("")
        }
    }

    private data class Coordinate(val x: Int, val y: Int)

    private data class PaintJob(val paintedPanels: Set<Coordinate>, val whitePanels: Set<Coordinate>)

    private enum class Colour(val number: Int) {
        BLACK(0),
        WHITE(1)
    }

    private enum class Direction {
        NORTH, EAST, SOUTH, WEST
    }
}
