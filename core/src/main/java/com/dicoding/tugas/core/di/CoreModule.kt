package com.dicoding.tugas.core.di

import androidx.room.Room
import com.dicoding.tugas.core.data.TourismRepository
import com.dicoding.tugas.core.data.detailData.coreL.LocalDataSource
import com.dicoding.tugas.core.data.detailData.coreL.room.TourismDatabase
import com.dicoding.tugas.core.data.detailData.remote.RemoteDataSource
import com.dicoding.tugas.core.data.detailData.remote.network.ApiService
import com.dicoding.tugas.core.domain.repository.ITourismRepository
import com.dicoding.tugas.core.utils.AppExecutors
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import net.sqlcipher.database.SQLiteDatabase

val databaseModule = module {
    factory { get<TourismDatabase>().tourismDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            TourismDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {

    single {

        val hostname = "tourism-api.dicoding.dev"
        /* val certificatePinner = CertificatePinner.Builder()
             .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
             .build()*/
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/paJOw+DTCx1KaSMeALtM5gXuxJN4lP04qMKhSXBFa9Y=")
            .add(hostname, "sha256/qPerI4uMwY1VrtRE5aBY8jIQJopLUuBt2+GDUWMwZn4=")
            .add(hostname, "sha256/iie1VXtL7HzAMF+/PVPR9xzT80kQxdZeJ+zduCB3uj0=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tourism-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITourismRepository> {
        TourismRepository(
            get(),
            get(),
            get()
        )
    }
}