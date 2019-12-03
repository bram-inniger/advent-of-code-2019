package be.inniger.advent

import kotlin.math.abs

class Day03 {

    fun solveFirst(firstWire: List<String>, secondWire: List<String>) =
        followWire(firstWire)
            .intersect(followWire(secondWire))
            .map { abs(it.x) + abs(it.y) }
            .min()!!

    private fun followWire(wire: List<String>): Set<Position> {
        var x = 0
        var y = 0
        val positions = mutableSetOf<Position>()

        for (movement in wire) {
            val direction = movement[0]
            val duration = movement.substring(1).toInt()

            repeat(duration) {
                when (direction) {
                    'R' -> x++
                    'D' -> y--
                    'L' -> x--
                    'U' -> y++
                    else -> throw IllegalArgumentException("Cannot parse direction: $direction")
                }

                positions.add(Position(x, y))
            }
        }

        return positions.toSet()
    }

    private data class Position(val x: Int, val y: Int)
}
