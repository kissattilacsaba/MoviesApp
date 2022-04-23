package hu.bme.aut.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hu.bme.aut.movieapp.network.MovieService
import hu.bme.aut.movieapp.ui.MovieRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(
        movieService: MovieService
    ): MovieRepository {
        return MovieRepository(movieService)
    }
}