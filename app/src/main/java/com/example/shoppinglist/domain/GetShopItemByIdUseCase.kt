package com.example.shoppinglist.domain

class GetShopItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemDyId(shopItemId:Int):ShopItem{
        return shopListRepository.getShopItemDyId(shopItemId)
    }
}