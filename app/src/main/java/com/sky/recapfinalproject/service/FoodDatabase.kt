package com.sky.recapfinalproject.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sky.recapfinalproject.model.Food

@Database(entities = arrayOf(Food::class), version = 3)
abstract class FoodDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDAO

    //Singleton
    companion object{

        @Volatile private var instance : FoodDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): FoodDatabase = Room.databaseBuilder(context.applicationContext,FoodDatabase::class.java,"foodDatabase").build()
    }
}