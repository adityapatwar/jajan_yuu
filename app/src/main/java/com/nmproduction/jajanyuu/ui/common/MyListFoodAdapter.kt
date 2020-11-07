package com.nmproduction.jajanyuu.ui.common

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.data.model.product.Product
import com.nmproduction.jajanyuu.databinding.CardItemProductBinding
import com.nmproduction.jajanyuu.databinding.ItemListProductBinding
import com.nmproduction.jajanyuu.utils.MainUtilities


class MyListFoodAdapter(private val data: List<Product?>) :
    RecyclerView.Adapter<MyListFoodAdapter.ViewHolder>() {

    var listener: MyListFoodClickListener? = null

    inner class ViewHolder(val itemBinding: ItemListProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_product, parent, false
        )
    )

    override fun getItemCount() = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.drawable.ic_meatballs)

        Glide.with(holder.itemBinding.root).load(Uri.parse(data[position]?.imageMakanan))
            .apply(options).into(holder.itemBinding.imageProduct)

        holder.itemBinding.textName.text = data[position]?.namaMakanan
        holder.itemBinding.textPrice.text = "Rp " + data[position]?.hargaMakanan?.toInt()?.let {
            MainUtilities.localFormatNumber(
                it
            )
        }

        holder.itemBinding.viewCardProduct.setOnClickListener {
            data[position]?.let { it1 -> listener?.onItemClicked(it, it1) }
        }

        holder.itemBinding.viewCardProduct.setOnLongClickListener {
            data[position]?.let { it1 -> listener?.onLongClickListener(it, it1) }
            return@setOnLongClickListener true
        }
    }

}

interface MyListFoodClickListener {
    fun onItemClicked(view: View, makanan: Product)
    fun onLongClickListener(view: View, makanan: Product)

}
