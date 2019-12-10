package be.inniger.advent

import kotlin.math.abs

class Day10 {

    fun solveFirst(grid: List<String>): Int {
        val asteroids = parseGrid(grid)

        return asteroids.map { findVisibleAsteroids(it, asteroids) }
            .max()!!
    }

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

    private fun gcd(a: Int, b: Int): Int {
        if (a == 0 && b == 0) return 1

        var absA = abs(a)
        var absB = abs(b)

        if (absA == 0) return absB
        if (absB == 0) return absA

        while (absA != absB) {
            if (absA > absB) absA -= absB
            else absB -= absA
        }

        return absA
    }

    private data class Asteroid(val x: Int, val y: Int)
}
