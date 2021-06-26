package itacademy.kg.cinemafinder.models

import retrofit2.Call
import retrofit2.Response

class Repository () : RepoImpl {

    suspend fun getCurrentMovie(api_key: String, language: String, sort_by: String): Response<Movies> {
        return ServiceBuilder.provideRetrofit().getMovie(api_key, language, sort_by)
    }
    suspend fun getCurrentVidoes(id : Int,api_key: String, language: String) : Response<Videos>{
        return ServiceBuilder.provideRetrofit().getVidoes(id,api_key, language)
    }

}