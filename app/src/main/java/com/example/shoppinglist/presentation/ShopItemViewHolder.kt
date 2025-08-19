package com.example.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view){//вьюхолдер нужен для
// уменьшения кол-ва вызовов методов инфлэйт и файнд вью бай айди
// и созздании общей вью из нескольких если такие есть
val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvCount = view.findViewById<TextView>(R.id.tv_count)
}