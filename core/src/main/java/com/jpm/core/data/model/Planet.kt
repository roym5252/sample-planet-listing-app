package com.jpm.core.data.model

import com.jpm.datasource_local.entity.LocalPlanet
import com.jpm.datasource_remote.model.RemotePlanet

data class Planet(val id: Long = 0, val name: String) {

    companion object {

        fun fromLocalPlanet(localPlanet: LocalPlanet): Planet {
            return Planet(id=localPlanet.id,name = localPlanet.name)
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
