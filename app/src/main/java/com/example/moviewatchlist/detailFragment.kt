package com.example.moviewatchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detailFragment : Fragment() {
    lateinit var imgFind:ImageView
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View=inflater.inflate(R.layout.fragment_detail, container, false)
        imgFind=view.findViewById(R.id.backdrop_detail)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arguments=arguments
        val title=this.arguments?.get("title")
        val backdrop_path=this.arguments?.get("backdrop_path")
        val overview=this.arguments?.get("overview")
        val rating=this.arguments?.get("rating")
        val popularity=this.arguments?.get("popularity")
        // get display

        try {
            Glide.with(this).load("https://image.tmdb.org/t/p/original/"+backdrop_path.toString()).into(imgFind)
        }catch (c:Exception){
            Toast.makeText(activity,"item "+c.message.toString(), Toast.LENGTH_SHORT).show()
        }
//
        activity?.findViewById<TextView>(R.id.title_detail)?.setText(title.toString())
        activity?.findViewById<TextView>(R.id.overview_detail)?.setText(overview.toString())
        activity?.findViewById<TextView>(R.id.rating_detail)?.setText(rating.toString())
        activity?.findViewById<TextView>(R.id.popularity_detail)?.setText(popularity.toString())


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}