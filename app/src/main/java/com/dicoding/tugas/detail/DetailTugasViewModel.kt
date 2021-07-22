package com.dicoding.tugas.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tugas.core.domain.model.Tourism
import com.dicoding.tugas.core.domain.usecase.TourismUseCase

class DetailTugasViewModel(private val tourismUseCase: TourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) =
        tourismUseCase.setFavoriteTourism(tourism, newStatus)
}

