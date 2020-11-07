package com.nmproduction.jajanyuu.ui.menu.account

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nmproduction.jajanyuu.data.constant.ApplicationConstant
import com.nmproduction.jajanyuu.data.model.product.Product
import com.nmproduction.jajanyuu.data.preferences.AlphaPreferences
import com.nmproduction.jajanyuu.databinding.FragmentAccountBinding
import com.nmproduction.jajanyuu.ui.add.prodact.AddProductActivity
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.common.MyListFoodAdapter
import com.nmproduction.jajanyuu.ui.common.MyListFoodClickListener
import com.nmproduction.jajanyuu.ui.common.MyListFoodDecoration
import com.nmproduction.jajanyuu.ui.profil.ProfileActivity
import com.nmproduction.jajanyuu.utils.MainUtilities


class AccountFragment : BaseFragment(), MyListFoodClickListener {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var productAdapter: MyListFoodAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private var listMakanan: MutableList<Product?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val alphaPreferences = AlphaPreferences(requireContext())


        productAdapter = MyListFoodAdapter(listMakanan)
        productAdapter.listener = this
        gridLayoutManager = GridLayoutManager(
            requireContext(),
            4
        )
        val rvListFood = binding.rvListFood
        rvListFood.apply {
            this.adapter = productAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
            this.addItemDecoration(
                MyListFoodDecoration(
                    MainUtilities.intToDp(8, resources)
                )
            )

            this.layoutManager = gridLayoutManager
        }

        getData()



        binding.textName.text = alphaPreferences.name
        Glide.with(requireContext()).load(Uri.parse(alphaPreferences.image))
            .into(binding.imgProfile)

        binding.imgProfile.setOnClickListener {
            startActivity<ProfileActivity>(requireContext())
        }

        binding.cardAddProduct.setOnClickListener {
            startActivity<AddProductActivity>(requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun getData() {
        val alphaPreferences = AlphaPreferences(requireContext())
        val ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PROFILE).child(
            alphaPreferences.uid.toString()
        ).child(ApplicationConstant.PRODUCT_LIST)

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
                productAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        ref.addValueEventListener(listener)

    }

    companion object {
        const val TAG = "AccountFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


    override fun onItemClicked(view: View, makanan: Product) {
        val intent = Intent(activity, AddProductActivity::class.java)
        intent.putExtra(ApplicationConstant.ID_PRODUCT, makanan.id)
        startActivity(intent)
    }

    override fun onLongClickListener(view: View, makanan: Product) {
        val alphaPreferences = AlphaPreferences(requireContext())
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        val dbProductList =
                            FirebaseDatabase.getInstance()
                                .getReference(ApplicationConstant.PRODUCT_LIST).child(makanan.id)
                        val dbMyProduct =
                            FirebaseDatabase.getInstance().getReference(ApplicationConstant.PROFILE).child(
                                alphaPreferences.uid.toString()
                            )
                                .child(ApplicationConstant.PRODUCT_LIST).child(makanan.id)

                        dbProductList.removeValue()
                        dbMyProduct.removeValue().addOnSuccessListener {
                            getData()
                            Toast.makeText(requireContext(), "Delete successfully", Toast.LENGTH_LONG).show()
                        }


                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setMessage("Hapus Prodak ? ").setPositiveButton("Ya", dialogClickListener)
            .setNegativeButton("Tidak", dialogClickListener).show()


    }
}