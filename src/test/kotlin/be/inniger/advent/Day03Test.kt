package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day03Test {

    private val problem = Day03()
    private val input = readInputFile("03").map { it.split(",") }

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(
            6, problem.solveFirst(
                listOf("R8", "U5", "L5", "D3"),
                listOf("U7", "R6", "D4", "L4")
            )
        )

        assertEquals(
            159, problem.solveFirst(
                listOf("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"),
                listOf("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83")
            )
        )

        assertEquals(
            135, problem.solveFirst(
                listOf("R98", "U47", "R26", "D63", "R33", "U87", "L62", "D20", "R33", "U53", "R51"),
                listOf("U98", "R91", "D20", "R16", "D67", "R40", "U7", "R15", "U6", "R7")
            )
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(870, problem.solveFirst(input[0], input[1]))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(
            30, problem.solveSecond(
                listOf("R8", "U5", "L5", "D3"),
                listOf("U7", "R6", "D4", "L4")
            )
        )

        assertEquals(
            610, problem.solveSecond(
                listOf("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"),
                listOf("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83")
            )
        )

        assertEquals(
            410, problem.solveSecond(
                listOf("R98", "U47", "R26", "D63", "R33", "U87", "L62", "D20", "R33", "U53", "R51"),
                listOf("U98", "R91", "D20", "R16", "D67", "R40", "U7", "R15", "U6", "R7")
            )
        )
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(13698, problem.solveSecond(input[0], input[1]))
    }
}
