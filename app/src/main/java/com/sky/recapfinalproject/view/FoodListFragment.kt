package com.sky.recapfinalproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.recapfinalproject.R
import com.sky.recapfinalproject.adapter.FoodListRecyclerViewAdapter
import com.sky.recapfinalproject.viewModel.FoodListViewModel
import kotlinx.android.synthetic.main.food_list_recycler_row.*
import kotlinx.android.synthetic.main.fragment_food_list.*

class FoodListFragment : Fragment() {

    private lateinit var viewModel: FoodListViewModel
    private var recyclerAdapter = FoodListRecyclerViewAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)
        viewModel.getData()



        foodList_swipeRefreshLayout.setOnRefreshListener {
            viewModel.isLoading.value = true
            foodList_recyclerView.visibility = View.GONE
            foodlist_errorTextView.visibility = View.GONE
            viewModel.refreshData()
            foodList_swipeRefreshLayout.isRefreshing = false

        }
        observeLiveData()
        foodList_recyclerView.layoutManager = LinearLayoutManager(context)
        foodList_recyclerView.adapter = recyclerAdapter

    }

    fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner, Observer{ foods->
            foods?.let {
                recyclerAdapter.refreshData(it)
                    foodList_recyclerView.visibility = View.VISIBLE
                    foodList_progressBar.visibility = View.GONE
                    foodlist_errorTextView.visibility = View.GONE
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner,Observer{ loading->

            loading?.let{
                if(loading){
                    foodList_progressBar.visibility = View.VISIBLE
                    foodList_recyclerView.visibility = View.GONE
                }else{
                    foodList_recyclerView.visibility = View.VISIBLE
                    foodList_progressBar.visibility = View.GONE
                }
            }

        })

        viewModel.isSuccessful.observe(viewLifecycleOwner, Observer{successing->
            successing?.let {
                if(it == true){
                    foodlist_errorTextView.visibility = View.GONE
                    foodList_recyclerView.visibility = View.VISIBLE
                }
                else{
                    foodlist_errorTextView.visibility = View.VISIBLE
                    foodList_recyclerView.visibility = View.GONE
                }
            }
        })
    }

}