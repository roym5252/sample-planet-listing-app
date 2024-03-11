package com.jpm.datasource_local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalPlanet(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val gravity: String,
    val name: String,
    val orbitalPeriod: String,
    val population: String,
    val rotationPeriod: String,
    val surfaceWater: String,
    val terrain: String,
    val url: String
)