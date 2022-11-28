package com.example.moviewatchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [movieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class favoriteFragment : Fragment() {
    private lateinit var rv_movie: RecyclerView
    private val movies=ArrayList<MovieModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initData()
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movie=view.findViewById<RecyclerView>(R.id.movie_rv)
        showView()
        val myToast=Toast.makeText(context,"toast message with gravity",Toast.LENGTH_SHORT)
        myToast.show()
    }
    private fun showView() {
        rv_movie.layoutManager= LinearLayoutManager(activity)
        rv_movie.adapter=MovieAdapter(movies)
    }
    fun initData(){
        movies.add(MovieModel("The raid 1",9.9,false,"/lol"))

    }


}