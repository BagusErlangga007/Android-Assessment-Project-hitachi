package com.example.assessment_hitachi_bagus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment_hitachi_bagus.model.GitHubUserEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val users: StateFlow<List<GitHubUserEntity>> =
        _query
            .debounce(500) // biar ga nembak API tiap ketik
            .filter { it.isNotBlank() }
            .flatMapLatest { q ->
                repository.searchUsers(q)
            }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.fetchAndSaveUsers(newQuery)
        }
    }
}