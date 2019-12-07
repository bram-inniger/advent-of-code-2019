package be.inniger.advent

import kotlin.math.floor

class Day01 {

    fun solveFirst(masses: List<Int>) = masses.map { calculateFuel(it) }.sum()

    fun solveSecond(masses: List<Int>) = masses.map { calculateCompoundFuel(it) }.sum()

    private fun calculateFuel(mass: Int) = floor(mass.toDouble() / 3).toInt() - 2

    private tailrec fun calculateCompoundFuel(mass: Int, acc: Int = 0): Int {
        val fuel = calculateFuel(mass)

        return if (fuel <= 0) acc
        else calculateCompoundFuel(fuel, acc + fuel)
    }
}
