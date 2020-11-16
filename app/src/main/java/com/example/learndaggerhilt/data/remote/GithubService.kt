package com.example.learndaggerhilt.data.remote

import com.example.learndaggerhilt.data.remote.response.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}")
    suspend fun getProfile(
        @Path("username") username: String
    ): ProfileResponse


}