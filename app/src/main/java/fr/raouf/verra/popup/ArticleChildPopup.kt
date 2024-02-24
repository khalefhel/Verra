package fr.raouf.verra.popup

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.raouf.verra.R
import fr.raouf.verra.adapter.ArticleChildAdapter
import fr.raouf.verra.models.ArticleChildModel

class ArticleChildPopup(
    private val adapter: ArticleChildAdapter,
    private  val currentChildArticle: ArticleChildModel
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_article_details)
        setupComponents()
        setupCloseButton()
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.btn_close).setOnClickListener {
            // fermer la fenetre
            dismiss()
        }
    }

    private fun setupComponents() {
        // Actualiser l'image de l'article
        val articleImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentChildArticle.imageUrl)).into(articleImage)

        // actualiser le nom de l'article
        findViewById<TextView>(R.id.article_item_name).text = currentChildArticle.name

        // actualiser le description de l'article
        findViewById<TextView>(R.id.article_item_description).text = currentChildArticle.description

        // actualiser le prix de l'article
        findViewById<TextView>(R.id.article_item_price).text = currentChildArticle.price.toString()
    }

}