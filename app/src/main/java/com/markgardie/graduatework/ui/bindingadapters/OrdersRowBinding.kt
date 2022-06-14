package com.markgardie.graduatework.ui.bindingadapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.markgardie.graduatework.R
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import com.markgardie.graduatework.models.ProductsList
import java.math.BigDecimal
import java.math.RoundingMode

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

        @BindingAdapter("setTotalPrice")
        @JvmStatic
        fun setTotalPrice(textView: TextView, products: ProductsList) {
            var totalPrice = 0.0

            products.products.forEach {
                totalPrice += it.price
            }

            totalPrice /= 100.toDouble()
            val totalPriceRounded = BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_EVEN)

            textView.text = "Total Price: ${totalPriceRounded}"

        }

    }
}