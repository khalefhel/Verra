package fr.raouf.verra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

interface CartItemChangeListener {
    fun onCartItemRemoved()
}
class CartItemsAdapter (

    private val cartItems: List<CartItem>,
    private val listener: CartItemChangeListener

) : RecyclerView.Adapter<CartItemsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.tv_articleName)
        val quantityTextView: TextView = view.findViewById(R.id.tv_articleQuantity)
        val priceTextView: TextView = view.findViewById(R.id.tv_articlePrice)
        val articleImageView: ImageView = view.findViewById(R.id.iv_articleImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.nameTextView.text = cartItem.article.name
        holder.quantityTextView.text = "Quantité : ${cartItem.quantity}"
        holder.priceTextView.text = "Prix : ${cartItem.article.price}€"

        // Utilisation de Glide pour charger l'image de l'article. Assurez-vous que l'URL de l'image est correcte.
        Glide.with(holder.articleImageView.context)
            .load(cartItem.article.imageUrl)
            .into(holder.articleImageView)

        holder.itemView.findViewById<ImageButton>(R.id.btn_removeArticle).setOnClickListener {
            // Appeler CartManager pour supprimer l'article
            CartManager.removeItem(cartItems[position].article.id)
            // Actualiser l'adapter
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)

            listener.onCartItemRemoved()
        }
    }

    override fun getItemCount() = cartItems.size
}