package com.jpm.feature_planet_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlanetListViewModel(application: Application):AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(PlanetListScreenUiState())
    val uiState: StateFlow<PlanetListScreenUiState> = _uiState
}