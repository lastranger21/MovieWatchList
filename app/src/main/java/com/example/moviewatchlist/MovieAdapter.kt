package com.example.moviewatchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val listMovie:ArrayList<MovieModel>) : RecyclerView.Adapter<MovieAdapter.CardViewHolder>() {
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
        val movie=listMovie[position]
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.png").into(holder.gambar)
        holder.judul.text=movie.judul
        holder.rating.text=movie.rating.toString()
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

}