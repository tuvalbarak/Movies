package com.example.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(val movies: List<Movie>, private val listener: (Movie) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MoviesViewHolder).bind(movies[position], listener)//        return holder.bind(movies[position])
    }
    class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {


        private val photo : ImageView = itemView.findViewById(R.id.movie_photo)
        private val title : TextView = itemView.findViewById(R.id.movie_title)
        private val overview : TextView = itemView.findViewById(R.id.movie_overview)
        private val rating : TextView = itemView.findViewById(R.id.movie_rating)

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie, listener: (Movie) -> Unit) {
            Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
            title.text = "Title: " + movie.title
            overview.text = movie.overview
            if(movie.vote_average != 0.0) rating.text = "Rating: " + movie.vote_average.toString()
            else rating.text = "Rating: " + "N/A"


            itemView.setOnClickListener { listener(movie)}


        }



    }

}

























//package com.example.movies
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//
//class MoviesAdapter(val movies: List<Movie>, val listener: OnItemClickListener): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
//        return MoviesViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return movies.size
//    }
//
//    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
//        return holder.bind(movies[position])
//    }
//    inner class MoviesViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
//
//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        private val photo : ImageView = itemView.findViewById(R.id.movie_photo)
//        private val title : TextView = itemView.findViewById(R.id.movie_title)
//        private val overview : TextView = itemView.findViewById(R.id.movie_overview)
//        private val rating : TextView = itemView.findViewById(R.id.movie_rating)
//
//        @SuppressLint("SetTextI18n")
//        fun bind(movie: Movie) {
//            Glide.with(itemView.context).load("http://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
//            title.text = "Title: " + movie.title
//            overview.text = movie.overview
//            if(movie.vote_average != 0.0) rating.text = "Rating: " + movie.vote_average.toString()
//            else rating.text = "Rating: " + "N/A"
//
//        }
//
//        override fun onClick(v: View?) {
//            val position = adapterPosition
//            if(position != RecyclerView.NO_POSITION) {
//                listener.onItemClick(position)
//            }
//        }
//
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(position: Int)
//    }
//}
//
//
//
//
//
//
