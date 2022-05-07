package hu.bme.aut.movieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import hu.bme.aut.movieapp.network.MovieService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class MovieServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: MovieService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val okHttpClient = OkHttpClient
            .Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            //.client(okHttpClient)
            .build()
            .create(MovieService::class.java)

    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getMoviesTest() = runBlocking {
        //Arrange
        mockWebServer.enqueueResponse("response.json", 200)

        //Act
        val movies = service.getMovies("", "").body()

        //Assert
        assertThat(movies, `is`(notNullValue()))
        assertThat(movies!!.Search.size, equalTo(4))
        assertThat(movies.Search[0].Title, equalTo("Jurassic Park"))
    }

    @Test
    fun getSingleMovieTest() = runBlocking {
        //Arrange
        mockWebServer.enqueueResponse("movie.json", 200)

        //Act
        val movie = service.getSingleMovie("tt0107290", "").body()

        //Assert
        assertThat(movie, `is`(notNullValue()))
        assertThat(movie!!.Title, equalTo("Jurassic Park"))
        assertThat(movie.Year, equalTo("1993"))
    }

    private fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("assets/api-response/$fileName")

        val source = inputStream?.let { inputStream.source().buffer() }
        source?.let {
            enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }



}