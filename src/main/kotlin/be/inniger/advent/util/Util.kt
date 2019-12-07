package be.inniger.advent.util

import kotlin.math.pow

fun Int.pow(power: Int) = this.toDouble().pow(power.toDouble()).toInt()

fun <T> List<T>.head() = first()
fun <T> List<T>.tail() = drop(1)
fun <T> List<T>.minusIndex(index: Int) = this[index] to subList(0, index) + subList(index + 1, size)

fun <T> generatePermutations(elements: List<T>): List<List<T>> =
    if (elements.isEmpty()) listOf(emptyList())
    else elements.indices
        .flatMap { index ->
            val (element, remainingElements) = elements.minusIndex(index)
            generatePermutations(remainingElements).map { it + element }
        }
