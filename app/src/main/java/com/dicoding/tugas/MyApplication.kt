package com.dicoding.tugas

import android.app.Application
import com.dicoding.tugas.core.di.databaseModule
import com.dicoding.tugas.core.di.networkModule
import com.dicoding.tugas.core.di.repositoryModule
import com.dicoding.tugas.vModule.useCaseModule
import com.dicoding.tugas.vModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}