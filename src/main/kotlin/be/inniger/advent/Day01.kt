package be.inniger.advent

import kotlin.math.floor

class Day01 {

    fun solveFirst(masses: List<Int>) =
        masses.map { it.toDouble() }
            .map { it / 3 }
            .map { floor(it) }
            .map { it.toInt() }
            .map { it - 2 }
            .sum()
}
