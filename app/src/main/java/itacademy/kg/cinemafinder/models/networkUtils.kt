package itacademy.kg.cinemafinder.models

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface networkUtils {
    @GET("discover/movie")
    suspend fun getMovie(
        @Query("api_key") api_key : String,
        @Query("language") language : String,
        @Query("sort_by") sort_by : String,
    ):Response<Movies>

    @GET("movie/{id}/videos")
    suspend fun getVidoes(
        @Path("id") id : Int,
        @Query("api_key") api_key : String,
        @Query("language") language : String,
    ) : Response<Videos>



}