package com.example.mymovieapp.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovieapp.R
import com.example.mymovieapp.data.MovieDetails
import com.example.mymovieapp.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    private val mViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mTitle: TextView
    private lateinit var mReleaseDate: TextView
    private lateinit var mScore: TextView
    private lateinit var mOverview: TextView
    private lateinit var mBanner: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val id = intent.getIntExtra("id", 0)
        initViews()
        initObservers()
        mViewModel.getMovieDetails(id)
    }

    private fun initObservers() {
        mViewModel.apply {
            movieDetails.observe(this@MovieDetailsActivity){
                setMovieInformation(it)

            }
        }
    }

    private fun setMovieInformation(movieDetails: MovieDetails?) {
        mTitle.text = movieDetails?.title
        mReleaseDate.text = movieDetails?.release_date.toString()
        mScore.text = movieDetails?.vote_average.toString()
        mOverview.text = movieDetails?.overview

        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movieDetails?.backdrop_path).into(mBanner)
        Log.d("testLogs", "onResponse Succes ${movieDetails?.backdrop_path.toString()}")
    }

    private fun initViews() {
        mTitle = findViewById(R.id.movie_details_title)
        mReleaseDate = findViewById(R.id.movie_details_date)
        mScore = findViewById(R.id.movie_details_score)
        mOverview = findViewById(R.id.movie_details_body_overview)
        mBanner = findViewById(R.id.movie_details_image_banner)
    }
}