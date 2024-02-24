package fr.raouf.verra

import fr.raouf.verra.models.ArticleModel

data class CartItem(
    val article: ArticleModel,
    var quantity: Int
)