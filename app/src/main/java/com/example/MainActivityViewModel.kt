package com.example.movies


import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivityViewModel : ViewModel() {

    var moviesLiveData = MutableLiveData<List<Movie>>()

    init {
        loadMovies(Category.UPCOMING.id)
    }

    fun loadMovies(selectedIndex: Int) {

        CoroutineScope(Dispatchers.Default).launch {
            val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)

            val movieResponse = when (selectedIndex) {
                Category.POPULAR.id -> request.getMovies(Category.POPULAR.category, "ae05a6260ca61f946d7b7c2d0d8e377f", 1)
                Category.UPCOMING.id -> request.getMovies(Category.UPCOMING.category, "ae05a6260ca61f946d7b7c2d0d8e377f", 1)
                Category.NOW_PLAYING.id -> request.getMovies(Category.NOW_PLAYING.category, "ae05a6260ca61f946d7b7c2d0d8e377f", 1)
                Category.TOP_RATED.id -> request.getMovies(Category.TOP_RATED.category, "ae05a6260ca61f946d7b7c2d0d8e377f", 1)

                else -> request.getMovies(Category.UPCOMING.category, "ae05a6260ca61f946d7b7c2d0d8e377f", 1)
            }

            if(movieResponse.isSuccessful) {
                moviesLiveData.postValue(movieResponse.body()!!.results)
            } else {
                //what do i do here?
            }

        }

    }

//    fun moviePage(position: Int) {
//
//    }

//    override fun onItemClick(position: Int) {
//        Toast.makeText(MainActivity(),"Item $position clicked", Toast.LENGTH_SHORT).show()
//
//        //need to update the container with a new fragment.
//        //do i do it in MyViewModel or do i need to create another class for this?
//        //should it supposed to be a fragment or an activity?
//        //
//    }
}












