package com.example.assessment_hitachi_bagus.model

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String
    ): SearchUsersResponse
}