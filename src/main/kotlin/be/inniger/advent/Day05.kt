package be.inniger.advent

import kotlin.math.pow

class Day05 {

    companion object {
        private const val TEST_ID = 1
    }

    fun solveFirst(program: IntArray, input: Int = TEST_ID) =
        runProgram(program, input)

    private fun runProgram(originalProgram: IntArray, input: Int): String {
        val program = originalProgram.copyOf()
        val diagnostics = StringBuilder()
        var pointer = 0

        while (true) {
            val instruction = program[pointer]
            val opcode = Opcode.parse(instruction % 100)

            when (opcode) {
                Opcode.ADD -> program[program[pointer + 3]] =
                    readArg(instruction, program, pointer, 1) + readArg(instruction, program, pointer, 2)
                Opcode.MULTIPLY -> program[program[pointer + 3]] =
                    readArg(instruction, program, pointer, 1) * readArg(instruction, program, pointer, 2)
                Opcode.INPUT -> program[program[pointer + 1]] = input
                Opcode.OUTPUT -> diagnostics.append(readArg(instruction, program, pointer, 1))
                Opcode.HALT -> return diagnostics.toString()
            }

            pointer += opcode.instructionLength
        }
    }

    private fun readArg(instruction: Int, program: IntArray, pointer: Int, argIndex: Int) =
        if (computeMode(instruction, argIndex) == Mode.POSITION) program[program[pointer + argIndex]]
        else program[pointer + argIndex]

    private fun computeMode(instruction: Int, argIndex: Int) =
        if (instruction / (10 * 10.0.pow(argIndex.toDouble()).toInt()) % 10 == 0) Mode.POSITION
        else Mode.IMMEDIATE

    private enum class Opcode(val instructionLength: Int) {
        ADD(4),
        MULTIPLY(4),
        INPUT(2),
        OUTPUT(2),
        HALT(1);

        companion object {
            fun parse(opcode: Int) = when (opcode) {
                1 -> ADD
                2 -> MULTIPLY
                3 -> INPUT
                4 -> OUTPUT
                99 -> HALT
                else -> error("Cannot parse opcode: $opcode")
            }
        }
    }

    private enum class Mode {
        POSITION, IMMEDIATE
    }
}
