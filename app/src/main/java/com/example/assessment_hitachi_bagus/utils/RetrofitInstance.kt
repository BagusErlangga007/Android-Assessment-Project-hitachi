package com.example.assessment_hitachi_bagus.utils

import com.example.assessment_hitachi_bagus.model.GitHubApi
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.github.com/"
    val moshi = Moshi.Builder().build()

    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(GitHubApi::class.java)
}