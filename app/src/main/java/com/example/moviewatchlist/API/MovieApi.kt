package com.example.moviewatchlist.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/movie/popular?api_key=0f9f60f4bc37269c7cc530ea68a16a03")
    fun getPopularMovies() : Call<Movies>
    @GET("/3/search/movie?api_key=0f9f60f4bc37269c7cc530ea68a16a03&")
    fun getSearchMovies(@Query("query")query:String) : Call<Movies>
}