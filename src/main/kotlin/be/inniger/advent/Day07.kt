package be.inniger.advent

import be.inniger.advent.util.IntComputer
import be.inniger.advent.util.generatePermutations
import be.inniger.advent.util.head
import be.inniger.advent.util.tail

class Day07 {

    companion object {
        private const val FIRST_INPUT = 0
    }

    fun solveFirst(program: List<Long>) = generatePermutations((0..4).toList())
        .map { createComputers(program, it) }
        .map { runAmplifiers(it) }
        .max()!!

    fun solveSecond(program: List<Long>) = generatePermutations((5..9).toList())
        .map { createComputers(program, it) }
        .map { runAmplifiersWithFeedback(it) }
        .max()!!

    private fun createComputers(program: List<Long>, phases: List<Int>) =
        phases.map { phase -> IntComputer(program, phase) }

    private tailrec fun runAmplifiers(computers: List<IntComputer>, output: Int = FIRST_INPUT): Int =
        if (computers.isEmpty()) output
        else runAmplifiers(computers.tail(), computers.head().runProgram(output).output.toInt())

    private tailrec fun runAmplifiersWithFeedback(
        computers: List<IntComputer>,
        output: Int = FIRST_INPUT,
        nrHalts: Int = 0,
        currentComputer: Int = 0
    ): Int =
        if (nrHalts >= computers.size) output
        else {
            val state = computers[currentComputer].runProgram(output)

            val updatedNrHalts = nrHalts + if (state.halted) 1 else 0
            val nextComputer = (currentComputer + 1) % computers.size

            runAmplifiersWithFeedback(computers, state.output.toInt(), updatedNrHalts, nextComputer)
        }
}
