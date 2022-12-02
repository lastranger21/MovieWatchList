package com.example.moviewatchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviewatchlist.API.Movie

class FavViewModel: ViewModel() {
    private val  repository : MovieRepository
    private val _allUsers = MutableLiveData<List<Movie>>()
    val allUsers : LiveData<List<Movie>> = _allUsers


    init {
        repository = MovieRepository().getInstance()
        repository.loadUsers(_allUsers)

    }

}