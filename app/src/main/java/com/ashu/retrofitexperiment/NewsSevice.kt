package com.ashu.retrofitexperiment

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey=78502fddad6844c3b0d6a9bf4249122f ///

const val BASE_URL ="https://newsapi.org/"
const val API_KEY = "78502fddad6844c3b0d6a9bf4249122f"

interface NewsInterface {




    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getheadlines(@Query("country") country:String,@Query("page") page:Int):Call<News>


    // when u call the endpoint it will generate a url
    //https://newsapi.org/v2/top-headlines?country=in&apiKey=78502fddad6844c3b0d6a9bf4249122f

}

object NewsService{

    val newsInstance : NewsInterface
    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        newsInstance= retrofit.create(NewsInterface::class.java)


    }

}