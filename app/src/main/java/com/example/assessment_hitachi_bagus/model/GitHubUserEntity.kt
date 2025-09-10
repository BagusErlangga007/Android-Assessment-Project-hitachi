package com.example.assessment_hitachi_bagus.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "github_users")
data class GitHubUserEntity(
    @PrimaryKey val login: String,
    val avatarUrl: String,
    val htmlUrl: String
)

@Dao
interface GitHubUserDao {
    @Query("SELECT * FROM github_users WHERE login LIKE '%' || :query || '%'")
    fun searchUsers(query: String): Flow<List<GitHubUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<GitHubUserEntity>)

    @Query("DELETE FROM github_users")
    suspend fun clearUsers()
}