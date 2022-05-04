package com.sky.recapfinalproject.utility

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SpecialSharedPreferences() {

    //Singleton
    companion object
    {
        private var sharedPreferences : SharedPreferences? = null
        private val DATE = "date"
        private var instance: SpecialSharedPreferences? = null
        private var lock = Any()
        operator fun invoke(context:Context) = instance ?: synchronized(lock){
            instance ?: create(context).also {
                instance = it
            }
        }

        private fun create(context: Context) : SpecialSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return SpecialSharedPreferences()
        }
    }
    fun recordTime(time:Long){
        sharedPreferences?.edit(commit = true){
            putLong(DATE,time)
        }
    }

    fun getRecordedTime() = sharedPreferences?.getLong(DATE,0L)
}