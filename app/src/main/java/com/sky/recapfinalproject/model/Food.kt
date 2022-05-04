package com.sky.recapfinalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// Model
@Entity(tableName = "tblFood")
data class Food(
    @ColumnInfo(name="name")
    @SerializedName("isim")
    val name: String?,
    @ColumnInfo(name="calory")
    @SerializedName("kalori")
    val calory: String?,
    @ColumnInfo(name="carbohydrate")
    @SerializedName("karbonhidrat")
    val carbohydrate: String?,
    @ColumnInfo(name="protein")
    @SerializedName("protein")
    val protein: String?,
    @ColumnInfo(name="oil")
    @SerializedName("yag")
    val oil: String?,
    @ColumnInfo(name="image")
    @SerializedName("gorsel")
    val image: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}