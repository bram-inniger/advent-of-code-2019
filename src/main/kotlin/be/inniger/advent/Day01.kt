package be.inniger.advent

import kotlin.math.floor

class Day01 {

    fun solveFirst(masses: List<Int>) = masses.map { floor(it.toDouble() / 3).toInt() - 2 }.sum()
}
