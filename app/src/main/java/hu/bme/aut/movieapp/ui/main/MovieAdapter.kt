package hu.bme.aut.movieapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.movieapp.R
import androidx.databinding.DataBindingUtil
import hu.bme.aut.movieapp.databinding.MovieRowBinding
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.details.MovieDetailActivity
import hu.bme.aut.movieapp.ui.main.MoviesViewModel

class MovieAdapter(private val context: Context,
                  private val moviesViewModel: MoviesViewModel
) : ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: MovieRowBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.movie_row,
            parent, false)
        binding.adapter = this
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    fun deleteMovie(movie: Movie) {
        moviesViewModel.delete(movie)
    }

    fun showDetails(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.KEY_MOVIE, movie.imdbID)
        context.startActivity(intent)
    }

    class ViewHolder(val binding: MovieRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
        }
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return false
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return false
    }
}