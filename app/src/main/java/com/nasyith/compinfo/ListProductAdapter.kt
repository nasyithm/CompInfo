package com.nasyith.compinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nasyith.compinfo.databinding.ItemRowProductBinding

class ListProductAdapter(private val listProduct: ArrayList<Product>) : RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowProductBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, _, _, _, _, photo) = listProduct[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listProduct[holder.adapterPosition]) }
    }

    class ListViewHolder(var binding: ItemRowProductBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Product)
    }
}