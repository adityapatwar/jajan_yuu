package com.nmproduction.jajanyuu.ui.profil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.ui.base.BaseActivity

class ProfileActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        addFragment(ProfileFragment())
    }
}