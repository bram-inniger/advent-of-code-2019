package be.inniger.advent

import kotlin.math.abs
import kotlin.Int as Coordinate
import kotlin.Int as StepCount

class Day03 {

    fun solveFirst(firstWire: List<String>, secondWire: List<String>) =
        followWire(firstWire).keys
            .intersect(followWire(secondWire).keys)
            .map { abs(it.x) + abs(it.y) }
            .min()!!

    fun solveSecond(firstWire: List<String>, secondWire: List<String>): StepCount {
        val firstPositions = followWire(firstWire)
        val secondPositions = followWire(secondWire)

        return firstPositions.keys
            .intersect(secondPositions.keys)
            .map { firstPositions.getValue(it) + secondPositions.getValue(it) }
            .min()!!
    }

    private fun followWire(wire: List<String>): Map<Position, StepCount> {
        var x = 0
        var y = 0
        var steps = 0
        val positions = mutableMapOf<Position, StepCount>()

        for (movement in wire) {
            val direction = movement[0]
            val duration = movement.substring(1).toInt()

            repeat(duration) {
                when (direction) {
                    'R' -> x++
                    'D' -> y--
                    'L' -> x--
                    'U' -> y++
                    else -> error("Cannot parse direction: $direction")
                }

                positions.putIfAbsent(Position(x, y), ++steps)
            }
        }

        return positions.toMap()
    }

    private data class Position(val x: Coordinate, val y: Coordinate)
}
