package com.sky.recapfinalproject.service

import com.sky.recapfinalproject.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {

    // source ->https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    // the url that has a json data
    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFoods(): Single<ArrayList<Food>>
}