package com.markgardie.graduatework.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.markgardie.graduatework.R
import com.markgardie.graduatework.ui.fragments.products.ProductsFragment
import com.markgardie.graduatework.util.Constants.Companion.INGREDIENTS_BUNDLE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class PriceActivity : AppCompatActivity() {

    private val args by navArgs<PriceActivityArgs>()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price)

        val ingredientsBundle = Bundle()
        ingredientsBundle.putParcelable(INGREDIENTS_BUNDLE, args.ingredient)

        val navController = findNavController(R.id.priceActivityNavHostFragment)
        navController.setGraph(R.navigation.price_activity_nav, ingredientsBundle)

    }
}