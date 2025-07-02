package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            Log.d("MainActivityTest", it.toString())//2025-06-13 17:06:45.051  6976-6976  MainActivityTest        com.example.shoppinglist             D  [ShopItem(name=Name 0, count=0, enabled=true, id=0), ShopItem(name=Name 1, count=1, enabled=true, id=1), ShopItem(name=Name 2, count=2, enabled=true, id=2), ShopItem(name=Name 3, count=3, enabled=true, id=3), ShopItem(name=Name 4, count=4, enabled=true, id=4), ShopItem(name=Name 5, count=5, enabled=true, id=5), ShopItem(name=Name 6, count=6, enabled=true, id=6), ShopItem(name=Name 7, count=7, enabled=true, id=7), ShopItem(name=Name 8, count=8, enabled=true, id=8), ShopItem(name=Name 9, count=9, enabled=true, id=9)]
        }//подписка на элемент



    }
}