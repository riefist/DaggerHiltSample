package com.example.learndaggerhilt.data.repository

import com.example.learndaggerhilt.data.remote.CurrencyApiService
import com.example.learndaggerhilt.data.remote.response.CurrencyResponse
import com.example.learndaggerhilt.utils.ResourceState
import javax.inject.Inject

interface CurrencyRepository {
    suspend fun getRates(base: String): ResourceState<CurrencyResponse>
}

class CurrencyRepositoryImpl @Inject constructor(private val api: CurrencyApiService): CurrencyRepository {
    override suspend fun getRates(base: String): ResourceState<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null){
                ResourceState.Success(result)
            } else {
                ResourceState.Error(response.message())
            }
        } catch (e: Exception){
            ResourceState.Error(e.message ?: "An error occured")
        }
    }

}