package com.nmproduction.jajanyuu.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.ui.base.BaseActivity
import com.nmproduction.jajanyuu.ui.menu.account.AccountFragment
import com.nmproduction.jajanyuu.ui.menu.home.HomeFragment
import com.nmproduction.jajanyuu.ui.menu.inbox.InboxFragment
import com.nmproduction.jajanyuu.ui.menu.payment.PaymentFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.inflateMenu(R.menu.home_navigator)

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemListener)
        if (supportFragmentManager.backStackEntryCount == 0)
            bottomNavigation.selectedItemId = R.id.home
    }


    private val navigationItemListener = BottomNavigationView.OnNavigationItemSelectedListener {
        val state = supportFragmentManager.findFragmentById(R.id.container)
        when (it.itemId) {
            R.id.home -> {
                if (state !is HomeFragment) {
                    addFragment(HomeFragment())

                }
            }

            R.id.activity -> {
                if (state !is AccountFragment) {
                    addFragment(AccountFragment())

                }
            }

            R.id.payment -> {
                if (state !is PaymentFragment) {
                    addFragment(PaymentFragment())

                }
            }

            R.id.inbox -> {
                if (state !is InboxFragment) {
                    addFragment(InboxFragment())

                }
            }

            R.id.account -> {
            if (state !is AccountFragment) {
                addFragment(AccountFragment())

            }
        }

        }

        return@OnNavigationItemSelectedListener true
    }

}