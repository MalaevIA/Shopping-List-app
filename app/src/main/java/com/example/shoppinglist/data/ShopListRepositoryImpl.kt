package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {
    //инициализируем пустой список из ShopItem
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id compareTo o2.id})//это тот же наш список но мы его сортируем по id
    // элементов чтобы функция редактирования элементов не меняла элементы местами

    //Создаем объект типа  LiveData, который мы будем возвращать
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 20){
            val item = ShopItem("Name $i", i, true)
            addShopItem(item)
        }
    }//инициализация тестового списка из 10 элементов
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){//проверка на новый элемент или мы его редактируем
            shopItem.id = autoIncrementId++//сначала присваеваем id для элемента потом добавляем 1 для уже следующего добавленного элемента
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
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

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
        // делаем возвращаемый тип данных LiveData
        // для автоматического измеения данных в Activity

    //делаем копию нашего листа с помощью метода toList()^
    // чтобы главный лист нельзя было изменять из другой точки программы,
    // если же главный лист был неизменияемым мы сделали метод toMutableList()
    }

    private fun updateList(){
        shopListLiveData.postValue(shopList.toList())
    }

}