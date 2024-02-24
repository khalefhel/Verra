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
import fr.raouf.verra.models.ArticleWomenModel
import fr.raouf.verra.popup.ArticleWomenPopup
import fr.raouf.verra.repositories.ArticleWomenRepository

class ArticleWomenAdapter (
    val context: DetailsActivity,
    private val articleWomenList: List<ArticleWomenModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<ArticleWomenAdapter.ViewHolderWomen>(){
    class ViewHolderWomen(view: View) : RecyclerView.ViewHolder(view) {
        val articleImage = view.findViewById<ImageView>(R.id.article_item)
        val articleName = view.findViewById<TextView>(R.id.article_item_name)
        val articleDescription = view.findViewById<TextView>(R.id.article_item_description)
        val articlePrice = view.findViewById<TextView>(R.id.article_item_price)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderWomen {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolderWomen(view)
    }

    override fun onBindViewHolder(holder: ViewHolderWomen, position: Int) {
        val currentWomenArticle = articleWomenList[position]
        val repoWomen = ArticleWomenRepository()

        Glide.with(context).load(Uri.parse(currentWomenArticle.imageUrl)).into(holder.articleImage)

        holder.articleName.text = currentWomenArticle.name
        holder.articleDescription.text = currentWomenArticle.description
        holder.articlePrice.text = currentWomenArticle.price.toString()

        if(currentWomenArticle.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        holder.starIcon.setOnClickListener {
            currentWomenArticle.liked = !currentWomenArticle.liked
            repoWomen.updateArticleWomen(currentWomenArticle)
        }
        // interaction lors du clic sur un article
        holder.itemView.setOnClickListener {
            // afficher la popup
            ArticleWomenPopup(this, currentWomenArticle).show()
        }
    }

    override fun getItemCount(): Int = articleWomenList.size
}












