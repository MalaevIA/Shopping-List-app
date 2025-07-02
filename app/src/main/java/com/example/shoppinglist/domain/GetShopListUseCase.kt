package com.example.shoppinglist.domain

import android.widget.ListView
import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {// делаем возвращаемый тип данных LiveData
    // для автоматического измеения данных в Activity
    fun getShopList():LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}