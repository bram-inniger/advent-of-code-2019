package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day09Test {

    private val problem = Day09()
    private val input = readInputFile("09")[0].split(",").map { it.toLong() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(
            listOf<Long>(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99),
            problem.solveFirst(listOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99))
        )

        assertEquals(16, problem.solveFirst(listOf(1102, 34915192, 34915192, 7, 4, 7, 99, 0))[0].toString().length)

        assertEquals(listOf(1125899906842624), problem.solveFirst(listOf(104, 1125899906842624, 99)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(listOf(3780860499), problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(listOf(33343L), problem.solveSecond(input))
    }
}
