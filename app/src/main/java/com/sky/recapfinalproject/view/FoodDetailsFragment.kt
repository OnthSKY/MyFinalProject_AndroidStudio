package com.sky.recapfinalproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.sky.recapfinalproject.R
import com.sky.recapfinalproject.utility.createPlaceHolder
import com.sky.recapfinalproject.utility.downloadFromInternet
import com.sky.recapfinalproject.viewModel.FoodDetailsViewModel
import kotlinx.android.synthetic.main.fragment_food_details.*

class FoodDetailsFragment : Fragment() {

    private lateinit var viewModel: FoodDetailsViewModel
    private var foodId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            foodId = FoodDetailsFragmentArgs.fromBundle(it).foodId
            println("int is ${foodId}")
        }
        viewModel = ViewModelProvider(this).get(FoodDetailsViewModel::class.java)
        viewModel.getData(foodId)
        observerLiveData()

    }

    fun observerLiveData(){
        viewModel.food.observe(viewLifecycleOwner, Observer{ food ->
            food?.let {
                context?.let {
                    foodDetails_foodImage.downloadFromInternet(food.image, createPlaceHolder(it))
                }
                foodDetails_foodName.text = food.name
                foodDetails_foodCalory.text = food.calory
                foodDetails_foodCarbohydrate.text = food.carbohydrate
                foodDetails_foodOil.text = food.oil

            }
        })
    }
}