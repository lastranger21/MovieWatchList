package com.example.moviewatchlist

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val movies=ArrayList<MovieModel>()
    private lateinit var rv_movie:RecyclerView
    private lateinit var moviesButton:ImageView
    private lateinit var favButton:ImageView
    private  var movieFrag = movieFragment()
    private var favFrag = favoriteFragment()


    fun initData(){
        movies.add(MovieModel("The raid 1",9.9,false,"/lol"))
        movies.add(MovieModel("The raid True",9.9,true,"/lol"))
        movies.add(MovieModel("The raid True",9.9,true,"/lol"))
        movies.add(MovieModel("The raid True",9.9,true,"/lol"))
        movies.add(MovieModel("Combat",9.9,false,"/lol"))
        movies.add(MovieModel("Lenovo : END GAMING PArt II",9.9,true,"/lol"))
        movies.add(MovieModel("The roid",9.9,true,"/lol"))
        movies.add(MovieModel("The rBlack",9.9,true,"/lol"))
        movies.add(MovieModel("Fast and forious",9.9,false,"/lol"))

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        code here
        initData()
        moviesButton=findViewById(R.id.moviesButton)
        favButton=findViewById<ImageView>(R.id.favoritesButton)
        moviesButton.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragContainer,movieFrag)
                commit()
            }
        }
        favButton.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragContainer,favFrag)
                commit()
            }
        }

    }

}