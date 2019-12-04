package be.inniger.advent

class Day04 {

    fun solveFirst(start: Int, end: Int) = (start..end)
        .map { splitDigits(it) }
        .filter { isValidPassword(it) }
        .count()

    private fun splitDigits(password: Int): List<Int> {
        val digits = mutableListOf<Int>()
        var mutablePassword = password

        while (mutablePassword > 0) {
            digits.add(mutablePassword % 10)
            mutablePassword /= 10
        }

        return digits.toList().asReversed()
    }

    private fun isValidPassword(digits: List<Int>): Boolean {
        var hasDoubleDigits = false
        var lastDigit = Int.MIN_VALUE

        for (digit in digits) {
            if (digit < lastDigit) return false
            if (digit == lastDigit) hasDoubleDigits = true

            lastDigit = digit
        }

        return hasDoubleDigits
    }
}
