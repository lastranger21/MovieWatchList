package com.example.moviewatchlist

import android.content.Context
import android.content.SharedPreferences
import android.net.http.SslCertificate.saveState
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
        var buttonHapus:ImageButton=itemView.findViewById(R.id.hapus_button)
        var gambar:ImageView = itemView.findViewById(R.id.imageViewFav)
        var judul:TextView=itemView.findViewById(R.id.textViewJudulFav)
        var rating:TextView=itemView.findViewById(R.id.ratingFav)
        var status:TextView = itemView.findViewById(R.id.status)
        var is_watched: ImageButton = itemView.findViewById(R.id.is_watched)
        init{
            buttonHapus.setOnClickListener {
                glistener?.onRemoveClick(movieList[adapterPosition])

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): favoriteAdapter.CardViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.rv_row_fav,parent,false)
        return CardViewHolder(view, gListener,movieList)
    }

    override fun onBindViewHolder(holder: favoriteAdapter.CardViewHolder, position: Int) {

        val movie=movieList[position]

        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/original/"+movie.poster_path).into(holder.gambar)
        holder.judul.text=movie.title
        holder.rating.text=movie.vote_average.toString()
        holder.status.text=movie.already_watch
        //holder.buttonHapus.text="HAPUS FAVORIT"

        holder.buttonHapus.setOnClickListener {
            deleteNote(movie.id.toString()) // this needs to be the key of the note that the user clicked on
            movieList.remove(movie)
            notifyDataSetChanged()

        }
        holder.is_watched.setOnClickListener {
            if (movie.already_watch.toString()=="not watched"){
                updateNote(movie.id.toString(),movie.already_watch.toString())
                Log.d("ADAPTER", "is watched berhasil: ")
                notifyDataSetChanged()
            }else{
                updateNoted(movie.id.toString(),movie.already_watch.toString())
                Log.d("ADAPTER", "is watched gagal: ")
                notifyDataSetChanged()
            }
            //notifyDataSetChanged()

        }
        FirebaseDatabase.getInstance().getReference("Movie").child(movie.id.toString()).orderByChild(movie.title.toString())



    }

    override fun getItemCount(): Int {
        return movieList.size
    }
    private fun deleteNote(id:String) {

        FirebaseDatabase.getInstance().getReference("Movie").child(id).removeValue()


        //Toast.makeText(mContext,"Deleted", Toast.LENGTH_LONG).show()
    }
    private fun updateNote(id:String,counters:String){
        val mDatabase = FirebaseDatabase.getInstance().getReference("Movie").child(id)
        mDatabase.child("already_watch").setValue("already watched")
    }
    private fun updateNoted(id:String,counters:String){
        val mDatabase = FirebaseDatabase.getInstance().getReference("Movie").child(id)
        mDatabase.child("already_watch").setValue("not watched")
    }

}