package be.inniger.advent

import be.inniger.advent.util.generatePermutations
import be.inniger.advent.util.head
import be.inniger.advent.util.pow
import be.inniger.advent.util.tail

class Day07 {

    companion object {
        private const val FIRST_INPUT = 0
        private const val FIRST_AMP_PHASE = 0
        private const val LAST_AMP_PHASE = 4
    }

    fun solveFirst(program: IntArray) = generatePermutations((FIRST_AMP_PHASE..LAST_AMP_PHASE).toList())
        .map { runAmplifiers(program, it) }
        .max()!!

    private tailrec fun runAmplifiers(originalProgram: IntArray, phases: List<Int>, output: Int = FIRST_INPUT): Int =
        if (phases.isEmpty()) output
        else runAmplifiers(originalProgram, phases.tail(), runProgram(originalProgram, phases.head(), output))

    private fun runProgram(originalProgram: IntArray, phase: Int, input: Int): Int {
        val program = originalProgram.copyOf()
        var pointer = 0
        var output = 0
        var nrInputReads = 0

        while (true) {
            val instruction = program[pointer]
            val opcode = Opcode.parse(instruction % 100)
            val arg = { argIndex: Int -> (::readArg)(instruction, program, pointer, argIndex) }

            when (opcode) {
                Opcode.ADD -> program[program[pointer + 3]] = arg(1) + arg(2)
                Opcode.MULTIPLY -> program[program[pointer + 3]] = arg(1) * arg(2)
                Opcode.INPUT -> program[program[pointer + 1]] = when (++nrInputReads) {
                    1 -> phase
                    2 -> input
                    else -> error("Called input already $nrInputReads times, expected at most 2")
                }
                Opcode.OUTPUT -> output = output * 10 + arg(1)
                Opcode.JT -> if (arg(1) != 0) pointer = arg(2) - opcode.instructionLength
                Opcode.JF -> if (arg(1) == 0) pointer = arg(2) - opcode.instructionLength
                Opcode.LT -> program[program[pointer + 3]] = if (arg(1) < arg(2)) 1 else 0
                Opcode.EQ -> program[program[pointer + 3]] = if (arg(1) == arg(2)) 1 else 0
                Opcode.HALT -> return output
            }

            pointer += opcode.instructionLength
        }
    }

    private fun readArg(instruction: Int, program: IntArray, pointer: Int, argIndex: Int) =
        if (computeMode(instruction, argIndex) == Mode.POSITION) program[program[pointer + argIndex]]
        else program[pointer + argIndex]

    private fun computeMode(instruction: Int, argIndex: Int) =
        if (instruction / (10 * 10.pow(argIndex)) % 10 == 0) Mode.POSITION
        else Mode.IMMEDIATE

    private enum class Opcode(val instructionLength: Int) {
        ADD(4),
        MULTIPLY(4),
        INPUT(2),
        OUTPUT(2),
        JT(3),
        JF(3),
        LT(4),
        EQ(4),
        HALT(1);

        companion object {
            fun parse(opcode: Int) = when (opcode) {
                1 -> ADD
                2 -> MULTIPLY
                3 -> INPUT
                4 -> OUTPUT
                5 -> JT
                6 -> JF
                7 -> LT
                8 -> EQ
                99 -> HALT
                else -> error("Cannot parse opcode: $opcode")
            }
        }
    }

    private enum class Mode {
        POSITION, IMMEDIATE
    }
}
