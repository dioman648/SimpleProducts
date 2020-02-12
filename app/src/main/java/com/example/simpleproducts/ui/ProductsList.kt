package com.example.simpleproducts.ui


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.simpleproducts.R
import com.example.simpleproducts.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_products_list.*
import org.json.JSONArray
import java.io.InputStream
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class ProductsList : Fragment(), ProductsAdapter.OnProductClickListener {

    val JSON_NAME = "products.json"
    val products = mutableListOf<Product>()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setAdapter()

    }


    fun readJson(): String? {
        var json : String? = null
        try {
            val  inputStream: InputStream = activity!!.assets.open(JSON_NAME)
            json = inputStream.bufferedReader().use { it.readText()}
        }catch (ex: Exception){
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun setAdapter(){
        val gson = Gson()
        val json = readJson()
        val jsonArray = JSONArray(json)

        for (i in 0 until jsonArray.length()){
            products.add(gson.fromJson(jsonArray.getString(i), Product::class.java))
        }
        val mAdapter = ProductsAdapter()
        mAdapter.setProductsList(products, this)
        recycler_view.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }



    override fun onStop() {
        super.onStop()
        products.clear()
    }

    override fun onProductClickListener(position: Int) {
        val bundle = Bundle()
        val product = products[position]
        bundle.putString("id",product.id)
        bundle.putString("name",product.name)
        bundle.putString("desc",product.description)
        bundle.putString("image",product.image)

        navController.navigate(R.id.action_productsList_to_productDescription,bundle)

    }


}
