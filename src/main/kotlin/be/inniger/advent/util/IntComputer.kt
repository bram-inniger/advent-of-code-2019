package be.inniger.advent.util

private const val DEFAULT_INPUT = 0

data class Computation(val output: Int, val program: List<Int>)

fun runProgram(originalProgram: List<Int>, input: Int = DEFAULT_INPUT, phase: Int = input): Computation {
    val program = originalProgram.toIntArray()
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
            Opcode.HALT -> return Computation(output, program.toList())
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
