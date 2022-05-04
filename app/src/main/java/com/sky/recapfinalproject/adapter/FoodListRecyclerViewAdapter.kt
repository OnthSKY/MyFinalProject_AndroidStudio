package com.sky.recapfinalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sky.recapfinalproject.R
import com.sky.recapfinalproject.databinding.FoodListRecyclerRowBinding
import com.sky.recapfinalproject.model.Food
import com.sky.recapfinalproject.utility.createPlaceHolder
import com.sky.recapfinalproject.utility.downloadFromInternet
import com.sky.recapfinalproject.view.FoodListFragment
import com.sky.recapfinalproject.view.FoodListFragmentDirections
import kotlinx.android.synthetic.main.food_list_recycler_row.view.*
import kotlinx.android.synthetic.main.fragment_food_details.view.*

class FoodListRecyclerViewAdapter(val listFood: ArrayList<Food>) : RecyclerView.Adapter<FoodListRecyclerViewAdapter.FoodListHolder>(), FoodClickListener {
    class FoodListHolder(var view: FoodListRecyclerRowBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListHolder {
        val inflater = LayoutInflater.from(parent.context)
       // val view = inflater.inflate(R.layout.food_list_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<FoodListRecyclerRowBinding>(inflater,R.layout.food_list_recycler_row,parent,false)
        return FoodListHolder(view)
    }

    override fun onBindViewHolder(holder: FoodListHolder, position: Int) {

        holder.view.food = listFood[position]
        holder.view.clickListener = this
    /* holder.itemView.foodList_recyclerView_foodNameTextView.text = listFood[position].name
        holder.itemView.foodList_recyclerView_foodCaloryTextView.text = listFood[position].calory
        holder.itemView.foodList_recyclerView_foodImageView.downloadFromInternet(
            listFood[position].image, createPlaceHolder(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailsFragment(listFood.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun getItemCount(): Int {
        return listFood.size
    }

    // if there is a new data, recylcerView will be renewed
    fun refreshData(newList: ArrayList<Food>){
        listFood.clear()
        listFood.addAll(newList)
        notifyDataSetChanged()
    }

    override fun viewWasTouched(view: View) {
        val foodId = view.foodList_recyclerView_food_Id.text.toString().toIntOrNull()
        foodId?.let {
        val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailsFragment(it)
        Navigation.findNavController(view).navigate(action)
        }
    }
}