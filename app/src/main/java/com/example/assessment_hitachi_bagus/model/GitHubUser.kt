package com.example.assessment_hitachi_bagus.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



@JsonClass(generateAdapter = true)
data class SearchUsersResponse(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "incomplete_results") val incompleteResults: Boolean,
    @Json(name = "items") val items: List<GitHubUser>
)
@JsonClass(generateAdapter = true)
data class GitHubUser(
    val login: String,
    val id: Long,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "html_url")  val htmlUrl: String
)