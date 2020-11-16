package com.example.learndaggerhilt.data.repository

import com.example.learndaggerhilt.data.remote.GithubService
import com.example.learndaggerhilt.data.remote.response.ProfileResponse
import javax.inject.Inject

interface GithubRepository {
    suspend fun getProfile(username: String): ProfileResponse
}

class GithubRepositoryImpl @Inject constructor(private val githubService: GithubService): GithubRepository {

    override suspend fun getProfile(username: String): ProfileResponse {
        return githubService.getProfile(username)
    }

}