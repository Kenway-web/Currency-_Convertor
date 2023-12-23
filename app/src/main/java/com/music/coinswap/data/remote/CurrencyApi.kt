package com.music.coinswap.data.remote

import com.music.coinswap.data.remote.DTO.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

        @GET("v1/latest")
        suspend fun getLatestRates(
            @Query("apikey")apikey:String = API_KEY
        ): CurrencyDto

        companion object{
            const val API_KEY="fca_live_2ThxZi4tUXHkdRRd7sm2mQksZDAqMgfqPEzPZshA"
            const val BASE_URL="https://api.freecurrencyapi.com/"
        }

}

