package com.example.moviewatchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviewatchlist.API.Movie

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [movieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class movieFragment : Fragment() {
    private lateinit var rv_movie: RecyclerView
    private val movies=ArrayList<Movie>()
    private lateinit var viewModel: MovieViewModel
    private var movieAdapter : MovieAdapter = MovieAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movie=view.findViewById<RecyclerView>(R.id.movie_rv)
        prepareRecyclerView()

        viewModel=ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovies()
        viewModel.observeMovieLiveData().observe(viewLifecycleOwner, Observer {movielist->
            movieAdapter.setMovieList(movielist)
        })
//        ketika item pada recycler view ditekan
        movieAdapter.setOnItemClickListener(
            object : MovieAdapter.onItemClickListener{
                override fun onItemClick(movie: Movie) {
                    val data = Bundle()
                    data.putString("title",movie.title)

                    val fragDetail = detailFragment()
                    fragDetail.arguments=data
                    Toast.makeText(activity,"item "+movie.title,Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragContainer,fragDetail)
                        commit()
                    }

                }
            }
        )


    }
    private fun prepareRecyclerView() {
        rv_movie.adapter=movieAdapter
        rv_movie.layoutManager=LinearLayoutManager(activity)
    }




}