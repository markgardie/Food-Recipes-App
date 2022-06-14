package com.markgardie.graduatework.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.markgardie.graduatework.R
import com.markgardie.graduatework.ui.adapters.CartAdapter
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.databinding.FragmentCartBinding
import com.markgardie.graduatework.models.Product
import com.markgardie.graduatework.models.ProductsList
import com.markgardie.graduatework.util.observeOnce
import com.markgardie.graduatework.viewmodels.MainViewModel
import com.markgardie.graduatework.viewmodels.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CartFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: CartAdapter by lazy { CartAdapter(requireActivity() ,mainViewModel) }
    private var products = mutableListOf<Product>()

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter

        setHasOptionsMenu(true)

        setupRecyclerView(binding.cartRecyclerView)

        mainViewModel.readCart.observe(viewLifecycleOwner, Observer { viewmodel_products ->
            for (cart_product in viewmodel_products) {
                products.add(cart_product.product)
            }
        })

        val productList = ProductsList(products)

        val action = CartFragmentDirections.actionCartFragmentToOrderFormFragment(productList)

        binding.orderButton.setOnClickListener {
            findNavController().navigate(action)
        }

        return binding.root
    }



    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mAdapter.clearContextualActionMode()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAll_favorite_recipes_menu){
            mainViewModel.removeAllFromCart()
            showSnackBar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSnackBar(){
        Snackbar.make(
                binding.root,
                "All products removed.",
                Snackbar.LENGTH_SHORT
        ).setAction("Okay"){}
                .show()
    }

}