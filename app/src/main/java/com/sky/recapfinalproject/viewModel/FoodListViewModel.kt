package com.sky.recapfinalproject.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.sky.recapfinalproject.model.Food
import com.sky.recapfinalproject.service.FoodApiService
import com.sky.recapfinalproject.service.FoodDatabase
import com.sky.recapfinalproject.utility.SpecialSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application) : BaseViewModel(application) {

    val foods = MutableLiveData<ArrayList<Food>>()
    val isSuccessful = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    private val updatingTime = 0.8 * 60 * 1000 * 1000 * 1000L
    private val foodApiService = FoodApiService()
    private val disposable = CompositeDisposable()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

    fun getData(){
        val dateNow = System.nanoTime()
        val timeOfSavingData = specialSharedPreferences.getRecordedTime()
        if(timeOfSavingData != null && timeOfSavingData != 0L && System.nanoTime() - timeOfSavingData < updatingTime){
            getDataFromDatabase()
        }else{
            getDataFromInternet()
        }
    }

    fun refreshData(){
        getDataFromInternet()
    }
    private fun getDataFromInternet(){
        isLoading.value = true

        disposable.add(

            foodApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith( object : DisposableSingleObserver<ArrayList<Food>>() {
                    override fun onSuccess(t: ArrayList<Food>) {
                        storageInSqlLite(t)
                        Toast.makeText(getApplication(), "Verileri internetten aldık ", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        isSuccessful.value = false
                        isLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }
    private fun getDataFromDatabase(){
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val listFood = arrayListOf<Food>()
            listFood.addAll(dao.getAll())
            showData(listFood)
            Toast.makeText(getApplication(), "Veriler room'dan çekildi", Toast.LENGTH_SHORT).show()
        }
    }
    private fun storageInSqlLite(listFood : ArrayList<Food>){
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAll()
            val listId = dao.insertAll(*listFood.toTypedArray())

            var i = 0
            while(i < listId.size){
                listFood[i].id = listId[i].toInt()
                i +=1
            }
            specialSharedPreferences.recordTime(System.nanoTime())
            showData(listFood)
        }
    }
    private fun showData(t:ArrayList<Food>){
        foods.value = t
        isSuccessful.value = true
        isLoading.value = false
    }
    private fun getDataOffline() {
        isLoading.value = true

        val muz = Food("Muz","15","16","12","10","www.test.com")
        val elma = Food("Elma","15","16","12","10","www.test.com")
        val armut = Food("Armut","15","16","12","10","www.test.com")

        val listFood = arrayListOf<Food>(muz,elma,armut)
        foods.value = listFood
        isSuccessful.value = true
        isLoading.value = false
    }

}