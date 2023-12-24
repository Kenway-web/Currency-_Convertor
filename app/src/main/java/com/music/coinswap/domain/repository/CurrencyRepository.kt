package com.music.coinswap.domain.repository

import com.music.coinswap.domain.model.CurrencyRate
import com.music.coinswap.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getCurrencyRateList(): Flow<Resource<List<CurrencyRate>>>
}