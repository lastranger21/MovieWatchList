package com.example.moviewatchlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviewatchlist.API.Movie
import com.example.moviewatchlist.API.Movies

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.CardViewHolder>() {
    private var movieList = ArrayList<Movie>()
    fun setMovieList(movieList : List<Movie>){
        this.movieList = movieList as ArrayList<Movie>
        Log.d("ADAPTER", "setMovieList: ")
        notifyDataSetChanged()
    }
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        memilih bagian card yang dinamis
        var gambar:ImageView = itemView.findViewById(R.id.imageView)
        var judul:TextView=itemView.findViewById(R.id.textViewJudul)
        var rating:TextView=itemView.findViewById(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.CardViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.rv_row,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.CardViewHolder, position: Int) {
        val movie=movieList[position]
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/original/"+movie.poster_path).into(holder.gambar)
        holder.judul.text=movie.title
        holder.rating.text=movie.vote_average.toString()
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}