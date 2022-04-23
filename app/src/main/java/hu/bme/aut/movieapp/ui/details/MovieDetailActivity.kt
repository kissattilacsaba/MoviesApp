package hu.bme.aut.movieapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.movieapp.R
import hu.bme.aut.movieapp.databinding.ActivityMainBinding
import hu.bme.aut.movieapp.databinding.ActivityMovieDetailBinding
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.main.MoviesViewModel

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE = "KEY_MOVIE"
    }

    private lateinit var movieId: String
    private lateinit var binding: ActivityMovieDetailBinding
    private val detailsViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getStringExtra(KEY_MOVIE)!!

        detailsViewModel.movie?.observe(this, {
            binding.Movieplot.text = it.Plot
        })

        detailsViewModel.getMovie(movieId)
    }

    fun bindMovie(movie: Movie) {

    }

    fun insertMovie(movie: Movie) {
        detailsViewModel.insert(movie)
    }
}