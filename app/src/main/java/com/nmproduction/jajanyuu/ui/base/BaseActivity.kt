package com.nmproduction.jajanyuu.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.ui.login.LoginFragment

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mActivity = this
    }


    fun addFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
    }

    fun addFragment(fragment: Fragment, transaction: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment, fragment.javaClass.simpleName)
                .addToBackStack(null)
                .setTransition(transaction)
                .commit()
    }


    protected inline fun <reified activity> startActivity(context: Context) {
        val intent = Intent(context, activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.backStackEntryCount
        val state = supportFragmentManager.findFragmentById(R.id.container)
        if (state is LoginFragment) {
            finish()
//            exitProcess(0)
        } else
            if (fragment <= 1) {
                finish()
            } else {
                super.onBackPressed()
            }

    }
}
