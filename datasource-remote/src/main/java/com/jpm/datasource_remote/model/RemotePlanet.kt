package com.jpm.datasource_remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RemotePlanet(
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    @SerializedName("orbital_period")
    @Expose
    val orbitalPeriod: String,
    val population: String,
    //val residents: List<String>,
    @SerializedName("rotation_period")
    @Expose
    val rotationPeriod: String,
    @SerializedName("surface_water")
    @Expose
    val surfaceWater: String,
    val terrain: String,
    val url: String
)