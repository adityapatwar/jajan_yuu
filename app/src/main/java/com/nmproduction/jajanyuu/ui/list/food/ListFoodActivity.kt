package com.nmproduction.jajanyuu.ui.list.food

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_list_food.*
import kotlinx.android.synthetic.main.fragment_list_food.*

class ListFoodActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_food)
        etSearch.requestFocus()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
            etSearch,
            InputMethodManager.SHOW_IMPLICIT
        )

//        etSearch.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                val intent = Intent()
//                intent.putExtra(ApplicationConstant.queryByName, etSearch.text.toString())
//                val preferences = AlphaPreferences(applicationContext)
//                preferences.history = preferences.history+","+etSearch.text.toString()
//                setResult(Activity.RESULT_OK, intent)
//                finish()
//                return@setOnEditorActionListener true
//            }
//            return@setOnEditorActionListener false
//        }


        addFragment(ListFoodFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == searchProductRequestCode) {
//            if (resultCode == Activity.RESULT_OK) {
//                val goToSearchedProduct = Intent(this, ProductActivity::class.java)
//                goToSearchedProduct.putExtra(
//                    ApplicationConstant.queryByName,
//                    data?.getStringExtra(ApplicationConstant.queryByName)
//                )
//                goToSearchedProduct.putExtra(ApplicationConstant.isAllProduct, true)
//                startActivityForResult(goToSearchedProduct, productListRequestCode)
//            }
//        } else {
//            if (data?.getStringExtra(ApplicationConstant.data).equals(ApplicationConstant.goToCart)) {
//                bottomNavigationMarket.selectedItemId = R.id.cart
//            }
//        }
    }

}