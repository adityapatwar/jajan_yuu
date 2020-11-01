package com.nmproduction.jajanyuu.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.databinding.FragmentLoginBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.menu.MainActivity


class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.cardFacebook.setOnClickListener {
            startActivity<MainActivity>(requireContext())

            activity?.finish()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {
        const val TAG = "LoginFragment"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}