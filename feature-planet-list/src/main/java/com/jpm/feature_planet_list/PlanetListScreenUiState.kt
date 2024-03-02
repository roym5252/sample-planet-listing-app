package com.jpm.feature_planet_list

import com.jpm.core.data.Planet

data class PlanetListScreenUiState (
    val loading: Boolean = true,
    val products:List<Planet>? = null,
    val showReloadIcon: Boolean = false,
    val infoMessage: Int? = null
)
