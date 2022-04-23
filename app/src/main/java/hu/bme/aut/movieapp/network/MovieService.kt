package hu.bme.aut.movieapp.network

import hu.bme.aut.movieapp.model.DetailedMovie
import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.model.SearchResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(".")
    fun getMovies(@Query("s")  term: String,
                  @Query("apikey") key: String): Call<SearchResult>

    @GET(".")
    fun getSingleMovie(@Query("i") id: String,
                        @Query("apikey") key: String): Call<DetailedMovie>

}