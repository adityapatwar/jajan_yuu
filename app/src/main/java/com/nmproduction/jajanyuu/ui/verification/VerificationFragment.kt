package com.nmproduction.jajanyuu.ui.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.databinding.FragmentLoginBinding
import com.nmproduction.jajanyuu.databinding.FragmentVerificationBinding


class VerificationFragment : Fragment() {

    private lateinit var binding: FragmentVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVerificationBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        const val TAG = "VerificationFragment"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VerificationFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}