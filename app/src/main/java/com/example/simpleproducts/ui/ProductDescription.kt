package com.example.simpleproducts.ui


import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.simpleproducts.R
import kotlinx.android.synthetic.main.fragment_product_description.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDescription : Fragment() {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        productDescription_id.text = arguments?.getString("id")
        productDescription_name.text = arguments?.getString("name")
        productDescription_description.text = arguments?.getString("desc")
        val inputStream = resources.assets.open(arguments!!.getString("image").toString())
        productDescription_image.setImageDrawable(Drawable.createFromStream(inputStream,null))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.company -> {
                navController.navigate(R.id.action_to_about)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
