package com.nmproduction.jajanyuu.ui.add.prodact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.nmproduction.jajanyuu.data.constant.ApplicationConstant
import com.nmproduction.jajanyuu.data.model.product.Product
import com.nmproduction.jajanyuu.databinding.FragmentAddProductBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.menu.MainActivity


class AddProductFragment : BaseFragment() {

    private lateinit var binding: FragmentAddProductBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        auth = Firebase.auth

        arguments?.getString(ApplicationConstant.ID_PRODUCT, "")?.let {
            if (it != "")
                getData()
        }

        binding.cardSubmit.setOnClickListener {
            if (binding.etName.text.toString() == "")
                binding.etNameLay.error = "Tidak boleh kosong"
            else if (binding.etHarga.text.toString() == "")
                binding.etHargaLay.error = "Tidak boleh kosong"
            else {
                arguments?.getString(ApplicationConstant.ID_PRODUCT, "")?.let {
                    if (it != "")
                        updateProduct()
                    else
                        addProduct()

                }


            }

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun addProduct() {
        val user = auth.currentUser

        val product = Product(
            "",
            "",
            binding.etName.text.toString(),
            binding.etHarga.text.toString(),
            user?.displayName
        )
        val ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PRODUCT_LIST)

        product.id = ref.push().key.toString()
        ref.child(product.id).setValue(product).addOnCompleteListener {

            val ref2 = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PROFILE)

            ref2.child(user!!.uid).child(ApplicationConstant.PRODUCT_LIST).child(product.id)
                .setValue(product).addOnCompleteListener {
                    activity?.onBackPressed()
                }.addOnFailureListener {

                }


        }.addOnFailureListener {

        }
    }

    fun updateProduct() {
        val user = auth.currentUser

        val product = Product(
            "",
            "",
            binding.etName.text.toString(),
            binding.etHarga.text.toString(),
            user?.displayName
        )
        arguments?.getString(ApplicationConstant.ID_PRODUCT, "")?.let {
            product.id = it
        }

        val ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PRODUCT_LIST)


        ref.child(product.id).setValue(product).addOnCompleteListener {

            val ref2 = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PROFILE)

            ref2.child(user!!.uid).child(ApplicationConstant.PRODUCT_LIST).child(product.id)
                .setValue(product).addOnCompleteListener {
                    activity?.onBackPressed()
                }.addOnFailureListener {

                }


        }.addOnFailureListener {

        }
    }

    fun getData() {

        val ref = FirebaseDatabase.getInstance().getReference(ApplicationConstant.PRODUCT_LIST)
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val it = dataSnapshot.getValue(Product::class.java)
                if (it != null) {
                    binding.etName.setText(it.namaMakanan)
                    binding.etHarga.setText(it.hargaMakanan)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        arguments?.getString(ApplicationConstant.ID_PRODUCT, "")?.let {
            ref.child(it).addValueEventListener(listener)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(id: String): AddProductFragment {
            val fragment = AddProductFragment()
            val bundle = Bundle()
            bundle.putString(ApplicationConstant.ID_PRODUCT, id)
            fragment.arguments = bundle
            return fragment
        }
    }
}