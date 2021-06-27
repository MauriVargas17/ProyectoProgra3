package com.eabmodel.juegazosgabazo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eabmodel.juegazosgabazo.R
import com.eabmodel.juegazosgabazo.objects.Product

class CartAdapter(val context: Context, var list: List<Product>): RecyclerView.Adapter<ProductViewHolderCart>() {

    var funMenuOptionClick: ((product: Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolderCart {
        val layoutInflater = LayoutInflater.from(context)
        val itemListView = layoutInflater.inflate(R.layout.product_item_box_cart, parent, false)
        return ProductViewHolderCart(itemListView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnProductClickListener(funcion: (product: Product) -> Unit) {
        funMenuOptionClick = funcion
    }

    override fun onBindViewHolder(holder: ProductViewHolderCart, position: Int) {
        holder.bind(context, list[position])
        holder.imageButton.setOnClickListener {
            funMenuOptionClick?.invoke(list[position])
        }
    }
}

class ProductViewHolderCart(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
    val textViewSubtitulo: TextView = itemView.findViewById(R.id.textViewPrecioTotal)
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageButton: ImageButton = itemView.findViewById(R.id.moreInfo)
    val type: TextView = itemView.findViewById(R.id.type)

    fun bind(context: Context, product: Product) {
        textViewTitulo.text = product.title
        textViewSubtitulo.text = "$ ${product.price}"
        type.text = product.type
        imageView.setImageResource(product.image)

    }
}