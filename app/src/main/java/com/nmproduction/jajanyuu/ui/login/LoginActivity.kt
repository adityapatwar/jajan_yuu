package com.nmproduction.jajanyuu.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.ui.base.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        addFragment(LoginFragment())

    }
}