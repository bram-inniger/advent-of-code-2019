package be.inniger.advent

import be.inniger.advent.util.IntComputer
import be.inniger.advent.util.generatePermutations
import be.inniger.advent.util.head
import be.inniger.advent.util.tail

class Day07 {

    companion object {
        private const val FIRST_INPUT = 0
        private const val FIRST_AMP_PHASE = 0
        private const val LAST_AMP_PHASE = 4
    }

    fun solveFirst(program: List<Int>) = generatePermutations((FIRST_AMP_PHASE..LAST_AMP_PHASE).toList())
        .map { runAmplifiers(program, it) }
        .max()!!

    private tailrec fun runAmplifiers(originalProgram: List<Int>, phases: List<Int>, output: Int = FIRST_INPUT): Int =
        if (phases.isEmpty()) output
        else runAmplifiers(
            originalProgram,
            phases.tail(),
            IntComputer(originalProgram, phases.head()).runProgram(output).output
        )
}
