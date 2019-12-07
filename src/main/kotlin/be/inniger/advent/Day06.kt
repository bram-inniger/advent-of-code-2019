package be.inniger.advent

import be.inniger.advent.util.head
import be.inniger.advent.util.tail
import kotlin.Int as Distance
import kotlin.String as Body
import kotlin.String as Description

class Day06 {

    companion object {
        private const val CENTER_OF_MASS = "COM"
        private const val YOU = "YOU"
        private const val SANTA = "SAN"
    }

    fun solveFirst(orbitDescriptions: List<Description>) =
        mapOrbits(orbitDescriptions).distanceToCenter.map { it.value }.sum()

    fun solveSecond(orbitDescriptions: List<Description>): Distance {
        val (orbiterToOrbited, distanceToCenter) = mapOrbits(orbitDescriptions)

        val yourDistanceToCenter = distanceToCenter.getValue(YOU) - 1
        val santasDistanceToCenter = distanceToCenter.getValue(SANTA) - 1
        val furthestCommonDistance = getOrbitPath(orbiterToOrbited, SANTA)
            .intersect(getOrbitPath(orbiterToOrbited, YOU))
            .map { distanceToCenter.getValue(it) }
            .max()!!

        return yourDistanceToCenter + santasDistanceToCenter - 2 * furthestCommonDistance
    }

    private fun mapOrbits(orbitDescriptions: List<Description>): MappedOrbits {
        val orbitRelations = orbitDescriptions.map { OrbitRelation.parse(it) }
        val orbitedToOrbiter = orbitRelations.groupBy(
            { relation -> relation.orbited },
            { relation -> relation.orbiter })
        val orbiterToOrbited = orbitRelations.groupBy(
            { relation -> relation.orbiter },
            { relation -> relation.orbited })
            .mapValues { it.value.single() }

        val distanceToCenter = calculateDistancesToCenter(
            orbitedToOrbiter,
            orbiterToOrbited,
            orbitedToOrbiter.getValue(CENTER_OF_MASS),
            mapOf(CENTER_OF_MASS to 0)
        )

        return MappedOrbits(orbiterToOrbited, distanceToCenter)
    }

    private tailrec fun calculateDistancesToCenter(
        orbitedToOrbiter: Map<Body, List<Body>>,
        orbiterToOrbited: Map<Body, Body>,
        orbitersToVisit: List<Body>,
        distancesToCenter: Map<Body, Distance>
    ): Map<Body, Distance> =
        if (orbitersToVisit.isEmpty()) distancesToCenter
        else {
            val orbiter = orbitersToVisit.head()
            val orbitersToAdd = orbitedToOrbiter.getOrDefault(orbiter, listOf())
            val distanceToCenter = distancesToCenter.getValue(orbiterToOrbited.getValue(orbiter)) + 1

            calculateDistancesToCenter(
                orbitedToOrbiter,
                orbiterToOrbited,
                orbitersToVisit.tail() + orbitersToAdd,
                distancesToCenter + (orbiter to distanceToCenter)
            )
        }

    private tailrec fun getOrbitPath(
        orbiterToOrbited: Map<Body, Body>, orbiter: Body?, orbitPath: Set<Body> = setOf()
    ): Set<Body> =
        if (orbiter == null) orbitPath
        else getOrbitPath(orbiterToOrbited, orbiterToOrbited[orbiter], orbitPath + orbiter)

    private data class OrbitRelation(val orbited: Body, val orbiter: Body) {
        companion object {
            private val regex = """^(\w+)\)(\w+)$""".toRegex()

            fun parse(orbitDescription: Description): OrbitRelation {
                val (orbited, orbiter) = regex.find(orbitDescription)!!.destructured
                return OrbitRelation(orbited, orbiter)
            }
        }
    }

    private data class MappedOrbits(val orbiterToOrbited: Map<Body, Body>, val distanceToCenter: Map<Body, Distance>)
}
