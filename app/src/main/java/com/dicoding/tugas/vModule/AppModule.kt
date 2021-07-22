package com.dicoding.tugas.vModule

import com.dicoding.tugas.core.domain.usecase.TourismInteractor
import com.dicoding.tugas.core.domain.usecase.TourismUseCase
import com.dicoding.tugas.detail.DetailTugasViewModel
import com.dicoding.tugas.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
//    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailTugasViewModel(get()) }
}