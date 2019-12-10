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
            "Visibility(asteroid=Asteroid(x=3, y=4), asteroidsVisible=8)", problem.solveFirst(
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
            "Visibility(asteroid=Asteroid(x=5, y=8), asteroidsVisible=33)", problem.solveFirst(
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
            "Visibility(asteroid=Asteroid(x=1, y=2), asteroidsVisible=35)", problem.solveFirst(
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
            "Visibility(asteroid=Asteroid(x=6, y=3), asteroidsVisible=41)", problem.solveFirst(
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
            "Visibility(asteroid=Asteroid(x=11, y=13), asteroidsVisible=210)", problem.solveFirst(
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
        assertEquals("Visibility(asteroid=Asteroid(x=20, y=19), asteroidsVisible=284)", problem.solveFirst(input))
    }

    @Test
    fun validateSecondSampleInputs() {
        val orderedAsteroids = problem.solveSecond(
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
            ), Day10.Asteroid(11, 13)
        )

        assertEquals("Asteroid(x=11, y=12)", orderedAsteroids[1 - 1].toString())
        assertEquals("Asteroid(x=12, y=1)", orderedAsteroids[2 - 1].toString())
        assertEquals("Asteroid(x=12, y=2)", orderedAsteroids[3 - 1].toString())
        assertEquals("Asteroid(x=12, y=8)", orderedAsteroids[10 - 1].toString())
        assertEquals("Asteroid(x=16, y=0)", orderedAsteroids[20 - 1].toString())
        assertEquals("Asteroid(x=16, y=9)", orderedAsteroids[50 - 1].toString())
        assertEquals("Asteroid(x=10, y=16)", orderedAsteroids[100 - 1].toString())
        assertEquals("Asteroid(x=9, y=6)", orderedAsteroids[199 - 1].toString())
        assertEquals("Asteroid(x=8, y=2)", orderedAsteroids[200 - 1].toString())
        assertEquals("Asteroid(x=10, y=9)", orderedAsteroids[201 - 1].toString())
        assertEquals("Asteroid(x=11, y=1)", orderedAsteroids[299 - 1].toString())
    }

    @Test
    fun validateSecondSolution() {
        val asteroid = problem.solveSecond(input, Day10.Asteroid(20, 19))[200 - 1]

        assertEquals(404, 100 * asteroid.x + asteroid.y)
    }
}
