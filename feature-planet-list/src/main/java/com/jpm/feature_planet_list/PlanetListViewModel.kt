package com.jpm.feature_planet_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jpm.core.domain.GetPlanetPagingDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    application: Application,
    getPlanetPagingDataUseCase: GetPlanetPagingDataUseCase
) : AndroidViewModel(application) {
    val planetPagingFlow = getPlanetPagingDataUseCase().cachedIn(viewModelScope)
}