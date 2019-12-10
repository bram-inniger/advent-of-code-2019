package be.inniger.advent

import be.inniger.advent.util.readInputFile
import org.junit.Test
import kotlin.test.assertEquals

class Day10Test {

    private val problem = Day10()
    private val input = readInputFile("10")

    @Test
    fun validateFirstSampleInputs() {
        assertEquals(
            8, problem.solveFirst(
                listOf(
                    ".#..#",
                    ".....",
                    "#####",
                    "....#",
                    "...##"
                )
            )
        )

        assertEquals(
            33, problem.solveFirst(
                listOf(
                    "......#.#.",
                    "#..#.#....",
                    "..#######.",
                    ".#.#.###..",
                    ".#..#.....",
                    "..#....#.#",
                    "#..#....#.",
                    ".##.#..###",
                    "##...#..#.",
                    ".#....####"
                )
            )
        )

        assertEquals(
            35, problem.solveFirst(
                listOf(
                    "#.#...#.#.",
                    ".###....#.",
                    ".#....#...",
                    "##.#.#.#.#",
                    "....#.#.#.",
                    ".##..###.#",
                    "..#...##..",
                    "..##....##",
                    "......#...",
                    ".####.###."
                )
            )
        )

        assertEquals(
            41, problem.solveFirst(
                listOf(
                    ".#..#..###",
                    "####.###.#",
                    "....###.#.",
                    "..###.##.#",
                    "##.##.#.#.",
                    "....###..#",
                    "..#.#..#.#",
                    "#..#.#.###",
                    ".##...##.#",
                    ".....#.#.."
                )
            )
        )

        assertEquals(
            210, problem.solveFirst(
                listOf(
                    ".#..##.###...#######",
                    "##.############..##.",
                    ".#.######.########.#",
                    ".###.#######.####.#.",
                    "#####.##.#.##.###.##",
                    "..#####..#.#########",
                    "####################",
                    "#.####....###.#.#.##",
                    "##.#################",
                    "#####.##.###..####..",
                    "..######..##.#######",
                    "####.##.####...##..#",
                    ".#####..#.######.###",
                    "##...#.##########...",
                    "#.##########.#######",
                    ".####.#.###.###.#.##",
                    "....##.##.###..#####",
                    ".#.#.###########.###",
                    "#.#.#.#####.####.###",
                    "###.##.####.##.#..##"
                )
            )
        )
    }

    @Test
    fun validateFirstSolution() {
        assertEquals(284, problem.solveFirst(input))
    }
}
