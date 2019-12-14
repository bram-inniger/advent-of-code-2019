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
        val orig = moonDescriptions.map { Moon.parse(it) }
        var moons = moonDescriptions.map { Moon.parse(it) }
        var xPeriod = 0
        var yPeriod = 0
        var zPeriod = 0

        for (i in 1..Int.MAX_VALUE) {
            moons = moons.map { gravity(it, moons) }.map { move(it) }
            if (moons.indices.all {
                    moons[it].position.x == orig[it].position.x &&
                            moons[it].velocity.x == orig[it].velocity.x &&
                            xPeriod == 0
                }) {
                xPeriod = i
            }
            if (moons.indices.all {
                    moons[it].position.y == orig[it].position.y &&
                            moons[it].velocity.y == orig[it].velocity.y &&
                            yPeriod == 0
                }) {
                yPeriod = i
            }
            if (moons.indices.all {
                    moons[it].position.z == orig[it].position.z &&
                            moons[it].velocity.z == orig[it].velocity.z &&
                            zPeriod == 0
                }) {
                zPeriod = i
            }

            if (xPeriod != 0 && yPeriod != 0 && zPeriod != 0) {
                return lcm(xPeriod.toLong(), lcm(yPeriod.toLong(), zPeriod.toLong()))
            }
        }

        error("")
    }

    // TODO cleanup
    private fun gravity(moon: Moon, moons: List<Moon>): Moon {
        val xChange = moons.map { it.position.x }
            .map {
                when {
                    it > moon.position.x -> +1
                    it == moon.position.x -> 0
                    it < moon.position.x -> -1
                    else -> error("Not mathematically possible")
                }
            }
            .sum()

        val yChange = moons.map { it.position.y }
            .map {
                when {
                    it > moon.position.y -> +1
                    it == moon.position.y -> 0
                    it < moon.position.y -> -1
                    else -> error("Not mathematically possible")
                }
            }
            .sum()

        val zChange = moons.map { it.position.z }
            .map {
                when {
                    it > moon.position.z -> +1
                    it == moon.position.z -> 0
                    it < moon.position.z -> -1
                    else -> error("Not mathematically possible")
                }
            }
            .sum()

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
