package com.example.assessment_hitachi_bagus.viewmodel

import com.example.assessment_hitachi_bagus.model.GitHubApi
import com.example.assessment_hitachi_bagus.model.GitHubUserDao
import com.example.assessment_hitachi_bagus.model.GitHubUserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val api: GitHubApi,
    private val dao: GitHubUserDao
) {
    fun searchUsers(query: String): Flow<List<GitHubUserEntity>> {
        return dao.searchUsers(query)
    }

    suspend fun fetchAndSaveUsers(query: String) {
        val response = api.searchUsers("$query in:login type:user")
        val entities = response.items.map {
            GitHubUserEntity(
                login = it.login,
                avatarUrl = it.avatar_url,
                htmlUrl = it.html_url
            )
        }
        dao.clearUsers()
        dao.insertUsers(entities)
    }
}