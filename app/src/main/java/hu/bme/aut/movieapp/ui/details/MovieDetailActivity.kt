package hu.bme.aut.movieapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.movieapp.databinding.ActivityMovieDetailBinding

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_MOVIE_ID = "KEY_MOVIE_ID"
    }

    private lateinit var movieId: String
    private lateinit var binding: ActivityMovieDetailBinding
    private val detailsViewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getStringExtra(KEY_MOVIE_ID)!!
        detailsViewModel.getMovie(movieId)

        detailsViewModel.movie.observe(this) {
            binding.tvTitle.text = it.Title
            binding.tvDate.text = it.Year
            binding.tvRuntime.text = it.Runtime
            binding.tvGenre.text = it.Genre
            binding.tvDirector.text = it.Director
            binding.tvActors.text = it.Actors
            binding.Movieplot.text = it.Plot
            binding.imageView2.load(it.Poster)
        }

        binding.savebtn.setOnClickListener {
            detailsViewModel.insert()
        }
    }
}