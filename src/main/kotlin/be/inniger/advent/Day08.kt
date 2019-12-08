package be.inniger.advent

class Day08 {

    companion object {
        private const val DEFAULT_PIXELS_HOR = 25
        private const val DEFAULT_PIXELS_VER = 6
    }

    fun solveFirst(image: String) =
        readLayers(image, DEFAULT_PIXELS_HOR, DEFAULT_PIXELS_VER)
            .minBy { it.pixelCount[0] ?: 0 }
            .let { (it?.pixelCount?.get(1) ?: 0) * (it?.pixelCount?.get(2) ?: 0) }

    fun solveSecond(image: String, pixelsHor: Int = DEFAULT_PIXELS_HOR, pixelsVer: Int = DEFAULT_PIXELS_VER):
            List<String> {
        val layers = readLayers(image, pixelsHor, pixelsVer)

        val decoded = (0 until pixelsHor * pixelsVer)
            .map { index ->
                layers.first { layer -> layer.pixels[index] != Pixel.TRANSPARENT }.pixels[index]
            }
            .map { draw(it) }
            .joinToString("")

        return (0 until pixelsVer)
            .map { decoded.substring(it * pixelsHor, it * pixelsHor + pixelsHor) }
    }

    private fun readLayers(image: String, pixelsHor: Int, pixelsVer: Int) =
        (image.indices step pixelsHor * pixelsVer)
            .map { image.substring(it, it + pixelsHor * pixelsVer) }
            .map { Layer.parse(it) }

    private fun draw(pixel: Pixel) = when (pixel) {
        Pixel.BLACK -> ' '
        Pixel.WHITE -> '#'
        Pixel.TRANSPARENT -> error("Cannot draw transparent pixels")
    }

    private data class Layer(val pixelCount: Map<Int, Int>, val pixels: List<Pixel>) {

        companion object {
            fun parse(layer: String): Layer {
                val pixelCount = layer.map { it - '0' }
                    .groupingBy { it }
                    .eachCount()
                val pixels = layer.map { Pixel.read(it) }

                return Layer(pixelCount, pixels)
            }
        }
    }

    private enum class Pixel {
        BLACK,
        WHITE,
        TRANSPARENT;

        companion object {
            fun read(value: Char) = when (value) {
                '0' -> BLACK
                '1' -> WHITE
                '2' -> TRANSPARENT
                else -> error("Cannot read pixel from value: $value")
            }
        }
    }
}
