package com.markgardie.graduatework.ui.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import com.markgardie.graduatework.ui.adapters.OrdersAdapter

class OrdersBinding {
    companion object {
        @BindingAdapter("ordersViewVisibility", "ordersSetData", requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
                view: View,
                ordersEntity: List<OrdersEntity>?,
                mAdapter: OrdersAdapter?
        ) {
            if (ordersEntity.isNullOrEmpty()) {
                when (view) {
                    is ImageView -> {
                        view.visibility = View.VISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.VISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.INVISIBLE
                    }
                }
            } else {
                when (view) {
                    is ImageView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is TextView -> {
                        view.visibility = View.INVISIBLE
                    }
                    is RecyclerView -> {
                        view.visibility = View.VISIBLE
                        mAdapter?.setData(ordersEntity)
                    }
                }
            }
        }
    }

}