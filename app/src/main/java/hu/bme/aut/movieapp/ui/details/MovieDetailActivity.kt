package hu.bme.aut.movieapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.main.MoviesViewModel

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE = "KEY_MOVIE"
    }

    private lateinit var movieId: String
    private val detailsViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieId = intent.getStringExtra(KEY_MOVIE)!!
    }

    fun bindMovie(movie: Movie) {

    }

    fun insertMovie(movie: Movie) {
        detailsViewModel.insert(movie)
    }
}