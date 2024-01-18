package fr.raouf.verra

class ArticleModel(
    val id: String = "article0",
    val description: String = "Petite description",
    val name: String = "Lacoste",
    val imageUrl: String = "https://example.com/your-image.jpg",
    val price: Double = 0.0,
    var liked: Boolean = false
)