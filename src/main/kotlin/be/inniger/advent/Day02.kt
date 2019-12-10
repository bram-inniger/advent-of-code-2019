package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day02 {

    companion object {
        private const val DESIRED_OUTPUT = 19690720
    }

    fun solveFirst(program: List<Long>, noun: Long = program[1], verb: Long = program[2]) =
        IntComputer(editNounAndVerb(program, Words(noun, verb))).runProgram().firsPositionValue

    fun solveSecond(program: List<Long>) = generateAllWords()
        .first { IntComputer(editNounAndVerb(program, it)).runProgram().firsPositionValue.toInt() == DESIRED_OUTPUT }
        .let { 100 * it.noun + it.verb }

    private fun editNounAndVerb(program: List<Long>, words: Words): List<Long> {
        val mutableProgram = program.toMutableList()

        mutableProgram[1] = words.noun
        mutableProgram[2] = words.verb

        return mutableProgram.toList()
    }

    private fun generateAllWords() =
        (1..99).flatMap { noun ->
            (1..99).map { verb -> Words(noun.toLong(), verb.toLong()) }
        }

    private data class Words(val noun: Long, val verb: Long)
}
