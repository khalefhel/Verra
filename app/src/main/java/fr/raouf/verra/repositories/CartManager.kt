package fr.raouf.verra.repositories

import fr.raouf.verra.models.ArticleModel
import fr.raouf.verra.models.CartItem

object CartManager {
    private val items = mutableListOf<CartItem>()

    fun addItem(article: ArticleModel) {
        val existingItem = items.find { it.article.id == article.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            items.add(CartItem(article, 1))
        }
    }

    fun getTotalPrice(): Double = items.sumOf { it.article.price * it.quantity }

    // Méthode pour obtenir les articles du panier
    fun getItems(): List<CartItem> = items

    fun removeItem(articleId: String) {
        items.removeAll { it.article.id == articleId }
    }
}
