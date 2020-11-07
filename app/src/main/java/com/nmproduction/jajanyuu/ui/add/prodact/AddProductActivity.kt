package com.nmproduction.jajanyuu.ui.add.prodact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.data.constant.ApplicationConstant
import com.nmproduction.jajanyuu.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_list_food.*

class AddProductActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prodact)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (intent.getStringExtra(ApplicationConstant.ID_PRODUCT)!! != "") {
            supportActionBar?.title = "Edit Product"
            addFragment(AddProductFragment.newInstance(intent.getStringExtra(ApplicationConstant.ID_PRODUCT)!!))
        } else {
            addFragment(AddProductFragment())
        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}