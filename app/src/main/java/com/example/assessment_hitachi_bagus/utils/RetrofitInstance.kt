package com.example.assessment_hitachi_bagus.utils

import com.example.assessment_hitachi_bagus.model.GitHubApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): SearchUsersResponse
}

data class SearchUsersResponse(
    val items: List<GitHubUser>
)

data class GitHubUser(
    val login: String,
    val avatar_url: String,
    val html_url: String
)

object ApiClient {
    private const val BASE_URL = "https://api.github.com/"

    val api: GitHubApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }
}