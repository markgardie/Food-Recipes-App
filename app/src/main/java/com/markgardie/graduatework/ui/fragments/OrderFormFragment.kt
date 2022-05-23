package com.markgardie.graduatework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.markgardie.graduatework.data.database.entities.OrdersEntity
import com.markgardie.graduatework.databinding.FragmentOrderFormBinding
import com.markgardie.graduatework.viewmodels.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OrderFormFragment : Fragment() {

    private val ordersViewModel: OrdersViewModel by viewModels()

    private val args by navArgs<OrderFormFragmentArgs>()

    private var _binding: FragmentOrderFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderFormBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val name = binding.nameEditText
        val phone = binding.phoneEditText
        val address = binding.addressEditText
        val products = args.products


        binding.confirmButton.setOnClickListener {


            if (name.length() == 0) {
                name.error = "Name is required!"
            }
            if (phone.length() == 0) {
                phone.error = "Phone is required!"
            }
            if (address.length() == 0) {
                address.error = "Address is required!"
            }
            if (name.length() != 0 && phone.length() != 0 && address.length() != 0) {
                val order = OrdersEntity(
                        0,
                        name.text.toString(),
                        phone.text.toString(),
                        address.text.toString(),
                        products
                )

                ordersViewModel.addOrder(order)
                showSnackBar()
            }

        }


        return binding.root
    }

    private fun showSnackBar(){
        Snackbar.make(
                binding.root,
                "Order added",
                Snackbar.LENGTH_SHORT
        ).setAction("Okay"){}
                .show()
    }


}