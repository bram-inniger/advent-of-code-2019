package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day06Test {

    private val problem = Day06()
    private val input = readInputFile("06")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(
            42,
            problem.solveFirst(
                listOf(
                    "COM)B",
                    "B)C",
                    "C)D",
                    "D)E",
                    "E)F",
                    "B)G",
                    "G)H",
                    "D)I",
                    "E)J",
                    "J)K",
                    "K)L"
                )
            )
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(308790, problem.solveFirst(input))
    }
}
