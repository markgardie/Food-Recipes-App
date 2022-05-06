package com.markgardie.graduatework.ui.fragments.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.markgardie.graduatework.adapters.CartAdapter
import com.markgardie.graduatework.databinding.FragmentCartBinding
import com.markgardie.graduatework.viewmodels.MainViewModel


class CartFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: CartAdapter by lazy { CartAdapter() }

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        setupRecyclerView(binding.cartRecyclerView)

        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}