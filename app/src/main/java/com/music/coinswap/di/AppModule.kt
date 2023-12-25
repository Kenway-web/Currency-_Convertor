package com.music.coinswap.di

import android.app.Application
import androidx.room.Room
import com.music.coinswap.data.local.CurrencyRateDao
import com.music.coinswap.data.local.CurrencyRateDatabase
import com.music.coinswap.data.remote.CurrencyApi
import com.music.coinswap.data.repository.CurrencyRepositoryImpl
import com.music.coinswap.domain.model.CurrencyRate
import com.music.coinswap.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // object of the currency api

    @Provides  // with this dagger knows that its a provide function and its need to provide an object.
    @Singleton // with this it will it will create one object and use it in all our app.
    fun provideCurrencyApi():CurrencyApi{
        val retrofit = Retrofit
            .Builder()
            .baseUrl(CurrencyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return  retrofit.create(CurrencyApi::class.java)
    }



    @Provides
    @Singleton
    fun provideDatabase(application: Application) : CurrencyRateDatabase {
            return Room
                .databaseBuilder(
                    application,
                    CurrencyRateDatabase::class.java,
                    "currency_db"
                )
                .build()
    }


    @Provides
    @Singleton
    fun provideRepository(
        api: CurrencyApi,
        db:CurrencyRateDatabase
    ):CurrencyRepository{
            return CurrencyRepositoryImpl(
                api=api,
                dao =db.currencyRateDao
            )
    }

}