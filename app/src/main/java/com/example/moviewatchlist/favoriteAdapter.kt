package com.example.moviewatchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class favoriteAdapter(private val listMovie:ArrayList<MovieModel>) : RecyclerView.Adapter<favoriteAdapter.CardViewHolder>() {
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        memilih bagian card yang dinamis
        var gambar:ImageView = itemView.findViewById(R.id.imageView)
        var judul:TextView=itemView.findViewById(R.id.textViewJudul)
        var rating:TextView=itemView.findViewById(R.id.rating)
        var buttonTambah:Button=itemView.findViewById(R.id.row_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoriteAdapter.CardViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.rv_row,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: favoriteAdapter.CardViewHolder, position: Int) {
        val movie=listMovie[position]
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.png").into(holder.gambar)
        holder.judul.text=movie.getJudul()
        holder.rating.text=movie.Rating.toString()
        holder.buttonTambah.text="HAPUS FAVORIT"
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

}