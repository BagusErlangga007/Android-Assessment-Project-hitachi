package com.example.assessment_hitachi_bagus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment_hitachi_bagus.model.GitHubUserDao
import com.example.assessment_hitachi_bagus.model.GitHubUserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel(private val UserDao: GitHubUserDao) : ViewModel() {
    private val _user = MutableStateFlow<GitHubUserEntity?>(null)
    val user: StateFlow<GitHubUserEntity?> = _user

    fun loadUser(login: String) {
        viewModelScope.launch {
            _user.value = UserDao.getUserByLogin(login)
        }
    }
}