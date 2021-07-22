package com.app.favorite.di

import com.app.favorite.FavoriteViewModelDynamic
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val  favoriteModule = module {
    viewModel { FavoriteViewModelDynamic(get()) }
}