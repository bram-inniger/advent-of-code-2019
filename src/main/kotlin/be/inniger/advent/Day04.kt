package be.inniger.advent

class Day04 {

    fun solveFirst(start: Int, end: Int) = solve(start, end) { hasGroupOfAtLeastTwo(it) }

    fun solveSecond(start: Int, end: Int) = solve(start, end) { hasGroupOfExactlyTwo(it) }

    private fun solve(start: Int, end: Int, groupingPredicate: (List<Int>) -> Boolean): Int {
        return (start..end)
            .map { splitDigits(it) }
            .filter { digitsOnlyIncrease(it) }
            .filter(groupingPredicate)
            .count()
    }

    private fun splitDigits(password: Int): List<Int> {
        val digits = mutableListOf<Int>()
        var mutablePassword = password

        while (mutablePassword > 0) {
            digits.add(mutablePassword % 10)
            mutablePassword /= 10
        }

        return digits.toList().asReversed()
    }

    private fun digitsOnlyIncrease(digits: List<Int>) =
        (1 until digits.size).none { digits[it] < digits[it - 1] }

    private fun hasGroupOfAtLeastTwo(digits: List<Int>) =
        (1 until digits.size).any { digits[it] == digits[it - 1] }

    private fun hasGroupOfExactlyTwo(digits: List<Int>) = digits.indices
        .any {
            val digit = digits[it]
            digit != digits.getOrNull(it - 1) && digit == digits.getOrNull(it + 1) && digit != digits.getOrNull(it + 2)
        }
}
