package com.music.coinswap.data.remote.DTO

import com.music.coinswap.domain.model.CurrencyRate


fun CurrencyDtO.toCurrencyRates(): List<CurrencyRate> {
    val currencyData = this.data
    return listOf(
        CurrencyRate("INR", "Indian Rupee", currencyData.INR),

    )
}