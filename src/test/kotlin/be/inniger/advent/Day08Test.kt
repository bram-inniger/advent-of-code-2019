package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day08Test {

    private val problem = Day08()
    private val input = readInputFile("08")[0]

    @Test
    fun validateFirstSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(1560, problem.solveFirst(input))
    }
}
