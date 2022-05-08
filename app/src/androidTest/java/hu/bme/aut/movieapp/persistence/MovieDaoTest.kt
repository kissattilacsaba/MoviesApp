package hu.bme.aut.movieapp.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.movieapp.LiveDataTestUtil.getValue
import hu.bme.aut.movieapp.model.Movie
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase

    @Before
    fun initDB() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Test
    fun insertMovieTest() = runBlocking {
        //Arrange
        val movieId = "movie1"
        val movie1 = Movie(movieId)

        //Act
        database.movieDao().insertMovie(movie1)

        //Assert
        val movie2 = database.movieDao().getMovieById(movieId)[0]
        assertThat(movie1, equalTo(movie2))
    }

    @Test
    fun getAllMoviesTest() = runBlocking {
        //Arrange
        val movie1 = Movie("movie1")
        val movie2 = Movie("movie2")
        database.movieDao().insertMovie(movie1)
        database.movieDao().insertMovie(movie2)

        //Act
        val movies = getValue(database.movieDao().getAllMovies())

        //Assert
        assertThat(movies!!.size, equalTo(2))
        assertThat(movies[0], equalTo(movie1))
        assertThat(movies[1], equalTo(movie2))
    }

    @Test
    fun deleteMovieTest() = runBlocking {
        //Arrange
        val movie1 = Movie("movie1")
        val movie2 = Movie("movie2")
        database.movieDao().insertMovie(movie1)
        database.movieDao().insertMovie(movie2)

        //Act
        database.movieDao().deleteMovie(movie2)

        //Assert
        val movies = getValue(database.movieDao().getAllMovies())
        assertThat(movies!!.size, equalTo(1))
        assertThat(movies[0], equalTo(movie1))
    }

    @Test
    fun deleteAllMoviesTest() = runBlocking {
        //Arrange
        val movie1 = Movie("movie1")
        val movie2 = Movie("movie2")
        database.movieDao().insertMovie(movie1)
        database.movieDao().insertMovie(movie2)

        //Act
        database.movieDao().deleteAllMovies()

        //Assert
        val movies = getValue(database.movieDao().getAllMovies())
        assertThat(movies!!.size, equalTo(0))
    }

    @Test
    fun updateMovieTest() = runBlocking {
        //Arrange
        val movie1 = Movie("movie1", "title1")
        val movie2 = Movie("movie1", "title2")
        database.movieDao().insertMovie(movie1)

        //Act
        database.movieDao().insertMovie(movie2)

        //Assert
        val movie = database.movieDao().getMovieById("movie1")[0]
        assertThat(movie, equalTo(movie2))
    }

    @After
    fun closeDB() {
        database.close()
    }
}