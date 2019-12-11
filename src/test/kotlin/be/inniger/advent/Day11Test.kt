package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {

    private val problem = Day11()
    private val input = readInputFile("11")[0].split(",").map { it.toLong() }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(
            6, problem.solveFirst(
                listOf(
                    104, 1, 104, 0,
                    104, 0, 104, 0,
                    104, 1, 104, 0,
                    104, 1, 104, 0,
                    104, 0, 104, 1,
                    104, 1, 104, 0,
                    104, 1, 104, 0,
                    99L
                )
            )
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(2211, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        // No sample inputs given
    }

    @Test
    fun validateSecondSolution() {
        val registration =
            """
                #### ####  ##  #  # #  # ####  ##   ## 
                #    #    #  # # #  #  # #    #  # #  #
                ###  ###  #    ##   #  # ###  #    #   
                #    #    #    # #  #  # #    # ## #   
                #    #    #  # # #  #  # #    #  # #  #
                #### #     ##  #  #  ##  ####  ###  ## 
            """.trimIndent()

        assertEquals(registration, problem.solveSecond(input))
    }
}
