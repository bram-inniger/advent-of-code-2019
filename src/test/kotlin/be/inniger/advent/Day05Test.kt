package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day05Test {

    private val problem = Day05()
    private val input = readInputFile("05")[0].split(",").map { it.toInt() }.toIntArray()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals("42", problem.solveFirst(intArrayOf(3, 0, 4, 0, 99), 42))

        assertEquals("", problem.solveFirst(intArrayOf(1002, 4, 3, 4, 33)))

        assertEquals("", problem.solveFirst(intArrayOf(1101, 100, -1, 4, 0)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(11049715, problem.solveFirst(input).toInt())
    }
}
