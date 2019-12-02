package be.inniger.advent

class Day02 {

    fun solveFirst(program: IntArray): Int {
        for (pointer in program.indices step 4) {
            when (OpCode.parse(program[pointer])) {
                OpCode.ADD ->
                    program[program[pointer + 3]] = program[program[pointer + 1]] + program[program[pointer + 2]]
                OpCode.MULTIPLY ->
                    program[program[pointer + 3]] = program[program[pointer + 1]] * program[program[pointer + 2]]
                OpCode.HALT ->
                    return program[0]
            }
        }

        throw IllegalArgumentException("Invalid input that doesn't properly HALT")
    }

    private enum class OpCode {
        ADD,
        MULTIPLY,
        HALT;

        companion object {
            fun parse(opcode: Int) = when (opcode) {
                1 -> ADD
                2 -> MULTIPLY
                99 -> HALT
                else -> throw IllegalArgumentException()
            }
        }
    }
}
