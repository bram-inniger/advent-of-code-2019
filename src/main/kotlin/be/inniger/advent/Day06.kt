package be.inniger.advent

import java.util.ArrayDeque

class Day06 {

    companion object {
        private const val CENTER_OF_MASS = "COM"
    }

    fun solveFirst(orbitDescriptions: List<String>): Int {
        val orbitRelations = orbitDescriptions.map { OrbitRelation.parse(it) }
        val orbitedToOrbiter = orbitRelations.groupBy(
            { relation -> relation.orbited },
            { relation -> relation.orbiter })
        val orbiterToOrbited = orbitRelations.groupBy(
            { relation -> relation.orbiter },
            { relation -> relation.orbited })
            .mapValues { it.value.single() }

        val orbiterStack = ArrayDeque<String>()
        val distanceToCenter = mutableMapOf<String, Int>()

        orbitedToOrbiter.getValue(CENTER_OF_MASS).forEach { orbiterStack.push(it) }
        distanceToCenter[CENTER_OF_MASS] = 0

        while (orbiterStack.isNotEmpty()) {
            val orbiter = orbiterStack.pop()
            orbitedToOrbiter.getOrDefault(orbiter, listOf()).forEach { orbiterStack.push(it) }
            distanceToCenter[orbiter] = distanceToCenter.getValue(orbiterToOrbited.getValue(orbiter)) + 1
        }

        return distanceToCenter.map { it.value }.sum()
    }

    private data class OrbitRelation(val orbited: String, val orbiter: String) {
        companion object {
            private val regex = """^(\w+)\)(\w+)$""".toRegex()

            fun parse(orbitDescription: String): OrbitRelation {
                val (orbited, orbiter) = regex.find(orbitDescription)!!.destructured
                return OrbitRelation(orbited, orbiter)
            }
        }
    }
}
