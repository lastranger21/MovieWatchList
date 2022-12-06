package com.example.moviewatchlist

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

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

    private lateinit var dbref : FirebaseDatabase
    private lateinit var ref : DatabaseReference
    private lateinit var rv_moviefav: RecyclerView
    private var movies=ArrayList<Movie>()
    private lateinit var viewModel : FavViewModel
    private var adapter : favoriteAdapter = favoriteAdapter(movies)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbref = FirebaseDatabase.getInstance()


        rv_moviefav=view.findViewById<RecyclerView>(R.id.favmovie_rv)
        //rv_moviefav.setHasFixedSize(true)

        //showView()
        Log.d("ADAPTER", "setFavoriteList: ")
        //ref = FirebaseDatabase.getInstance().getReference("Movie")

        showView()

        getUserData()
        Log.d("haha", "hapusi ")

        //adapter = favoriteAdapter()
        //rv_moviefav.adapter = adapter

        //viewModel = ViewModelProvider(this).get(FavViewModel::class.java)

       /*viewModel.allUsers.observe(viewLifecycleOwner, Observer {

            adapter.updateUserList(it)

        })*/
        Log.d("ADAPTER", "apakah berhasil?: ")
        val myToast=Toast.makeText(context,"Fragment favorite completed",Toast.LENGTH_SHORT)
        myToast.show()

    }
    private fun showView() {
        rv_moviefav.layoutManager= LinearLayoutManager(activity)
        rv_moviefav.adapter=favoriteAdapter(movies)
    }
    private fun deleteNote(id:String) {

        FirebaseDatabase.getInstance().getReference("Movie").child(id).removeValue()


        //Toast.makeText(mContext,"Deleted", Toast.LENGTH_LONG).show()
    }
    private fun removeUserData(movie:Movie){
        ref = FirebaseDatabase.getInstance().getReference("Movie")
        movies = arrayListOf<Movie>()
        val query= movie.id.toString()
        Log.d("pessan", "removeUserData: "+query)
        //ref= FirebaseDatabase.getInstance().getReference().child("Movie").child(movie.id.toString())

                        //val mov = userSnapshot.getRef().removeValue()
        ref?.removeEventListener(object : ValueEventListener {
            override fun onCancelled(snapshot: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.d("ADAPTER", "tidakberhasil: ")
            }
           override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children) {
                        if (query==userSnapshot.getKey()) {
                            Log.i(
                                "Firebase",                               // 👈 Log the key and value
                                "Reading Member2 from " + userSnapshot.getKey() // 👈 to know where the
                                        + ", value=" + userSnapshot.getValue()           // 👈 problem is in your
                            );
                            userSnapshot.getRef().removeValue()
                            //val mov = userSnapshot.removeValue()
                        }

                    }

                    val adapter = favoriteAdapter(movies)
                    rv_moviefav.setAdapter(adapter)
                    //adapter.notifyDataSetChanged();

                }




        }

        //ref.child("id").child(movies.getKey()).removeValue()


            })}


    private fun getUserData(){
        movies.clear()
        ref = FirebaseDatabase.getInstance().getReference("Movie")
        movies = arrayListOf<Movie>()

        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(snapshot: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.d("ADAPTER", "tidakberhasil: ")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                movies.clear();
                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children) {

                            Log.i("Firebase",                               // 👈 Log the key and value
                                "Reading Member2 from "+userSnapshot.getKey() // 👈 to know where the
                                        +", value="+userSnapshot.getValue()           // 👈 problem is in your
                            );
                            val mov = userSnapshot.getValue(Movie::class.java)
                            movies?.add(mov!!)


                    }

                    val adapter = favoriteAdapter(movies)
                    rv_moviefav.setAdapter(adapter)
                    adapter.notifyDataSetChanged();
                    Log.d("ADAPTER", "berhasil: ")
                }
                Log.d("ADAPTER", "tdiak bias: ")

            }




        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            favoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}