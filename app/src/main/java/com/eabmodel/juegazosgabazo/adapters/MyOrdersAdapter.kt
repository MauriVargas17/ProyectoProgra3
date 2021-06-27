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
import com.eabmodel.juegazosgabazo.objects.Order
import org.w3c.dom.Text

class MyOrdersAdapter(val context: Context, var list: List<Order>): RecyclerView.Adapter<OrderViewHolder>() {

    var funMenuOptionClick: ((order: Order) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemListView = layoutInflater.inflate(R.layout.product_item_box_orders, parent, false)
        return OrderViewHolder(itemListView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        holder.bind(context, list[position])
        /*
        holder.imageButton.setOnClickListener {
            funMenuOptionClick?.invoke(list[position])

         */


        }

    override fun getItemCount(): Int {
       return list.size
    }




    fun setOnProductClickListener(funcion: (order: Order) -> Unit) {

        funMenuOptionClick = funcion


    }


}

class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)
    val textViewSubtitulo: TextView = itemView.findViewById(R.id.textViewPrecioTotal)
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
 //  val imageButton: ImageButton = itemView.findViewById(R.id.moreInfo)
    val type: TextView = itemView.findViewById(R.id.type)
    val date: TextView = itemView.findViewById(R.id.date)
    val seller: TextView = itemView.findViewById(R.id.seller)

    fun bind(context: Context, order: Order) {
        textViewTitulo.text = order.title
        textViewSubtitulo.text = "$ ${order.price}"
        type.text = order.type
        date.text = order.date
        seller.text = order.seller
        imageView.setImageResource(order.image)

    }
}