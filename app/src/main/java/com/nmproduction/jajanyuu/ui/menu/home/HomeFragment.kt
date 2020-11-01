package com.nmproduction.jajanyuu.ui.menu.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.databinding.FragmentHomeBinding
import com.nmproduction.jajanyuu.databinding.FragmentVerificationBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.list.food.ListFoodActivity


class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.imgFood.setOnClickListener {
            startActivity<ListFoodActivity>(requireContext())
        }
        binding.imgDrink.setOnClickListener {
            startActivity<ListFoodActivity>(requireContext())
        }
        binding.imgMeatBall.setOnClickListener {
            startActivity<ListFoodActivity>(requireContext())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    companion object {
        const val TAG = "HomeFragment"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}