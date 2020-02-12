package com.example.simpleproducts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleproducts.R
import com.example.simpleproducts.model.Product
import kotlinx.android.synthetic.main.product_item.view.*

class ProductsAdapter: RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    lateinit var products : List<Product>
    lateinit var  mClickListener : OnProductClickListener

    fun setProductsList(productsList: List<Product>, onProductClickListener: OnProductClickListener){
        products = productsList
        mClickListener = onProductClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item,parent,false),mClickListener)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.product_id.text = product.id
        holder.product_name.text = product.name
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductsViewHolder(itemView: View, val onProductClickListener: OnProductClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var product_id = itemView.item_product_id
        var product_name = itemView.item_product_name

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onProductClickListener.onProductClickListener(adapterPosition)
        }
    }

    interface OnProductClickListener{
        fun onProductClickListener(position: Int    )
    }
}