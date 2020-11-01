package com.nmproduction.jajanyuu.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.ui.base.BaseActivity
import com.nmproduction.jajanyuu.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide.with(applicationContext).load(R.drawable.ic_buy)
            .into(splash_icon)

        Handler().postDelayed({
            startActivity<LoginActivity>(applicationContext)
            finish()
        }, 2000)



    }
}