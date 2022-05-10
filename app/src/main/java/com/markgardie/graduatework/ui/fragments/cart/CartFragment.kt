package com.markgardie.graduatework.ui.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.adapters.CartAdapter
import com.markgardie.graduatework.data.database.entities.ProductEntity
import com.markgardie.graduatework.databinding.FragmentCartBinding
import com.markgardie.graduatework.util.observeOnce
import com.markgardie.graduatework.viewmodels.MainViewModel
import com.markgardie.graduatework.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private val mAdapter: CartAdapter by lazy { CartAdapter() }

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    var productEntities = mutableListOf<ProductEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        setupRecyclerView(binding.cartRecyclerView)
        readDatabase()

        return binding.root
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readCart.observeOnce(viewLifecycleOwner, Observer { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database)
                    productEntities.addAll(database)
                }
            })
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}