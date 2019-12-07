package be.inniger.advent

import be.inniger.advent.util.runProgram

class Day05 {

    companion object {
        private const val CONDITIONER_ID = 1
        private const val RADIATOR_ID = 5
    }

    fun solveFirst(program: List<Int>, input: Int = CONDITIONER_ID) = runProgram(program, input).output

    fun solveSecond(program: List<Int>, input: Int = RADIATOR_ID) = runProgram(program, input).output
}
