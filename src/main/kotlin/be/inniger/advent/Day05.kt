package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day05 {

    companion object {
        private const val CONDITIONER_ID = 1
        private const val RADIATOR_ID = 5
    }

    fun solveFirst(program: List<Long>, input: Int = CONDITIONER_ID) = runUntilHalt(IntComputer(program), input)

    fun solveSecond(program: List<Long>, input: Int = RADIATOR_ID) = runUntilHalt(IntComputer(program), input)

    private tailrec fun runUntilHalt(computer: IntComputer, input: Int, output: String = "0", halted: Boolean = false): Int =
        if (halted) output.toInt()
        else {
            val state = computer.runProgram(input)
            val newOutput = output + if (!state.halted) state.output else ""
            runUntilHalt(computer, input, newOutput, state.halted)
        }
}
