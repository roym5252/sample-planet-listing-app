package com.jpm.core.data.model

import com.jpm.datasource_local.entity.LocalPlanet
import com.jpm.datasource_remote.model.RemotePlanet

data class Planet(
    val id: Long = 0,
    val name: String,
    val climate: String? = null,
    val diameter: String? = null,
    val gravity: String? = null,
    val orbitalPeriod: String? = null,
    val population: String? = null,
    val rotationPeriod: String? = null,
    val surfaceWater: String? = null,
    val terrain: String? = null
) {

    companion object {

        fun fromLocalPlanet(localPlanet: LocalPlanet): Planet {
            return Planet(
                localPlanet.id,
                localPlanet.name,
                localPlanet.climate,
                localPlanet.diameter,
                localPlanet.gravity,
                localPlanet.orbitalPeriod,
                localPlanet.population,
                localPlanet.rotationPeriod,
                localPlanet.surfaceWater,
                localPlanet.terrain
            )
        }

        fun fromRemotePlanetToLocalPlanet(remotePlanet: RemotePlanet): LocalPlanet {
            return LocalPlanet(
                climate = remotePlanet.climate,
                created = remotePlanet.created,
                diameter = remotePlanet.diameter,
                edited = remotePlanet.edited,
                gravity = remotePlanet.gravity,
                name = remotePlanet.name,
                orbitalPeriod = remotePlanet.orbitalPeriod,
                population = remotePlanet.population,
                rotationPeriod = remotePlanet.rotationPeriod,
                surfaceWater = remotePlanet.surfaceWater,
                terrain = remotePlanet.terrain,
                url = remotePlanet.url
            )
        }
    }
}
