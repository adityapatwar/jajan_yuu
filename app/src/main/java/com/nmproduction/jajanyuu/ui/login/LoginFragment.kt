package com.nmproduction.jajanyuu.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.data.constant.ApplicationConstant
import com.nmproduction.jajanyuu.data.model.product.Product
import com.nmproduction.jajanyuu.data.model.profile.Profile
import com.nmproduction.jajanyuu.data.preferences.AlphaPreferences
import com.nmproduction.jajanyuu.databinding.FragmentLoginBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.menu.MainActivity


class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions

    private lateinit var auth: FirebaseAuth

    private lateinit var firebaseAuth: FirebaseAuth

    // Initialize Facebook Login button
    var callbackManager = CallbackManager.Factory.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupUI()
        configureGoogleSignIn()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {

            startActivity<MainActivity>(requireContext())
            activity?.finish()

        }
    }

    companion object {
        const val TAG = "LoginFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }

        fun getLaunchIntent(from: Context) = Intent(from, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), mGoogleSignInOptions)
    }

    private fun setupUI() {


        binding.cardGoogle.setOnClickListener {
            signIn()
        }

//        binding.loginFacebook.setReadPermissions("email", "public_profile")
//        binding.loginFacebook.registerCallback(callbackManager, object :
//            FacebookCallback<LoginResult> {
//            override fun onSuccess(loginResult: LoginResult) {
//                Log.d(TAG, "facebook:onSuccess:$loginResult")
//                handleFacebookAccessToken(loginResult.accessToken)
//            }
//
//            override fun onCancel() {
//                Log.d(TAG, "facebook:onCancel")
//
//            }
//
//            override fun onError(error: FacebookException) {
//                Log.d(TAG, "facebook:onError", error)
//
//            }
//        })

        binding.cardFacebook.setOnClickListener {
            startActivity<MainActivity>(requireContext())

            activity?.finish()
        }


    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")// ...
// Initialize Firebase Auth
        auth = Firebase.auth

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithCredential:success")
                val user = auth.currentUser
                //updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithCredential:failure", task.exception)
                Toast.makeText(
                    requireContext(), "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
                //updateUI(null)
            }

        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                val alphaPreferences = AlphaPreferences(requireContext())
                if (user != null) {
                    val ref2 = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PROFILE).child(user.uid)

                    val listener = object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {

                            for (h in dataSnapshot.children) {
                                val dat = h.getValue(Profile::class.java)
                                if (dat != null) {
                                    val profil = Profile(user.uid,user.displayName,user.email,user.phoneNumber)
                                    val ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PROFILE)

                                    alphaPreferences.image = user.photoUrl.toString()
                                    alphaPreferences.name = user.displayName.toString()
                                    alphaPreferences.email = user.email.toString()
                                    alphaPreferences.phone = user.phoneNumber.toString()
                                    alphaPreferences.uid = user.uid



                                    ref.child(user.uid).setValue(profil).addOnCompleteListener {
                                        startActivity<MainActivity>(requireContext())
                                        activity?.finish()
                                    }.addOnFailureListener {

                                    }
                                } else {
                                    startActivity<MainActivity>(requireContext())
                                    activity?.finish()
                                }
                            }

                        }

                        override fun onCancelled(databaseError: DatabaseError) {

                        }
                    }

                    ref2.addValueEventListener(listener)



                }

            } else {
                Toast.makeText(requireContext(), "Google sign in failed:(", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                account?.let { firebaseAuthWithGoogle(it) }

            } catch (e: ApiException) {
                Toast.makeText(requireContext(), "Google sign in failed:(", Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            // Pass the activity result back to the Facebook SDK
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }
}