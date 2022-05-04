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
    // data will be got it from database if users touch the recyclerView on FoodListFragment to watch the food details
    private fun getDataFromSqlLite(foodId: Int){
        launch {

            val receivedData = FoodDatabase(getApplication()).foodDao().getFood(foodId)
            food.value = receivedData
        }
    }
}