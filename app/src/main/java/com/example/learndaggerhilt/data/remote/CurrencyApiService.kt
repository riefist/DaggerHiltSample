package com.example.learndaggerhilt.data.remote

import com.example.learndaggerhilt.data.remote.response.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("latest")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<CurrencyResponse>
}