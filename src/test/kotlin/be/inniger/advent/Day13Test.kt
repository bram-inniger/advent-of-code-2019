package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day13Test {

    private val problem = Day13()
    private val input = readInputFile("13")[0].split(",").map { it.toLong() }

    @Test
    fun validateFirstSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(273, problem.solveFirst(input))
    }
}
