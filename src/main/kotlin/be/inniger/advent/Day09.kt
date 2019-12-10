package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day09 {

    fun solveFirst(program: List<Long>, computerInput: Int) = runProgramUtilEnd(IntComputer(program), computerInput)

    private tailrec fun runProgramUtilEnd(
        computer: IntComputer, computerInput: Int, output: List<Long> = listOf()
    ): List<Long> {
        val state = computer.runProgram(computerInput)

        return if (state.halted) return output
        else runProgramUtilEnd(computer, computerInput, output.plus(state.output))
    }
}
