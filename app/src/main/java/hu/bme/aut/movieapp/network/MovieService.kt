package hu.bme.aut.movieapp.network

import hu.bme.aut.movieapp.model.Movie
import hu.bme.aut.movieapp.model.SearchResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MovieService {

    @GET(".")
    suspend  fun getMovies(@Query("s")  term: String,
                  @Query("apikey") key: String): Response<SearchResult>
            //Call<SearchResult>

    @GET(".")
    suspend fun getSingleMovie(@Query("i") id: String,
                       @Query("apikey") key: String): Response<Movie>


    @PUT(".")
    fun updateMovie(@Query("i") id: String,
                    @Query("apikey") key: String,
                    @Body movie: Movie): Call<ResponseBody>

    @DELETE(".")
    fun deleteMovie(@Query("i") id: String,
                    @Query("apikey") key: String): Call<ResponseBody>

}