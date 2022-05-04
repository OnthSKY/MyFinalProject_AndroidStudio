package com.sky.recapfinalproject.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sky.recapfinalproject.model.Food
import com.sky.recapfinalproject.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailsViewModel(application: Application) : BaseViewModel(application) {

    val food = MutableLiveData<Food>()

    fun getData(foodId: Int){
        getDataFromSqlLite(foodId)
    }
    private fun getDataFromSqlLite(foodId: Int){
        launch {

            val receivedData = FoodDatabase(getApplication()).foodDao().getFood(foodId)
            food.value = receivedData
        }
    }
    private fun getDataOffline(){
        val elma = Food("Elma","15","16","12","10","www.test.com")

        food.value = elma
    }
}