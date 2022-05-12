package com.markgardie.graduatework.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.markgardie.graduatework.R
import com.markgardie.graduatework.util.Constants.Companion.INGREDIENTS_BUNDLE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {

    private val args by navArgs<ProductsActivityArgs>()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val ingredientsBundle = Bundle()
        ingredientsBundle.putParcelable(INGREDIENTS_BUNDLE, args.ingredient)

        val navController = findNavController(R.id.priceActivityNavHostFragment)
        navController.setGraph(R.navigation.products_activity_nav, ingredientsBundle)

    }
}