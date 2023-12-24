package com.music.coinswap.data.local.entity

import com.music.coinswap.domain.model.CurrencyRate

fun CurrencyRateEntity.toCurrencyRate():CurrencyRate{
    return CurrencyRate(
        code=code,
        name=name,
        rate=rate
    )
}

fun CurrencyRate.toCurrencyRateEnity():CurrencyRateEntity{
    return CurrencyRateEntity(
        code=code,
        name=name,
        rate=rate
    )
}