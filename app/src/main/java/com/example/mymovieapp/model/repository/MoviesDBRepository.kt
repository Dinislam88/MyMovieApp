package com.example.mymovieapp.model.repository

import com.example.mymovieapp.data.MovieDetails
import com.example.mymovieapp.data.Movies
import retrofit2.Call

interface MoviesDBRepository {

    fun getMovies(): Call<Movies>

    fun getMovieDetails(id: Int): Call<MovieDetails>

}