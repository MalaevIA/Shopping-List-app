package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItemDyId(shopItemId:Int):ShopItem

    fun getShopList(): LiveData<List<ShopItem>>// делаем возвращаемый тип данных LiveData
    // для автоматического измеения данных в Activity

}