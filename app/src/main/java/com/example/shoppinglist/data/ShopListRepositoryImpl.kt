package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    //инициализируем пустой список из ShopItem
    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }//инициализация тестового списка из 10 элементов
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){//проверка на новый элемент или мы его редактируем
            shopItem.id = autoIncrementId++//сначала присваеваем id для элемента потом добавляем 1 для уже следующего добавленного элемента
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItemDyId(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItemDyId(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not Found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()//делаем копию нашего листа с помощью метода toList()^
    // чтобы главный лист нельзя было изменять из другой точки программы,
    // если же главный лист был неизменияемым мы сделали метод toMutableList()
    }

}