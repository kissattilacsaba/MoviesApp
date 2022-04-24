package hu.bme.aut.movieapp.network

import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.model.SearchResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MovieService {

    @GET(".")
    fun getMovies(@Query("s")  term: String,
                  @Query("apikey") key: String): Call<SearchResult>

    @GET(".")
    fun getSingleMovie(@Query("i") id: String,
                       @Query("apikey") key: String): Call<Movie>


    @PUT(".")
    fun updateMovie(@Query("i") id: String,
                    @Query("apikey") key: String,
                    @Body movie: Movie): Call<ResponseBody>

    @DELETE(".")
    fun deleteMovie(@Query("i") id: String,
                    @Query("apikey") key: String): Call<ResponseBody>

}