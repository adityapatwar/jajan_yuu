package com.nmproduction.jajanyuu.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.databinding.FragmentLoginBinding
import com.nmproduction.jajanyuu.databinding.FragmentOrderBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment


class OrderFragment : BaseFragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}