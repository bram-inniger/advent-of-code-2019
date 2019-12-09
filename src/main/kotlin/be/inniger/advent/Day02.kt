package be.inniger.advent

import be.inniger.advent.util.IntComputer

class Day02 {

    companion object {
        private const val DESIRED_OUTPUT = 19690720
    }

    fun solveFirst(program: List<Int>, noun: Int = program[1], verb: Int = program[2]) =
        IntComputer(editNounAndVerb(program, Words(noun, verb))).runProgram().firsPositionValue

    fun solveSecond(program: List<Int>) = generateAllWords()
        .first { IntComputer(editNounAndVerb(program, it)).runProgram().firsPositionValue.toInt() == DESIRED_OUTPUT }
        .let { 100 * it.noun + it.verb }

    private fun editNounAndVerb(program: List<Int>, words: Words): List<Int> {
        val mutableProgram = program.toMutableList()

        mutableProgram[1] = words.noun
        mutableProgram[2] = words.verb

        return mutableProgram.toList()
    }

    private fun generateAllWords() =
        (1..99).flatMap { noun ->
            (1..99).map { verb -> Words(noun, verb) }
        }

    private data class Words(val noun: Int, val verb: Int)
}
