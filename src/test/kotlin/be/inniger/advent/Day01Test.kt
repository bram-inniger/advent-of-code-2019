package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day01Test {

    private val problem = Day01()
    private val input = readInputFile("01").map(String::toInt)

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(2, problem.solveFirst(listOf(12)))

        assertEquals(2, problem.solveFirst(listOf(14)))

        assertEquals(654, problem.solveFirst(listOf(1969)))

        assertEquals(33583, problem.solveFirst(listOf(100756)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(3295424, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(2, problem.solveSecond(listOf(14)))

        assertEquals(966, problem.solveSecond(listOf(1969)))

        assertEquals(50346, problem.solveSecond(listOf(100756)))
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(4940279, problem.solveSecond(input))
    }
}
