package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.AddShopItemUseCase
import com.example.shoppinglist.domain.EditShopItemUseCase
import com.example.shoppinglist.domain.GetShopItemByIdUseCase
import com.example.shoppinglist.domain.ShopItem

class ShopItemViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemByIdUseCase = GetShopItemByIdUseCase(repository)
    private val _errorInputName = MutableLiveData<Boolean>() // с этой переменной работаем только
    //внутри вью модели
    val errorInputName:LiveData<Boolean> //переопределяем геттер у переменной чтобы он давал
        //значение из пременной _errorInputName в другой части программы, но изменять его будет нельзя
        get() = _errorInputName
    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount:LiveData<Boolean>
        get() = _errorInputCount
    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem : LiveData<ShopItem>
        get() = _shopItem
    private val _permissionOnClose = MutableLiveData<Unit>()
    val permissionOnClose: LiveData<Unit>
        get() = _permissionOnClose
    fun addShopItem(inputName: String?, inoutCount:String?){
        val name = parseName(inputName)
        val count = parseCount(inoutCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid){
            val shopItem = ShopItem(name,count,true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }

    }
    fun getShopItemById(id: Int){
        val item = getShopItemByIdUseCase.getShopItemDyId(id)
        _shopItem.value = item
    }
    fun editShopItem(inputName: String?, inoutCount:String?){
        val name = parseName(inputName)
        val count = parseCount(inoutCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid){
             _shopItem.value?.let {
                 val item = it.copy(name = name, count = count)
                 editShopItemUseCase.editShopItem(item)
                 finishWork()
             }
        }

    }

    private fun parseName(InputName:String?):String{
        return InputName?.trim() ?: ""
    }
    private fun parseCount(inputCount:String?):Int{
        if (inputCount.isNullOrEmpty() || inputCount.toIntOrNull() == null){
            return 0
        }else{
            return inputCount?.trim()?.toInt() ?: 0
        }
    }
    private fun validateInput(name:String, count:Int):Boolean{
        var result = true
        if (name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if (count <= 0){
            _errorInputCount.value = true
            result = false
        }
        return result
    }
    public fun resetErrorInputName(){
        _errorInputName.value = false
    }
    public fun resetErrorInputCount(){
        _errorInputCount.value = false
    }
    private fun finishWork(){
        _permissionOnClose.value = Unit
    }
}