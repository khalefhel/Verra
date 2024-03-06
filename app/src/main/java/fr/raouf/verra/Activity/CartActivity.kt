package fr.raouf.verra.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.raouf.verra.adapter.CartItemChangeListener
import fr.raouf.verra.adapter.CartItemsAdapter
import fr.raouf.verra.repositories.CartManager
import fr.raouf.verra.R

class CartActivity : AppCompatActivity(), CartItemChangeListener {
    // Implémentation de la méthode de l'interface
    override fun onCartItemRemoved() {
        updateTotalPrice()
    }

    private lateinit var cartItemsRecyclerView: RecyclerView
    private lateinit var cartItemsAdapter: CartItemsAdapter // Adaptez le nom selon votre implémentation
    lateinit var btn_return: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartItemsRecyclerView = findViewById(R.id.rv_cartItems)
        cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)


        cartItemsAdapter = CartItemsAdapter(CartManager.getItems(), this)
        cartItemsRecyclerView.adapter = cartItemsAdapter

        // Définissez l'adapter de votre RecyclerView
        cartItemsRecyclerView.adapter = cartItemsAdapter

        updateTotalPrice()

        btn_return = findViewById(R.id.return_home)
        btn_return.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }
    private fun updateTotalPrice() {
        val totalPrice = CartManager.getTotalPrice()
        findViewById<TextView>(R.id.tv_totalPrice).text = "Total: $totalPrice€"
    }
}