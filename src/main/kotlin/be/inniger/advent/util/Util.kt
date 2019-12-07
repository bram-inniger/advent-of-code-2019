package be.inniger.advent.util

import kotlin.math.pow

fun Int.pow(power: Int) = this.toDouble().pow(power.toDouble()).toInt()

fun <T> List<T>.head() = first()
fun <T> List<T>.tail() = drop(1)
