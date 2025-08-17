package com.example.shoppinglist.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>(){
    var shopList = listOf<ShopItem>()
        set(value){
            field = value
            notifyDataSetChanged()//говорим списку обновляться после установки значения
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        // как создавать VIew
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(view)
        //то есть создаем вью из макета
        //и создаем холдер где получаем элементы где можно вставлять значения
    }



    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        // как вставить значения внутри view
        val shopItem = shopList[position]

        holder.tvName.text = "${shopItem.name}"
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener{
            true
        }
    }

    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.tvName.setTextColor(ContextCompat.getColor(holder.view.context, android.R.color.white))
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }
    class ShopItemViewHolder(val view: View):RecyclerView.ViewHolder(view){//вьюхолдер нужен для
        // уменьшения кол-ва вызовов методов инфлэйт и файнд вью бай айди
    // и созздании общей вью из нескольких если такие есть
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun getItemCount() = shopList.size

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_PULL_SIZE = 25
    }
}