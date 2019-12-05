package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day05Test {

    private val problem = Day05()
    private val input = readInputFile("05")[0].split(",").map { it.toInt() }.toIntArray()

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(42, problem.solveFirst(intArrayOf(3, 0, 4, 0, 99), 42))

        assertEquals(0, problem.solveFirst(intArrayOf(1002, 4, 3, 4, 33)))

        assertEquals(0, problem.solveFirst(intArrayOf(1101, 100, -1, 4, 0)))
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(11049715, problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        assertEquals(0, problem.solveFirst(intArrayOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 7))
        assertEquals(1, problem.solveFirst(intArrayOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 8))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8), 9))

        assertEquals(1, problem.solveFirst(intArrayOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 7))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 8))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8), 9))

        assertEquals(0, problem.solveFirst(intArrayOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 7))
        assertEquals(1, problem.solveFirst(intArrayOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 8))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 3, 1108, -1, 8, 3, 4, 3, 99), 9))

        assertEquals(1, problem.solveFirst(intArrayOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 7))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 8))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 3, 1107, -1, 8, 3, 4, 3, 99), 9))

        assertEquals(1, problem.solveFirst(intArrayOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), -1))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 0))
        assertEquals(1, problem.solveFirst(intArrayOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9), 1))

        assertEquals(1, problem.solveFirst(intArrayOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), -1))
        assertEquals(0, problem.solveFirst(intArrayOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 0))
        assertEquals(1, problem.solveFirst(intArrayOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1), 1))

        assertEquals(
            999, problem.solveFirst(
                intArrayOf(
                    3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
                ), 7
            )
        )
        assertEquals(
            1000, problem.solveFirst(
                intArrayOf(
                    3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
                ), 8
            )
        )
        assertEquals(
            1001, problem.solveFirst(
                intArrayOf(
                    3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                    1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                    999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
                ), 9
            )
        )
    }

    @Test
    fun validateSecondSolution() {
        assertEquals(2140710, problem.solveSecond(input))
    }
}
