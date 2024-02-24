package fr.raouf.verra.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.raouf.verra.Activity.DetailsActivity
import fr.raouf.verra.R
import fr.raouf.verra.models.ArticleChildModel
import fr.raouf.verra.popup.ArticleChildPopup
import fr.raouf.verra.repositories.ArticleChildRepository

class ArticleChildAdapter(
    val context: DetailsActivity,
    private val articleChildList: List<ArticleChildModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<ArticleChildAdapter.ViewHolderChild>(){
    class ViewHolderChild(view: View) : RecyclerView.ViewHolder(view){
        val articleImage = view.findViewById<ImageView>(R.id.article_item)
        val articleName = view.findViewById<TextView>(R.id.article_item_name)
        val articleDescription = view.findViewById<TextView>(R.id.article_item_description)
        val articlePrice = view.findViewById<TextView>(R.id.article_item_price)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderChild {
        val view = LayoutInflater.
                from(parent.context).
                inflate(layoutId, parent, false)

        return ViewHolderChild(view)
    }

    override fun onBindViewHolder(holder: ViewHolderChild, position: Int) {
        // recuperer les informations de l'article Child
        val currentChildArticle = articleChildList[position]
        val repoChild = ArticleChildRepository()

        Glide.with(context).load(Uri.parse(currentChildArticle.imageUrl)).into(holder.articleImage)

        holder.articleName.text = currentChildArticle.name
        holder.articleDescription.text = currentChildArticle.description
        holder.articlePrice.text = currentChildArticle.price.toString()

        // Verifier si l'article à été liké
        if(currentChildArticle.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        holder.starIcon.setOnClickListener {
            currentChildArticle.liked = !currentChildArticle.liked
            repoChild.updateArticleChild(currentChildArticle)
        }
        // interaction lors du clic sur un article
        holder.itemView.setOnClickListener {
            // afficher la popup
            ArticleChildPopup(this, currentChildArticle).show()
        }
    }

    override fun getItemCount(): Int = articleChildList.size
}