package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()){
    //здесь мы наследуемся от ListAdapter чтобы не писать явно переопределение метода
    //set и элементы обновлялись плавно без задержек, это нам предоставляет реализация
    //ListAdapter, когда мы передаем объект ShopItemDiffCallback
    // скорость и уменьшение задержек происходит из-за того какэти операции работают в
    //ListAdapter, то есть в другом потоке


    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener:((ShopItem)-> Unit)? = null
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
        val shopItem = getItem(position)

        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener{
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        holder.view.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }
    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_PULL_SIZE = 25
    }
}