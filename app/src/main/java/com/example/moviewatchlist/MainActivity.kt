package com.example.moviewatchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moviewatchlist.API.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val movies=ArrayList<Movie>()
    private lateinit var rv_movie:RecyclerView
    private lateinit var moviesButton:ImageView
    private lateinit var favButton:ImageView
    private  var movieFrag = movieFragment()
    private var favFrag = favoriteFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        code here
        moviesButton=findViewById(R.id.moviesButton)
        favButton=findViewById<ImageView>(R.id.favoritesButton)
        moviesButton.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                replace(R.id.fragContainer,movieFrag)
                commit()
            }
        }
        favButton.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                replace(R.id.fragContainer,favFrag)
                commit()
            }
        }


    }

}