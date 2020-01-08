package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day13 {

    fun solveFirst(program: List<Long>): Int {
        val screen = runUntilHalt(IntComputer(program))
            .chunked(3)
            .map { Point(it[0], it[1]) to Tile.parse(it[2]) }
            .toMap()

        return screen
            .filter { it.value == Tile.BLOCK }
            .count()
    }

    private tailrec fun runUntilHalt(
        computer: IntComputer, outputs: List<Int> = emptyList(), halted: Boolean = false
    ): List<Int> =
        if (halted) outputs
        else {
            val state = computer.runProgram()
            val newOutput = if (!state.halted) outputs + state.output.toInt() else outputs
            runUntilHalt(computer, newOutput, state.halted)
        }

    @Suppress("unused")
    private fun printScreen(screen: Map<Point, Tile>): String {
        val points = screen.keys

        val minX = points.minBy { it.x }!!.x
        val maxX = points.maxBy { it.x }!!.x
        val minY = points.minBy { it.y }!!.y
        val maxY = points.maxBy { it.y }!!.y

        return (minY..maxY).joinToString("\n") { y ->
            (minX..maxX).map { x -> screen.getOrDefault(Point(x, y), Tile.EMPTY) }.map { it.icon }.joinToString("")
        }
    }

    private data class Point(val x: Int, val y: Int)

    private enum class Tile(val icon: Char, @Suppress("UNUSED_PARAMETER") description: String) {
        EMPTY(' ', "An empty tile. No game object appears in this tile."),
        WALL('#', "A wall tile. Walls are indestructible barriers."),
        BLOCK('=', "A block tile. Blocks can be broken by the ball."),
        PADDLE('_', "A horizontal paddle tile. The paddle is indestructible."),
        BALL('o', "A ball tile. The ball moves diagonally and bounces off objects.");

        companion object {
            fun parse(tile: Int) = when (tile) {
                0 -> EMPTY
                1 -> WALL
                2 -> BLOCK
                3 -> PADDLE
                4 -> BALL
                else -> error("Cannot parse tile: $tile")
            }
        }
    }
}
