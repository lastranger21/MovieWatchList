package com.example.moviewatchlist

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviewatchlist.API.Movie
import com.example.moviewatchlist.API.Movies
import com.example.moviewatchlist.API.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Movie>>()
    fun getPopularMovies() {
        RetrofitInstance.api.getPopularMovies().enqueue(object  : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()!!.results
                    Log.d("TEST API","TEST")
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TEST API",t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<List<Movie>> {
        return movieLiveData
    }
}