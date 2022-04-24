package hu.bme.aut.movieapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.ui.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val repository: MovieRepository) : ViewModel() {

    var movie = MutableLiveData<Movie>()

    fun insert() = viewModelScope.launch(Dispatchers.IO) {
        movie.value?.let { repository.insert(it) }
    }

    fun getMovie(id: String) {
        repository.getMovieById(id, movie)
    }
}