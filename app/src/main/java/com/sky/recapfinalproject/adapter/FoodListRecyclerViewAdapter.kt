package com.sky.recapfinalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sky.recapfinalproject.R
import com.sky.recapfinalproject.model.Food
import com.sky.recapfinalproject.utility.createPlaceHolder
import com.sky.recapfinalproject.utility.downloadFromInternet
import com.sky.recapfinalproject.view.FoodListFragment
import com.sky.recapfinalproject.view.FoodListFragmentDirections
import kotlinx.android.synthetic.main.food_list_recycler_row.view.*
import kotlinx.android.synthetic.main.fragment_food_details.view.*

class FoodListRecyclerViewAdapter(val listFood: ArrayList<Food>) : RecyclerView.Adapter<FoodListRecyclerViewAdapter.FoodListHolder>() {
    class FoodListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_list_recycler_row,parent,false)
        return FoodListHolder(view)
    }

    override fun onBindViewHolder(holder: FoodListHolder, position: Int) {
        holder.itemView.foodList_recyclerView_foodNameTextView.text = listFood[position].name
        holder.itemView.foodList_recyclerView_foodCaloryTextView.text = listFood[position].calory
        holder.itemView.foodList_recyclerView_foodImageView.downloadFromInternet(
            listFood[position].image, createPlaceHolder(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailsFragment(listFood.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    fun refreshData(newList: ArrayList<Food>){
        listFood.clear()
        listFood.addAll(newList)
        notifyDataSetChanged()
    }
}