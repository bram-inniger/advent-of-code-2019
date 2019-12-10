package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day09 {

    companion object {
        private const val TEST_MODE = 1
        private const val BOOST_MODE = 2
    }

    fun solveFirst(program: List<Long>) = runProgramUtilEnd(IntComputer(program), TEST_MODE)

    fun solveSecond(program: List<Long>) = runProgramUtilEnd(IntComputer(program), BOOST_MODE)

    private tailrec fun runProgramUtilEnd(
        computer: IntComputer, computerInput: Int, output: List<Long> = listOf()
    ): List<Long> {
        val state = computer.runProgram(computerInput)

        return if (state.halted) return output
        else runProgramUtilEnd(computer, computerInput, output.plus(state.output))
    }
}
