package com.example.movies


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbEndpoints {

    @GET("/3/movie/{category}")
    suspend fun getMovies(@Path("category") id: String,
                          @Query("api_key") key: String ,
                          @Query("page") page: Int): Response<MovieResponse>


}


