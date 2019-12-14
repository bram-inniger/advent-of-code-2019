package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day12Test {

    private val problem = Day12()
    private val input = readInputFile("12")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(
            179, problem.solveFirst(
                listOf(
                    "<x=-1, y=0, z=2>",
                    "<x=2, y=-10, z=-7>",
                    "<x=4, y=-8, z=8>",
                    "<x=3, y=5, z=-1>"
                ), 10
            )
        )

        assertEquals(
            1940, problem.solveFirst(
                listOf(
                    "<x=-8, y=-10, z=0>",
                    "<x=5, y=5, z=10>",
                    "<x=2, y=-7, z=3>",
                    "<x=9, y=-8, z=-3>"
                ), 100
            )
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(10055, problem.solveFirst(input, 1000))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(
            2772, problem.solveSecond(
                listOf(
                    "<x=-1, y=0, z=2>",
                    "<x=2, y=-10, z=-7>",
                    "<x=4, y=-8, z=8>",
                    "<x=3, y=5, z=-1>"
                )
            )
        )

        assertEquals(
            4686774924, problem.solveSecond(
                listOf(
                    "<x=-8, y=-10, z=0>",
                    "<x=5, y=5, z=10>",
                    "<x=2, y=-7, z=3>",
                    "<x=9, y=-8, z=-3>"
                )
            )
        )
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(374307970285176, problem.solveSecond(input))
    }
}
