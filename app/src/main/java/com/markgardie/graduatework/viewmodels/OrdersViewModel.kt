package com.markgardie.graduatework.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.markgardie.graduatework.data.Repository
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OrdersViewModel @ViewModelInject constructor(
        private val repository: Repository,
        application: Application
) : AndroidViewModel(application) {


    val readOrders: LiveData<List<OrdersEntity>> = repository.local.readOrders().asLiveData()

    fun addOrder(ordersEntity: OrdersEntity) =
            viewModelScope.launch(Dispatchers.IO) {
                repository.local.addOrder(ordersEntity)
            }

    fun deleteOrder(ordersEntity: OrdersEntity) =
            viewModelScope.launch(Dispatchers.IO) {
                repository.local.deleteOrder(ordersEntity)
            }

    fun deleteAllOrders() =
            viewModelScope.launch(Dispatchers.IO) {
                repository.local.deleteAllOrders()
            }
}