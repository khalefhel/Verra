package fr.raouf.verra

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.raouf.verra.adapter.ArticleAdapter
import fr.raouf.verra.models.ArticleModel

class ArticlePopup(
    private val adapter: ArticleAdapter,
    private  val currentArticle: ArticleModel
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
        Glide.with(adapter.context).load(Uri.parse(currentArticle.imageUrl)).into(articleImage)

        // actualiser le nom de l'article
        findViewById<TextView>(R.id.article_item_name).text = currentArticle.name

        // actualiser le description de l'article
        findViewById<TextView>(R.id.article_item_description).text = currentArticle.description

        // actualiser le prix de l'article
        findViewById<TextView>(R.id.article_item_price).text = currentArticle.price.toString()
    }

}