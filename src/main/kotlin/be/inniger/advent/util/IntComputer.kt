package be.inniger.advent.util

class IntComputer(originalProgram: List<Int>, private val phase: Int? = null) {

    companion object {
        private const val DEFAULT_INPUT = 0
        private val BREAKING_OPCODES = setOf(Opcode.OUTPUT, Opcode.HALT)
    }

    data class State(val output: Int, val firsProgramPosition: Int, val halted: Boolean)

    private val program = originalProgram.mapIndexed { index, element -> index to element }.toMap().toMutableMap()
    private var pointer = 0
    private var nrInputReads = 0
    private var output = 0
    private var halted = false

    fun runProgram(input: Int = DEFAULT_INPUT): State {
        while (!halted) {
            val instruction = program[pointer] ?: 0
            val opcode = Opcode.parse(instruction % 100)
            val arg = { argIndex: Int -> (::readArg)(instruction, pointer, argIndex) }

            when (opcode) {
                Opcode.ADD -> program[program[pointer + 3] ?: 0] = arg(1) + arg(2)
                Opcode.MULTIPLY -> program[program[pointer + 3] ?: 0] = arg(1) * arg(2)
                Opcode.INPUT -> program[program[pointer + 1] ?: 0] =
                    if (nrInputReads++ == 0 && phase != null) phase
                    else input
                Opcode.OUTPUT -> output = arg(1)
                Opcode.JT -> if (arg(1) != 0) pointer = arg(2) - opcode.instructionLength
                Opcode.JF -> if (arg(1) == 0) pointer = arg(2) - opcode.instructionLength
                Opcode.LT -> program[program[pointer + 3] ?: 0] = if (arg(1) < arg(2)) 1 else 0
                Opcode.EQ -> program[program[pointer + 3] ?: 0] = if (arg(1) == arg(2)) 1 else 0
                Opcode.HALT -> halted = true
            }

            pointer += opcode.instructionLength
            if (opcode in BREAKING_OPCODES) return currentState()
        }

        error("Tried running an already halted program")
    }

    private fun readArg(instruction: Int, pointer: Int, argIndex: Int) =
        when (computeMode(instruction, argIndex)) {
            Mode.POSITION -> program[program[pointer + argIndex]] ?: 0
            Mode.IMMEDIATE -> program[pointer + argIndex] ?: 0
        }

    private fun computeMode(instruction: Int, argIndex: Int) =
        if (instruction / (10 * 10.pow(argIndex)) % 10 == 0) Mode.POSITION
        else Mode.IMMEDIATE

    private fun currentState() = State(output, program[0] ?: 0, halted)

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
