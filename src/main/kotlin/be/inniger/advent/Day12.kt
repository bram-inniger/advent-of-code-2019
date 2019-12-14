package be.inniger.advent

import be.inniger.advent.util.lcm
import kotlin.math.abs

class Day12 {

    fun solveFirst(moonDescriptions: List<String>, nrSteps: Int): Int {
        var moons = moonDescriptions.map { Moon.parse(it) }

        repeat(nrSteps) {
            moons = moons.map { gravity(it, moons) }.map { move(it) }
        }

        return moons.map { energy(it) }.sum()
    }

    fun solveSecond(moonDescriptions: List<String>): Long {
        val originalMoons = moonDescriptions.map { Moon.parse(it) }
        val backAtOrigin = { currentMoons: List<Moon>,
                             positionExtractor: (Position) -> Int,
                             velocityExtractor: (Velocity) -> Int ->
            (::isBackAt)(originalMoons, currentMoons, positionExtractor, velocityExtractor)
        }

        var currentMoons = moonDescriptions.map { Moon.parse(it) }
        var xPeriod = 0
        var yPeriod = 0
        var zPeriod = 0
        var index = 1

        while (true) {
            currentMoons = currentMoons.map { gravity(it, currentMoons) }.map { move(it) }

            if (backAtOrigin(currentMoons, { it.x }, { it.x }) && xPeriod == 0) xPeriod = index
            if (backAtOrigin(currentMoons, { it.y }, { it.y }) && yPeriod == 0) yPeriod = index
            if (backAtOrigin(currentMoons, { it.z }, { it.z }) && zPeriod == 0) zPeriod = index

            if (xPeriod != 0 && yPeriod != 0 && zPeriod != 0) {
                return lcm(xPeriod.toLong(), lcm(yPeriod.toLong(), zPeriod.toLong()))
            }

            index++
        }
    }

    private fun gravity(moon: Moon, moons: List<Moon>): Moon {
        val xChange = gravityChange(moon, moons) { it.x }
        val yChange = gravityChange(moon, moons) { it.y }
        val zChange = gravityChange(moon, moons) { it.z }

        val vel = moon.velocity
        return Moon(moon.position, Velocity(vel.x + xChange, vel.y + yChange, vel.z + zChange))
    }

    private fun move(moon: Moon): Moon {
        val pos = moon.position
        val vel = moon.velocity

        return Moon(Position(pos.x + vel.x, pos.y + vel.y, pos.z + vel.z), vel)
    }

    private fun energy(moon: Moon): Int {
        val pos = moon.position
        val vel = moon.velocity

        return (abs(pos.x) + abs(pos.y) + abs(pos.z)) *
                (abs(vel.x) + abs(vel.y) + abs(vel.z))
    }

    private fun gravityChange(moon: Moon, moons: List<Moon>, positionExtractor: (Position) -> Int) =
        moons.map { it.position.z }
            .map {
                when {
                    it > positionExtractor(moon.position) -> +1
                    it == positionExtractor(moon.position) -> 0
                    it < positionExtractor(moon.position) -> -1
                    else -> error("Not mathematically possible")
                }
            }
            .sum()

    private fun isBackAt(
        pointsOfReturn: List<Moon>,
        currentMoons: List<Moon>,
        positionExtractor: (Position) -> Int,
        velocityExtractor: (Velocity) -> Int
    ) = currentMoons.indices.all {
        positionExtractor(currentMoons[it].position) == positionExtractor(pointsOfReturn[it].position) &&
                velocityExtractor(currentMoons[it].velocity) == velocityExtractor(pointsOfReturn[it].velocity)
    }

    private data class Moon(val position: Position, val velocity: Velocity) {
        companion object {
            private val regex = """^<x=(-?\d+), y=(-?\d+), z=(-?\d+)>$""".toRegex()

            fun parse(moonDescription: String): Moon {
                val (x, y, z) = regex.find(moonDescription)!!.destructured
                return Moon(Position(x.toInt(), y.toInt(), z.toInt()), Velocity(0, 0, 0))
            }
        }
    }

    private data class Position(val x: Int, val y: Int, val z: Int)

    private data class Velocity(val x: Int, val y: Int, val z: Int)
}
