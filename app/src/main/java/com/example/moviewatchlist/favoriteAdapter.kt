package com.example.moviewatchlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviewatchlist.API.Movie
import com.google.firebase.database.FirebaseDatabase

class favoriteAdapter(var movieList: ArrayList<Movie>) : RecyclerView.Adapter<favoriteAdapter.CardViewHolder>() {



    private   var gListener : onRemoveClickListener?=null
    private lateinit var viewModelFav: FavViewModel
    //private var movieList = ArrayList<Movie>()
    interface onRemoveClickListener{
        fun onRemoveClick(movie: Movie)
    }
    fun setOnRemoveClickListener(listener: onRemoveClickListener){
        gListener = listener
    }
    class CardViewHolder(itemView: View, glistener: onRemoveClickListener?, movieList: List<Movie>) : RecyclerView.ViewHolder(itemView) {
        //        memilih bagian card yang dinamis
        var buttonTambah:Button=itemView.findViewById(R.id.row_button)
        var gambar:ImageView = itemView.findViewById(R.id.imageView)
        var judul:TextView=itemView.findViewById(R.id.textViewJudul)
        var rating:TextView=itemView.findViewById(R.id.rating)

        init{
            buttonTambah.setOnClickListener {
                glistener?.onRemoveClick(movieList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoriteAdapter.CardViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.rv_row,parent,false)
        return CardViewHolder(view, gListener,movieList)
    }

    override fun onBindViewHolder(holder: favoriteAdapter.CardViewHolder, position: Int) {
        val movie=movieList[position]
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/original/"+movie.poster_path).into(holder.gambar)
        holder.judul.text=movie.title
        holder.rating.text=movie.vote_average.toString()
        holder.buttonTambah.text="HAPUS FAVORIT"
        holder.buttonTambah.setOnClickListener {
            deleteNote(movie.id.toString()) // this needs to be the key of the note that the user clicked on

            movieList.remove(movie)
            notifyDataSetChanged()
        }

        //holder.buttonTambah.setOnClickListener {  viewModelFav.add(movie) }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
    private fun deleteNote(id:String) {

        FirebaseDatabase.getInstance().getReference("Movie").child(id!!).removeValue()


        //Toast.makeText(mContext,"Deleted", Toast.LENGTH_LONG).show()
    }



}