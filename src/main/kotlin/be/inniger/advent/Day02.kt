package be.inniger.advent

class Day02 {

    companion object {
        private const val DESIRED_OUTPUT = 19690720
    }

    fun solveFirst(program: IntArray, noun: Int = program[1], verb: Int = program[2]) =
        runProgram(program, noun, verb)

    fun solveSecond(program: IntArray): Int {
        for (noun in 1..99) {
            for (verb in 1..99) {
                if (runProgram(program, noun, verb) == DESIRED_OUTPUT) {
                    return 100 * noun + verb
                }
            }
        }

        throw IllegalArgumentException("No suitable noun and verb found to produce the correct output: $DESIRED_OUTPUT")
    }

    private fun runProgram(originalProgram: IntArray, noun: Int, verb: Int): Int {
        val program = originalProgram.copyOf()
        var pointer = 0

        program[1] = noun
        program[2] = verb

        while (true) {
            val opcode = OpCode.parse(program[pointer])

            when (opcode) {
                OpCode.ADD ->
                    program[program[pointer + 3]] = program[program[pointer + 1]] + program[program[pointer + 2]]
                OpCode.MULTIPLY ->
                    program[program[pointer + 3]] = program[program[pointer + 1]] * program[program[pointer + 2]]
                OpCode.HALT ->
                    return program[0]
            }

            pointer += opcode.nrInstructions
        }
    }

    private enum class OpCode(val nrInstructions: Int) {
        ADD(4),
        MULTIPLY(4),
        HALT(1);

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
