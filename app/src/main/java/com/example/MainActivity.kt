package com.example.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_page.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private val moviePageViewModel: MoviePageViewModel by viewModels()

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottom_navigation.selectedItemId = R.id.upcoming
        bottom_navigation.setOnNavigationItemSelectedListener(navListener)

        updateUI(Category.UPCOMING)

        mainActivityViewModel.moviesLiveData.observe(this, Observer {
            recyclerView.adapter = MoviesAdapter(it) { movie: Movie -> movieItemClicked(movie) }
            progress_bar.visibility = View.GONE //should it be here?
        })

    }

    private fun movieItemClicked(movie: Movie) {

        MoviePageViewModel().apply {

            val bundle = Bundle()
            bundle.putString("title", movie.title)
            bundle.putString("release_date", movie.release_date)
            bundle.putString("vote_count", movie.vote_count.toString())

            val newFragment = MoviePageViewModel()
            newFragment.arguments = bundle
            newFragment.show(supportFragmentManager, newFragment.tag)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun updateUI(categoryName: Category) {
        recyclerView.apply { layoutManager = LinearLayoutManager(this@MainActivity) }
        setPageTitle(categoryName)
    }

    ////updating the page title according to the selected category on the menu
    @SuppressLint("SetTextI18n")
    private fun setPageTitle(categoryName: Category) {
        page_title.text = categoryName.category.replace('_', ' ')
                .capitalizeWords() + " " + getString(R.string.movies)
                .capitalize(Locale.ROOT)
    }


    //Listens for changes in bottom navigation bar
    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener {

                progress_bar.visibility = View.VISIBLE

                when(bottom_navigation.selectedItemId) { //which item is selected on the menu
                    R.id.popular -> {
                        mainActivityViewModel.loadMovies(Category.POPULAR.id)
                        updateUI(Category.POPULAR)
                    }
                    R.id.upcoming -> {
                        mainActivityViewModel.loadMovies(Category.UPCOMING.id)
                        updateUI(Category.UPCOMING)
                    }
                    R.id.now_playing -> {
                        mainActivityViewModel.loadMovies(Category.NOW_PLAYING.id)
                        updateUI(Category.NOW_PLAYING)
                    }
                    R.id.top_rated -> {
                        mainActivityViewModel.loadMovies(Category.TOP_RATED.id)
                        updateUI(Category.TOP_RATED)
                    }
                }

                true
            }

    //capitalizing every first word through the sentence
    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize(Locale.ROOT) }

}
























//package com.example.movies
//
//import android.annotation.SuppressLint
//import android.app.ProgressDialog.show
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.activity.viewModels
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import kotlinx.android.synthetic.main.activity_main.*
//import java.util.*
//
//class MainActivity : AppCompatActivity(), MoviesAdapter.OnItemClickListener {
//
//    private val mainActivityViewModel: MainActivityViewModel by viewModels()
//    private val moviePageViewModel: MoviePageViewModel by viewModels()
//
//    @SuppressLint("VisibleForTests")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//        bottom_navigation.selectedItemId = R.id.upcoming
//        bottom_navigation.setOnNavigationItemSelectedListener(navListener)
//
//        updateUI(Category.UPCOMING)
//
//        mainActivityViewModel.moviesLiveData.observe(this, Observer {
//            recyclerView.adapter = MoviesAdapter(it,this)
//            progress_bar.visibility = View.GONE //should it be here?
//        })
//
//
//
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun updateUI(categoryName: Category) {
//        recyclerView.apply { layoutManager = LinearLayoutManager(this@MainActivity) }
//        setPageTitle(categoryName)
//    }
//
//    ////updating the page title according to the selected category on the menu
//    @SuppressLint("SetTextI18n")
//    private fun setPageTitle(categoryName: Category) {
//        page_title.text = categoryName.category.replace('_', ' ')
//                .capitalizeWords() + " " + getString(R.string.movies)
//                .capitalize(Locale.ROOT)
//    }
//
//
//    //Listens for changes in bottom navigation bar
//    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
//        BottomNavigationView.OnNavigationItemSelectedListener {
//
//            progress_bar.visibility = View.VISIBLE
//
//            when(bottom_navigation.selectedItemId) { //which item is selected on the menu
//                R.id.popular -> {
//                    mainActivityViewModel.loadMovies(Category.POPULAR.id)
//                    updateUI(Category.POPULAR)
//                }
//                R.id.upcoming -> {
//                    mainActivityViewModel.loadMovies(Category.UPCOMING.id)
//                    updateUI(Category.UPCOMING)
//                }
//                R.id.now_playing -> {
//                    mainActivityViewModel.loadMovies(Category.NOW_PLAYING.id)
//                    updateUI(Category.NOW_PLAYING)
//                }
//                R.id.top_rated -> {
//                    mainActivityViewModel.loadMovies(Category.TOP_RATED.id)
//                    updateUI(Category.TOP_RATED)
//                }
//            }
//
//            true
//        }
//
//    override fun onItemClick(position: Int) {
//
//        Log.i("HERE","inside onItemClick")
//
//        MoviePageViewModel().apply {
//            show(supportFragmentManager, MoviePageViewModel.TAG)
//        }
//
////        myViewModel.moviePage(position)
//
////        recyclerView.visibility = View.GONE
////        supportFragmentManager.beginTransaction().
////                replace(R.id.fragment_container, MoviePageFrag())
////                .commit()
//    }
//
//
//
////capitalizing every first word through the sentence
//    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize(Locale.ROOT) }
//
//}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//package com.example.movies
//
//import android.annotation.SuppressLint
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.TextView
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import kotlinx.android.synthetic.main.activity_main.*
//import org.w3c.dom.Text
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import java.util.*
//
///**
// * 1. Create a ViewModel class that inherits tha base view model//
// * 2. Create a function in the VM that retrieves the movies by category
// * 3. That function should postValue() the movies using a LiveData object (MutableLiveData)
// * 4. In the MainActivity, observe the movies livedata object.
// * 6. In the mainActivity: Only listener to the bottom nav and call the VM function to retrieve movies, and observe the movies livedata and update the list
// */
//class MainActivity : AppCompatActivity() {
//
//    lateinit var call: Call<Movies> //should it be lateinit or should it be assigned to null?
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        bottom_navigation.selectedItemId = R.id.upcoming //arbitrary choosing one of the items
//        bottom_navigation.setOnNavigationItemSelectedListener(navListener) //so i can take care of changes in the menu
//
//        makeApiCall() //making the API call
//        updateUI() //updating the UI
//
//    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private fun makeApiCall() {
//
//        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
//
//        when(bottom_navigation.selectedItemId) { //which item is selected on the menu
//            R.id.popular -> {
//                call = request.getMovies(Category.POPULAR.category, getString(R.string.api_key))
//                setPageTitle(Category.POPULAR)
//            }
//            R.id.upcoming -> {
//                call = request.getMovies(Category.UPCOMING.category, getString(R.string.api_key))
//                setPageTitle(Category.UPCOMING)
//            }
//            R.id.now_playing -> {
//                call = request.getMovies(Category.NOW_PLAYING.category, getString(R.string.api_key))
//                setPageTitle(Category.NOW_PLAYING)
//            }
//            R.id.top_rated -> {
//                call = request.getMovies(Category.TOP_RATED.category, getString(R.string.api_key))
//                setPageTitle(Category.TOP_RATED)
//            }
//        }
//    }
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
////updating the page title according to the selected category on the menu
//    @SuppressLint("SetTextI18n")
//    private fun setPageTitle(categoryName: Category) {
//        page_title.text = categoryName.category.replace('_', ' ')
//                .capitalizeWords() + " " + getString(R.string.movies)
//                .capitalize(Locale.ROOT)
//    }
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////will always be called after making the API call, updating the UI with the updated 'call' variable
//    private fun updateUI() {
//        call.enqueue(object : Callback<Movies>{
//            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
//                if (response.isSuccessful){
//                    progress_bar.visibility = View.GONE
//                    recyclerView.apply {
////                        setHasFixedSize(true)
//                        layoutManager = LinearLayoutManager(this@MainActivity)
//                        adapter = MoviesAdapter(response.body()!!.results)
//                    }
//                }
//            }
//            override fun onFailure(call: Call<Movies>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////Listens for changes in bottom navigation bar
//    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
//            BottomNavigationView.OnNavigationItemSelectedListener {
////                val category = menuItemToCategory(it)
////                viewModel.getMovies(category)
//                makeApiCall()
//                updateUI()
//
//                true
//            }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////capitalizing every first word through the sentence
//    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize(Locale.ROOT) }
//
//
//}
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//
////package com.example.movies
////
////import androidx.appcompat.app.AppCompatActivity
////import android.os.Bundle
////import android.util.Log
////import android.view.View
////import android.widget.Toast
////import androidx.recyclerview.widget.LinearLayoutManager
////import androidx.recyclerview.widget.RecyclerView
////import kotlinx.android.synthetic.main.activity_main.*
////import retrofit2.Call
////import retrofit2.Callback
////import retrofit2.Response
////
////class MainActivity : AppCompatActivity() {
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
////
////        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
////        val call = request.getMovies(getString(R.string.api_key))
////
////
////        call.enqueue(object : Callback<PopularMovies>{
////            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
////                if (response.isSuccessful){
////                    progress_bar.visibility = View.GONE
////                    recyclerView.apply {
////                        setHasFixedSize(true)
////                        layoutManager = LinearLayoutManager(this@MainActivity)
////                        adapter = MoviesAdapter(response.body()!!.results)
////                    }
////                }
////            }
////            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
////                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
////            }
////        })
////    }
////}