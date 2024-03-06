package fr.raouf.verra.models

import fr.raouf.verra.models.ArticleModel

data class CartItem(
    val article: ArticleModel,
    var quantity: Int
)