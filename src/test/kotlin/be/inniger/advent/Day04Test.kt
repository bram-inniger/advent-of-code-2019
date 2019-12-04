package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day04Test {

    private val problem = Day04()
    private val input = readInputFile("04")[0].split("-").map { it.toInt() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(1, problem.solveFirst(122345, 122345))

        assertEquals(1, problem.solveFirst(111123, 111123))

        assertEquals(0, problem.solveFirst(135679, 135679))

        assertEquals(1, problem.solveFirst(111111, 111111))

        assertEquals(0, problem.solveFirst(223450, 223450))

        assertEquals(0, problem.solveFirst(123789, 123789))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(460, problem.solveFirst(input[0], input[1]))
    }
}
