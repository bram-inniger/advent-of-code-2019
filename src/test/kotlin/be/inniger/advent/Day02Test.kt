package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day02Test {

    private val problem = Day02()
    private val input = readInputFile("02")[0].split(",").map { it.toInt() }.toIntArray()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(3500, problem.solveFirst(intArrayOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)))

        assertEquals(2, problem.solveFirst(intArrayOf(1, 0, 0, 0, 99)))

        assertEquals(2, problem.solveFirst(intArrayOf(2, 3, 0, 3, 99)))

        assertEquals(2, problem.solveFirst(intArrayOf(2, 4, 4, 5, 99, 0)))

        assertEquals(30, problem.solveFirst(intArrayOf(1, 1, 1, 4, 99, 5, 6, 0, 99)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(2894520, problem.solveFirst(input, 12, 2))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(9342, problem.solveSecond(input))
    }
}
