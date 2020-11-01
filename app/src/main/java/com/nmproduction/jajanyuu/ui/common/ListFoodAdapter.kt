package com.nmproduction.jajanyuu.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nmproduction.jajanyuu.R
import com.nmproduction.jajanyuu.data.model.makanan.Makanan
import com.nmproduction.jajanyuu.databinding.CardItemProductBinding


class ListFoodAdapter(private val data: List<Makanan>) :
    RecyclerView.Adapter<ListFoodAdapter.ViewHolder>() {

    var listener: MainRvClickListener? = null

    inner class ViewHolder(val itemBinding: CardItemProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.card_item_product, parent, false
        )
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(holder.itemBinding.root).load(data[position].imageMakanan).apply(options)
            .into(holder.itemBinding.imgList)
        holder.itemBinding.txtName.text = data[position].namaMakanan
        holder.itemBinding.card.setOnClickListener {
            listener?.onItemClicked(it, data[position])
        }
    }

}

interface MainRvClickListener {
    fun onItemClicked(view: View, makanan: Makanan)

}
