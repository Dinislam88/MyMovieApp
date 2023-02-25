package com.example.mymovieapp.model.repository

import com.example.mymovieapp.Constants
import com.example.mymovieapp.data.MovieDetails
import com.example.mymovieapp.data.Movies
import com.example.mymovieapp.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {
    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<Movies> {
        return apiInterface.getMovies(Constants.APY_KEY, "en-US", 1)
    }

    override fun getMovieDetails(id: Int): Call<MovieDetails> {
        return apiInterface.getMovieDetails(id, Constants.APY_KEY)
    }

}