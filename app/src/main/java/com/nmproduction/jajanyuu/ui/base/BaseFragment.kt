package com.nmproduction.jajanyuu.ui.base


import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    protected fun Context.showMessage(message: String,showingView: View) {
        try {
            showSnackBar(message,showingView)
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    protected inline fun <reified act> startActivity(context: Context) {
        val intent = Intent(context, act::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    protected fun showSnackBar(message: String?, showingView: View) {
        Snackbar.make(showingView, message.toString(), Snackbar.LENGTH_LONG).show()
    }


}
