package be.inniger.advent

import kotlin.Int as Count
import kotlin.Int as Pixel

class Day08 {

    companion object {
        private const val PIXELS_HOR = 25
        private const val PIXELS_VER = 6
    }

    fun solveFirst(image: String) = (image.indices step PIXELS_HOR * PIXELS_VER)
        .map { image.substring(it, it + PIXELS_HOR * PIXELS_VER) }
        .map { Layer.parse(it) }
        .minBy { it.pixelCount[0] ?: 0 }
        .let { (it?.pixelCount?.get(1) ?: 0) * (it?.pixelCount?.get(2) ?: 0) }

    private data class Layer(val pixelCount: Map<Pixel, Count>) {

        companion object {
            fun parse(layer: String) = Layer(
                layer.map { it - '0' }
                    .groupingBy { it }
                    .eachCount())
        }
    }
}
