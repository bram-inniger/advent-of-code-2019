package be.inniger.advent.util

class IntComputer(originalProgram: List<Long>, originalPhase: Int? = null) {

    companion object {
        private const val DEFAULT_INPUT = 0
        private val BREAKING_OPCODES = setOf(Opcode.OUTPUT, Opcode.HALT)
    }

    data class State(val output: Long, val firsPositionValue: Long, val halted: Boolean)

    private val program =
        originalProgram.mapIndexed { index, element -> index to element }
            .toMap()
            .toMutableMap()
    private val phase = originalPhase?.toLong()
    private var pointer = 0
    private var relativeBase = 0
    private var nrInputReads = 0
    private var output = 0L
    private var halted = false

    private fun Long?.toIndex() = this?.toInt() ?: 0
    private fun Long?.toValue() = this ?: 0L

    fun runProgram(input: Int = DEFAULT_INPUT): State {
        while (!halted) {
            val instruction = program[pointer].toValue()
            val opcode = Opcode.parse((instruction % 100).toInt())
            val arg = { argIndex: Int -> (::readArg)(instruction, pointer, argIndex) }
            val out = { argIndex: Int -> (::readOutput)(instruction, argIndex) }

            when (opcode) {
                Opcode.ADD -> program[out(3)] = arg(1) + arg(2)
                Opcode.MULTIPLY -> program[out(3)] = arg(1) * arg(2)
                Opcode.INPUT -> program[out(1)] = if (nrInputReads++ == 0 && phase != null) phase else input.toLong()
                Opcode.OUTPUT -> output = arg(1)
                Opcode.JT -> if (arg(1) != 0L) pointer = arg(2).toInt() - opcode.instructionLength
                Opcode.JF -> if (arg(1) == 0L) pointer = arg(2).toInt() - opcode.instructionLength
                Opcode.LT -> program[out(3)] = if (arg(1) < arg(2)) 1L else 0L
                Opcode.EQ -> program[out(3)] = if (arg(1) == arg(2)) 1L else 0L
                Opcode.BASE -> relativeBase += arg(1).toInt()
                Opcode.HALT -> halted = true
            }

            pointer += opcode.instructionLength
            if (opcode in BREAKING_OPCODES) return currentState()
        }

        error("Tried running an already halted program")
    }

    private fun readOutput(instruction: Long, arg: Int) =
        program[pointer + arg].toIndex() +
                if (computeMode(instruction, arg) == Mode.RELATIVE) relativeBase
                else 0

    private fun readArg(instruction: Long, pointer: Int, argIndex: Int) =
        when (computeMode(instruction, argIndex)) {
            Mode.POSITION -> program[program[pointer + argIndex].toIndex()].toValue()
            Mode.IMMEDIATE -> program[pointer + argIndex].toValue()
            Mode.RELATIVE -> program[(program[pointer + argIndex].toIndex()) + relativeBase].toValue()
        }

    private fun computeMode(instruction: Long, argIndex: Int) =
        when (val mode = instruction / (10 * 10.pow(argIndex)) % 10) {
            0L -> Mode.POSITION
            1L -> Mode.IMMEDIATE
            2L -> Mode.RELATIVE
            else -> error("IntComputer does not support mode: $mode")
        }

    private fun currentState() = State(output, program[0].toValue(), halted)

    private enum class Opcode(val instructionLength: Int) {
        ADD(4),
        MULTIPLY(4),
        INPUT(2),
        OUTPUT(2),
        JT(3),
        JF(3),
        LT(4),
        EQ(4),
        BASE(2),
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
                9 -> BASE
                99 -> HALT
                else -> error("Cannot parse opcode: $opcode")
            }
        }
    }

    private enum class Mode {
        POSITION, IMMEDIATE, RELATIVE
    }
}
