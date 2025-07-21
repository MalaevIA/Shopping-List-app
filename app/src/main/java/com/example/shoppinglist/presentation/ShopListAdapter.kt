package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>(){
    val list = listOf<ShopItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder { // как создавать VIew
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled, parent, false)
        return ShopItemViewHolder(view)
        //то есть создаем вью из макета
        //и создаем холдер где получаем элементы где можно вставлять значения
    }



    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) { // как вставить значения внутри view
        val shopItem = list[position]
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnLongClickListener{

            true
        }

    }

    class ShopItemViewHolder(val view: View):RecyclerView.ViewHolder(view){//вьюхолдер нужен для уменьшения кол-ва вызовов методов инфлэйт и файнд вью бай айди
    // и созздании общей вью из нескольких если такие есть
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun getItemCount() = list.size
}