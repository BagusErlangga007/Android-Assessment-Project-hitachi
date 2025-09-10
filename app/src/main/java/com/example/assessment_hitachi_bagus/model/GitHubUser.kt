package com.example.assessment_hitachi_bagus.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



data class SearchUsersResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<GitHubUser>
)

data class GitHubUser(
    val login: String,
    val avatar_url: String,
    val html_url: String
)

