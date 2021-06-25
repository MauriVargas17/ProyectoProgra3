package com.eabmodel.juegazosgabazo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import com.eabmodel.juegazosgabazo.objects.Product

class ShopwindowAdapter(val context: Context, val list: List<Product>): RecyclerView.Adapter<ProductViewHolder>() {

    var funMenuOptionClick: ((product: Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemListView = layoutInflater.inflate(R.layout.product_item_box, parent, false)
        return ProductViewHolder(itemListView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(context, list[position])
        holder.imageButton.setOnClickListener {
            funMenuOptionClick?.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnProductClickListener(funcion: (product: Product) -> Unit) {
        funMenuOptionClick = funcion
    }
}

class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
    val textViewSubtitulo: TextView = itemView.findViewById(R.id.textViewPrecioTotal)
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageButton: ImageButton = itemView.findViewById(R.id.textViewCantidad)

    fun bind(context: Context, product: Product) {
        textViewTitulo.text = product.title
        textViewSubtitulo.text = "$ ${product.price}"

        imageView.setImageResource(product.image)
/*
        Glide.with(context)
            .load(product.image)
            .centerCrop()
            .placeholder(R.drawable.ic_hold)
            .into(imageView)

 */
    }
}