package com.markgardie.graduatework.ui.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.markgardie.graduatework.R
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import com.markgardie.graduatework.databinding.OrdersRowLayoutBinding
import com.markgardie.graduatework.util.RecipesDiffUtil
import com.markgardie.graduatework.viewmodels.OrdersViewModel
import kotlinx.android.synthetic.main.orders_row_layout.view.*

class OrdersAdapter(
        private val requireActivity: FragmentActivity,
        private val ordersViewModel: OrdersViewModel
): RecyclerView.Adapter<OrdersAdapter.MyViewHolder>(), ActionMode.Callback  {


    private lateinit var mActionMode: ActionMode
    private lateinit var rootView: View

    private var orders = emptyList<OrdersEntity>()

    private var multiSelection = false

    private var selectedOrders = arrayListOf<OrdersEntity>()
    private var myViewHolders = arrayListOf<MyViewHolder>()

    class MyViewHolder(private val binding: OrdersRowLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ordersEntity: OrdersEntity) {
            binding.ordersEntity = ordersEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = OrdersRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        myViewHolders.add(holder)

        rootView = holder.itemView.rootView

        val currentOrder = orders[position]
        holder.bind(currentOrder)

        holder.itemView.delete_orders_imageView.setOnClickListener {
            ordersViewModel.deleteOrder(currentOrder)
            showSnackBar("Order has been removed")
        }

        holder.itemView.ordersRowLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, currentOrder)
            }
        }

        holder.itemView.ordersRowLayout.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentOrder)
                true
            } else {
                multiSelection = false
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    fun setData(newData: List<OrdersEntity>) {
        val recipesDiffUtil = RecipesDiffUtil(orders, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        orders = newData

        diffUtilResult.dispatchUpdatesTo(this)

    }

    private fun applySelection(holder: MyViewHolder, currentOrder: OrdersEntity) {
        if (selectedOrders.contains(currentOrder)) {
            selectedOrders.remove(currentOrder)
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
            applyActionModeTitle()
        } else {
            selectedOrders.add(currentOrder)
            changeRecipeStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary)
            applyActionModeTitle()
        }
    }

    private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.itemView.ordersRowLayout.setBackgroundColor(
                ContextCompat.getColor(requireActivity, backgroundColor)
        )
        holder.itemView.ordersCardView.strokeColor =
                ContextCompat.getColor(requireActivity, strokeColor)
    }

    private fun applyActionModeTitle(){
        when(selectedOrders.size) {
            0 -> {
                mActionMode.finish()
            }
            1 -> {
                mActionMode.title = "${selectedOrders.size} item selected"
            }
            else -> {
                mActionMode.title = "${selectedOrders.size} items selected"
            }
        }
    }

    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        mActionMode = actionMode!!
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {

        if(menu?.itemId == R.id.delete_favorite_recipe_menu){
            selectedOrders.forEach {
                ordersViewModel.deleteOrder(it)
            }
            showSnackBar("${selectedOrders.size} Orders/s removed.")

            multiSelection = false
            selectedOrders.clear()
            actionMode?.finish()
        }

        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {
        myViewHolders.forEach { holder ->
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
        }
        multiSelection = false
        selectedOrders.clear()
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int){
        requireActivity.window.statusBarColor =
                ContextCompat.getColor(requireActivity, color)
    }

    fun clearContextualActionMode() {
        if (this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(
                rootView,
                message,
                Snackbar.LENGTH_SHORT
        ).setAction("Okay"){}
                .show()
    }


}