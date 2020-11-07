package com.nmproduction.jajanyuu.ui.list.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nmproduction.jajanyuu.data.constant.ApplicationConstant
import com.nmproduction.jajanyuu.data.model.product.Product
import com.nmproduction.jajanyuu.databinding.FragmentListFoodBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.common.ListFoodAdapter
import com.nmproduction.jajanyuu.ui.common.MainRvClickListener
import com.nmproduction.jajanyuu.ui.order.OrderActivity


class ListFoodFragment : BaseFragment(), MainRvClickListener {

    private lateinit var binding: FragmentListFoodBinding
    private lateinit var mainAdapter : ListFoodAdapter
    private var listMakanan: MutableList<Product?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainAdapter = ListFoodAdapter(listMakanan)
        mainAdapter.listener = this
        val recyclerView = binding.rvListItem
        recyclerView.apply {
            this.adapter = mainAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }


    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    fun getData() {
        val ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PRODUCT_LIST)
        val data: MutableList<Product?> = mutableListOf()


        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                data.clear()

                for (h in dataSnapshot.children) {
                    val it = h.getValue(Product::class.java)
                    if (it != null) {
                        data.add(it)
                    }
                }
                listMakanan.clear()

                data.let { listMakanan.addAll(it) }
                mainAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        ref.addValueEventListener(listener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFoodFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClicked(view: View, makanan: Product) {
        startActivity<OrderActivity>(requireContext())
    }

}