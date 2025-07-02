package com.example.shoppinglist.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.DeleteShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.ShopItem


class MainViewModel: ViewModel() {//пишем ViewModel а не AndroidViewModel тк нам тут не нужен контекст
    //передаем неправильно репозиторий в конструктор use case (без DI)
    // то есть в наш класс из presentation слоя попал класс из лоя data, что неправильно по  Clean Architecture
    // data слой нисего не должен знать о presentation и наоборот
    private val repository = ShopListRepositoryImpl//передаем релизацию репозитория

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    // создаем лист из покупок через LiveData для сохранения состояния на экране и подписки на него
    //MutableLiveData это обычный LiveData но в которую мы сами можем вставлять данные и подписчики сразу получат эти данные

    val shopList = getShopListUseCase.getShopList()//полчуаем список элементов с liveData


    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }
    fun changeEnabledStateShopItem(shopItem: ShopItem){
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)//создаем полную копию нашего объекта и
        // меняем состояние enabled на противоположное
        editShopItemUseCase.editShopItem(newShopItem)//тут это работает тк сохраняется id старого
        // элемента и мы его ищем а затем меняем на новый
    }
}