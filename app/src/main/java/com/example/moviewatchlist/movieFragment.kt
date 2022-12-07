package com.example.moviewatchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviewatchlist.API.Movie
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.rxjava3.core.Observable

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
    private lateinit var database: DatabaseReference
    private lateinit var viewModel: MovieViewModel
    private lateinit var editTextSearch : EditText
    private lateinit var searchButton : Button
    private var movieAdapter : MovieAdapter = MovieAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie, container, false)

    }
    private fun createButtonClickObservable(): Observable<String> {
        // 2
        return Observable.create { emitter ->
            // 3
            searchButton.setOnClickListener {
                // 4
                emitter.onNext(editTextSearch.text.toString())
            }

            // 5
            emitter.setCancellable {
                // 6
                searchButton.setOnClickListener(null)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movie=view.findViewById<RecyclerView>(R.id.movie_rv)
        editTextSearch=view.findViewById(R.id.search_editText)
        searchButton=view.findViewById(R.id.search_button)
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
                    data.putString("backdrop_path",movie.backdrop_path)
                    data.putString("overview",movie.overview)
                    data.putString("rating",movie.vote_average.toString())
                    data.putString("popularity",movie.popularity.toString())

                    val fragDetail = detailFragment()
                    fragDetail.arguments=data
                    Toast.makeText(activity,"item "+movie.title,Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.beginTransaction().apply {
                        setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        replace(R.id.fragContainer,fragDetail)
                        commit()
                    }

                }
            }
        )
        movieAdapter.setOnFavClickListener(
            object : MovieAdapter.onFavClickListener{
                override fun onFavClick(movie: Movie) {
                    val data = Bundle()
                    data.putString("title",movie.title)
                    data.putString("backdrop_path",movie.backdrop_path)
                    data.putString("overview",movie.overview)
                    data.putString("rating",movie.vote_average.toString())
                    data.putString("popularity",movie.popularity.toString())
                    database = FirebaseDatabase.getInstance().getReference("Movie")

                    database.child(movie.id.toString()).setValue(movie).addOnSuccessListener {

                    }.addOnFailureListener {

                    }
                }
            }
        )
//        ketika button search ditekan
//        searchButton.setOnClickListener{
//            var query:String = editTextSearch.text.toString()
//            viewModel.getSearchMovies(query)
//
//        }
//        ketika search button di click
        val searchTextObservable = createButtonClickObservable()

        searchTextObservable
            // 2
            .subscribe { query ->
                // 3
                viewModel.getSearchMovies(query)
            }


    }
    private fun prepareRecyclerView() {
        rv_movie.adapter=movieAdapter
        rv_movie.layoutManager=LinearLayoutManager(activity)
    }




}