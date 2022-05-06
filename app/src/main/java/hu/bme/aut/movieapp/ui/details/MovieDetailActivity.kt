package hu.bme.aut.movieapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.movieapp.databinding.ActivityMovieDetailBinding
import kotlinx.android.synthetic.main.activity_movie_detail.*

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

        detailsViewModel.movie.observe(this, {
            binding.tvTitle.text = it.Title
            binding.tvDate.text = it.Year
            binding.tvRuntime.text = it.Runtime
            binding.tvGenre.text = it.Genre
            binding.tvDirector.text = it.Director
            binding.tvActors.text = it.Actors
            binding.Movieplot.text = it.Plot
            binding.imageView2.load(it.Poster)
        })

        detailsViewModel.getMovie(movieId)
        binding.savebtn.setOnClickListener {
            detailsViewModel.insert()
        }
        binding.btnDelete.setOnClickListener {
            detailsViewModel.delete()
        }
    }


}