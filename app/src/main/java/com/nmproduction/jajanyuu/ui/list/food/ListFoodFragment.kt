package com.nmproduction.jajanyuu.ui.list.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nmproduction.jajanyuu.data.model.makanan.Makanan
import com.nmproduction.jajanyuu.databinding.FragmentListFoodBinding
import com.nmproduction.jajanyuu.ui.base.BaseFragment
import com.nmproduction.jajanyuu.ui.common.ListFoodAdapter
import com.nmproduction.jajanyuu.ui.common.MainRvClickListener
import com.nmproduction.jajanyuu.ui.order.OrderActivity


class ListFoodFragment : BaseFragment(), MainRvClickListener {

    private lateinit var binding: FragmentListFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val listMakanan = listOf(
            Makanan(
                "https://1.bp.blogspot.com/-r5oPlk8QS2g/XVLA8OuW40I/AAAAAAAABAs/kShANz0lliEPbCN798oS_jEjjJz-V4KHQCLcBGAs/s1600/nasgor.jpg",
                "Makaroni",
                "Rp.2000"
            ),
            Makanan(
                "https://1.bp.blogspot.com/-r5oPlk8QS2g/XVLA8OuW40I/AAAAAAAABAs/kShANz0lliEPbCN798oS_jEjjJz-V4KHQCLcBGAs/s1600/nasgor.jpg",
                "Ayam geprek",
                "Rp.2000"
            ),
            Makanan(
                "https://1.bp.blogspot.com/-r5oPlk8QS2g/XVLA8OuW40I/AAAAAAAABAs/kShANz0lliEPbCN798oS_jEjjJz-V4KHQCLcBGAs/s1600/nasgor.jpg",
                "Ayam Krispi",
                "Rp.2000"
            )
        )

        val mainAdapter = ListFoodAdapter(listMakanan)
        val recyclerView = binding.rvListItem

        mainAdapter.listener = this

        recyclerView.apply {
            this.adapter = mainAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
        }
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

    override fun onItemClicked(view: View, makanan: Makanan) {
        startActivity<OrderActivity>(requireContext())
    }

}