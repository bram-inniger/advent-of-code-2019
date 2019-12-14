package be.inniger.advent.util

import kotlin.math.abs
import kotlin.math.pow

fun Int.pow(power: Int) = this.toDouble().pow(power.toDouble()).toInt()

fun <T> List<T>.head() = first()
fun <T> List<T>.tail() = drop(1)

fun <T> generatePermutations(elements: List<T>): List<List<T>> =
    if (elements.isEmpty()) listOf(emptyList())
    else elements.indices
        .flatMap { index ->
            val element = elements[index]
            val remainingElements = elements.subList(0, index) + elements.subList(index + 1, elements.size)
            generatePermutations(remainingElements).map { it + element }
        }

fun gcd(a: Int, b: Int) = gcd(a.toLong(), b.toLong()).toInt()

fun gcd(a: Long, b: Long): Long {
    if (a == 0L && b == 0L) return 1L

    var absA = abs(a)
    var absB = abs(b)

    if (absA == 0L) return absB
    if (absB == 0L) return absA

    while (absA != absB) {
        if (absA > absB) absA -= absB
        else absB -= absA
    }

    return absA
}

fun lcm(a: Int, b: Int) = lcm(a.toLong(), b.toLong()).toInt()

fun lcm(a: Long, b: Long) = a / gcd(a, b) * b
