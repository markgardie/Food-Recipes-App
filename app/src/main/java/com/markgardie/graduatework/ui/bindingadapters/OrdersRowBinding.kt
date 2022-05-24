package com.markgardie.graduatework.ui.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.markgardie.graduatework.R
import com.markgardie.graduatework.data.database.entities.OrdersEntity

class OrdersRowBinding {

    companion object {

        @BindingAdapter("setName")
        @JvmStatic
        fun setName(textView: TextView, ordersEntity: OrdersEntity) {

            textView.text = "Contact Name: ${ordersEntity.name}"
        }

        @BindingAdapter("setPhone")
        @JvmStatic
        fun setPhone(textView: TextView, ordersEntity: OrdersEntity) {

            textView.text = "Contact Phone: ${ordersEntity.phone}"
        }

        @BindingAdapter("setAddress")
        @JvmStatic
        fun setAddress(textView: TextView, ordersEntity: OrdersEntity) {

            textView.text = "Delivery Address: ${ordersEntity.address}"
        }


    }
}