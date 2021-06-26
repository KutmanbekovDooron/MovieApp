package itacademy.kg.cinemafinder.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {


    fun provideRetrofit(): networkUtils {
        val retrofit =  Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(networkUtils::class.java)
    }




}