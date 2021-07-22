package com.dicoding.tugas.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tugas.core.domain.usecase.TourismUseCase

class MapsViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val tourism = tourismUseCase.getAllTourism().asLiveData()
}