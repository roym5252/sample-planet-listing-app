package com.jpm.feature_planet_detail

import com.jpm.core.data.model.Planet

data class PlanetDetailScreenUiState(val loading:Boolean=false, val planet: Planet?=null)
