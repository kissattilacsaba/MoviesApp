package hu.bme.aut.movieapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.movieapp.model.DetailedMovie
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val repository: MovieRepository
) : ViewModel() {
    var movie = MutableLiveData<DetailedMovie>(DetailedMovie("", ""))

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movie)
    }

    fun getMovie(id: String) {
        repository.getMovieById(id, movie)
    }
}