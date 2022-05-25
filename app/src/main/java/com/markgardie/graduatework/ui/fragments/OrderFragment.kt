package com.markgardie.graduatework.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.markgardie.graduatework.R
import com.markgardie.graduatework.databinding.FragmentOrderBinding
import com.markgardie.graduatework.ui.adapters.OrdersAdapter
import com.markgardie.graduatework.viewmodels.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OrderFragment : Fragment() {


    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val ordersViewModel: OrdersViewModel by viewModels()
    private val mAdapter: OrdersAdapter by lazy { OrdersAdapter(requireActivity(), ordersViewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.ordersViewModel = ordersViewModel
        binding.mAdapter = mAdapter


        setupRecyclerView(binding.ordersRecyclerView)

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
            ordersViewModel.deleteAllOrders()
            showSnackBar()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSnackBar(){
        Snackbar.make(
                binding.root,
                "All orders removed.",
                Snackbar.LENGTH_SHORT
        ).setAction("Okay"){}
                .show()
    }

}