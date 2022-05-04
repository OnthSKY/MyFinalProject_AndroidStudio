package com.sky.recapfinalproject.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sky.recapfinalproject.model.Food

@Dao
interface FoodDAO {
// Suspend function is for applying Coroutine
    @Insert
    suspend fun insertAll(vararg food:Food) : List<Long>

    @Query("SELECT * FROM tblFood")
    suspend fun getAll() : List<Food>

    @Query("SELECT * FROM tblFood WHERE id= :foodId")
    suspend fun getFood(foodId: Int) : Food

    @Query("DELETE FROM tblFood")
    suspend fun deleteAll()
}