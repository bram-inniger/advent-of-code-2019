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

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(
            listOf(
                " #",
                "# "
            ), problem.solveSecond("0222112222120000", 2, 2)
        )
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(
            listOf(
                "#  #  ##   ##  #  # #  # ",
                "#  # #  # #  # #  # #  # ",
                "#  # #    #    #  # #### ",
                "#  # # ## #    #  # #  # ",
                "#  # #  # #  # #  # #  # ",
                " ##   ###  ##   ##  #  # "
            ), problem.solveSecond(input)
        )
    }
}
