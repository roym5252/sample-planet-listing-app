package com.jpm.datasource_remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemotePlanetListResponse(
    val count: Int,
    val next: String,
    val previous: Any,

    @SerializedName("results")
    @Expose
    val remotePlanets: List<RemotePlanet>
)