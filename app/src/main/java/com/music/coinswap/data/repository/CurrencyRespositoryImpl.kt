package com.music.coinswap.data.repository

import com.music.coinswap.data.local.CurrencyRateDao
import com.music.coinswap.data.local.entity.toCurrencyRate
import com.music.coinswap.data.local.entity.toCurrencyRateEnity
import com.music.coinswap.data.remote.CurrencyApi
import com.music.coinswap.data.remote.DTO.toCurrencyRates
import com.music.coinswap.domain.model.CurrencyRate
import com.music.coinswap.domain.model.Resource
import com.music.coinswap.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CurrencyRepositoryImpl(
    private val api:CurrencyApi,
    private val dao:CurrencyRateDao
):CurrencyRepository {
    override fun getCurrencyRateList(): Flow<Resource<List<CurrencyRate>>> = flow{
         val localCurrencyRates =  getLocalCurrencyRates()
        emit(Resource.Success(localCurrencyRates))

        try {
            val newRates=getRemoteCurrencyRate()
            updatelocalCurrencyRates(newRates)
            emit(Resource.Success(newRates))
        }
        catch (e:IOException){
                emit(
                    Resource.Error(
                        message = "Couldn't reach server check your internet connection",
                        data = localCurrencyRates
                    )
                )
        }
        catch (e:Exception){
            emit(
                Resource.Error(
                    message = "Something Went Wrong!",
                    data = localCurrencyRates
                )
            )
        }
    }

    private suspend fun getLocalCurrencyRates():List<CurrencyRate>{
          return dao.getAllCurrencyRates().map{it.toCurrencyRate()}
    }

    private suspend fun getRemoteCurrencyRate():List<CurrencyRate>{
        val response = api.getLatestRates()
        return response.toCurrencyRates()
    }

    private suspend fun updatelocalCurrencyRates(currencyRate: List<CurrencyRate>){
        dao.upsertAll(currencyRate.map{it.toCurrencyRateEnity()})
    }

}