package com.markgardie.graduatework.ui.fragments.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.markgardie.graduatework.R
import com.markgardie.graduatework.adapters.ProductsAdapter
import com.markgardie.graduatework.databinding.FragmentProductsBinding
import com.markgardie.graduatework.databinding.FragmentRecipesBinding
import com.markgardie.graduatework.ui.DetailsActivityArgs
import com.markgardie.graduatework.ui.PriceActivityArgs
import com.markgardie.graduatework.util.Constants.Companion.EKO_MARKET
import com.markgardie.graduatework.util.NetworkListener
import com.markgardie.graduatework.util.NetworkResult
import com.markgardie.graduatework.viewmodels.MainViewModel
import com.markgardie.graduatework.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    private val mAdapter by lazy { ProductsAdapter () }

    private lateinit var networkListener: NetworkListener

    private val args by navArgs<PriceActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel


        setupRecyclerView()
        requestApiData()

        return binding.root
    }

    private fun requestApiData() {
        Log.d("ProductsFragment", "requestApiData called")

        mainViewModel.getProducts(EKO_MARKET, args.ingredient.name)
        mainViewModel.productsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                            requireContext(),
                            response.message.toString(),
                            Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }


    private fun setupRecyclerView() {
        binding.priceRecyclerView.adapter = mAdapter
        binding.priceRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.priceRecyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.priceRecyclerView.hideShimmer()
    }


}