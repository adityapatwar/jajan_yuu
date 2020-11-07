package com.nmproduction.jajanyuu.ui.profil

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.data.preferences.AlphaPreferences
import com.nmproduction.jajanyuu.databinding.FragmentLoginBinding
import com.nmproduction.jajanyuu.databinding.FragmentProfileBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.login.LoginActivity


class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val alphaPreferences = AlphaPreferences(requireContext())

        binding.txtUserName.text = alphaPreferences.name
        binding.txtEmail.text = alphaPreferences.email
        binding.txtPhone.text = alphaPreferences.phone
        Glide.with(requireContext()).load(Uri.parse(alphaPreferences.image))
            .into(binding.imgMyProfile)

        binding.cardLogout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity<LoginActivity>(requireContext())
            activity?.finish()
        }

        binding.icBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "ProfileFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}