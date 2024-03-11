package com.jpm.feature_planet_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jpm.core.data.repository.PlanetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(application:Application, private val planetRepository: PlanetRepository):AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(PlanetDetailScreenUiState(true, null))
    val uiState: StateFlow<PlanetDetailScreenUiState> = _uiState

    fun getPlanetDetail(planetId:Long){

        viewModelScope.launch {
            delay(200)
            planetRepository.getPlanet(planetId).collectLatest {
                _uiState.value = PlanetDetailScreenUiState(false,it)
            }
        }
    }
}