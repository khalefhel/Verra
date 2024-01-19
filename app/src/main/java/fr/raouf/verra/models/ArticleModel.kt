interface Article {
    val id: String
    val description: String
    val name: String
    val imageUrl: String
    val price: Double
    var liked: Boolean
}

// ArticleModel implémente l'interface Article
data class ArticleModel(
    override val id: String = "article0",
    override val description: String = "Petite description",
    override val name: String = "Lacoste",
    override val imageUrl: String = "https://example.com/your-image.jpg",
    override val price: Double = 0.0,
    override var liked: Boolean = false
) : Article

// ArticleChildModel implémente l'interface Article
data class ArticleChildModel(
    override val id: String = "article0",
    override val description: String = "Petite description",
    override val name: String = "Lacoste",
    override val imageUrl: String = "https://example.com/your-image.jpg",
    override val price: Double = 0.0,
    override var liked: Boolean = false
) : Article