package hu.bme.aut.movieapp.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.movieapp.R
import androidx.databinding.DataBindingUtil
import coil.load
import com.google.firebase.analytics.FirebaseAnalytics
import hu.bme.aut.movieapp.databinding.MovieRowBinding
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.details.MovieDetailActivity

class MovieAdapter(private val context: Context,
                  private val moviesViewModel: MoviesViewModel
) : ListAdapter<Movie, MovieAdapter.ViewHolder>(MovieDiffCallback()) {

    private  var firebaseAnalytics = FirebaseAnalytics.getInstance(context)

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

    fun showDetails(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.KEY_MOVIE_ID, movie.imdbID)
        context.startActivity(intent)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Select movie")
        firebaseAnalytics.logEvent("Select_movie", bundle)
    }

    fun deleteItem(position: Int) {
        moviesViewModel.delete(position)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Delete one")
        firebaseAnalytics.logEvent("Delete_one", bundle)
    }

    class ViewHolder(val binding: MovieRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.imageView.load(movie.Poster)
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