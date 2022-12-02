package com.example.moviewatchlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviewatchlist.API.Movie
import com.google.firebase.database.*
import retrofit2.http.POST

class MovieRepository {
    private val databaseReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("Movie")

    @Volatile private var INSTANCE : MovieRepository ?= null

    fun getInstance() :MovieRepository{
        return INSTANCE ?: synchronized(this){

            val instance = MovieRepository()
            INSTANCE = instance
            instance
        }


    }


    fun loadUsers(userList : MutableLiveData<List<Movie>>){

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _userList : List<Movie> = snapshot.children.map { dataSnapshot ->

                       dataSnapshot.getValue(Movie::class.java)!!

                    }

                    userList.postValue(_userList)

                }catch (e : Exception){
                    print("gagal")

                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }
}