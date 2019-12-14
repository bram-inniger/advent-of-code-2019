package be.inniger.advent

import be.inniger.advent.util.gcd
import kotlin.math.atan2

class Day10 {

    data class Asteroid(val x: Int, val y: Int)

    fun solveFirst(grid: List<String>): String {
        val asteroids = parseGrid(grid)

        return asteroids.map { findVisibleAsteroids(it, asteroids) }
            .maxBy { it.asteroidsVisible }!!
            .toString()
    }

    fun solveSecond(grid: List<String>, station: Asteroid) =
        parseGrid(grid)
            .filter { it != station }
            .map { Asteroid(it.x - station.x, it.y - station.y) }
            .groupBy { angle(it) }
            .mapValues { asteroidsInOneLine ->
                asteroidsInOneLine.value.sortedBy { asteroid -> gcd(asteroid.x, asteroid.y) }
            }
            .entries
            .flatMap {
                it.value.mapIndexed { layer, asteroid -> RelativeLocation(asteroid, layer, it.key) }
            }
            .sortedWith(compareBy({ it.layer }, { it.angle }))
            .map { Asteroid(it.asteroid.x + station.x, it.asteroid.y + station.y) }

    private fun parseGrid(gridDescription: List<String>) =
        gridDescription.indices.flatMap { y ->
            gridDescription[y].indices
                .filter { x -> gridDescription[y][x] == '#' }
                .map { x -> Asteroid(x, y) }
        }

    private fun findVisibleAsteroids(asteroid: Asteroid, asteroids: List<Asteroid>) =
        asteroids.asSequence()
            .filter { it != asteroid }
            .map { Asteroid(it.x - asteroid.x, it.y - asteroid.y) }
            .map { val gcd = gcd(it.x, it.y); Asteroid(it.x / gcd, it.y / gcd) }
            .distinct()
            .count()
            .let { Visibility(asteroid, it) }

    private fun angle(asteroid: Asteroid) = -atan2(asteroid.x.toDouble(), asteroid.y.toDouble())

    private data class Visibility(val asteroid: Asteroid, val asteroidsVisible: Int)

    private data class RelativeLocation(val asteroid: Asteroid, val layer: Int, val angle: Double)
}
