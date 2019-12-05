package be.inniger.advent

import kotlin.math.pow

class Day05 {

    companion object {
        private const val CONDITIONER_ID = 1
        private const val RADIATOR_ID = 5
    }

    fun solveFirst(program: IntArray, input: Int = CONDITIONER_ID) =
        runProgram(program, input)

    fun solveSecond(program: IntArray, input: Int = RADIATOR_ID) =
        runProgram(program, input)

    private fun runProgram(originalProgram: IntArray, input: Int): Int {
        val program = originalProgram.copyOf()
        var pointer = 0
        var diagnostics = 0

        while (true) {
            val instruction = program[pointer]
            val opcode = Opcode.parse(instruction % 100)
            val arg = partialApply(::readArg, instruction, program, pointer)

            when (opcode) {
                Opcode.ADD -> program[program[pointer + 3]] = arg(1) + arg(2)
                Opcode.MULTIPLY -> program[program[pointer + 3]] = arg(1) * arg(2)
                Opcode.INPUT -> program[program[pointer + 1]] = input
                Opcode.OUTPUT -> diagnostics = diagnostics * 10 + arg(1)
                Opcode.JT -> if (arg(1) != 0) pointer = arg(2) - opcode.instructionLength
                Opcode.JF -> if (arg(1) == 0) pointer = arg(2) - opcode.instructionLength
                Opcode.LT -> program[program[pointer + 3]] = if (arg(1) < arg(2)) 1 else 0
                Opcode.EQ -> program[program[pointer + 3]] = if (arg(1) == arg(2)) 1 else 0
                Opcode.HALT -> return diagnostics
            }

            pointer += opcode.instructionLength
        }
    }

    private fun <A, B, C, D, E> partialApply(f: (A, B, C, D) -> E, a: A, b: B, c: C): (D) -> E {
        return { d: D -> f(a, b, c, d) }
    }

    private fun readArg(instruction: Int, program: IntArray, pointer: Int, argIndex: Int) =
        if (computeMode(instruction, argIndex) == Mode.POSITION) program[program[pointer + argIndex]]
        else program[pointer + argIndex]

    private fun computeMode(instruction: Int, argIndex: Int) =
        if (instruction / (10 * 10.pow(argIndex)) % 10 == 0) Mode.POSITION
        else Mode.IMMEDIATE

    private fun Int.pow(power: Int) = this.toDouble().pow(power.toDouble()).toInt()

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
