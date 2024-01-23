package fr.raouf.verra.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.raouf.verra.DetailsActivity
import fr.raouf.verra.R
import fr.raouf.verra.models.ArticleManModel
import fr.raouf.verra.repositories.ArticleManRepository

class ArticleManAdapter (
    private val context: DetailsActivity,
    private val articleManList: List<ArticleManModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<ArticleManAdapter.ViewHolderMan>() {
    class ViewHolderMan(view: View) : RecyclerView.ViewHolder(view) {
        val articleImage = view.findViewById<ImageView>(R.id.article_item)
        val articleName = view.findViewById<TextView>(R.id.article_item_name)
        val articleDescription = view.findViewById<TextView>(R.id.article_item_description)
        val articlePrice = view.findViewById<TextView>(R.id.article_item_price)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMan {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ArticleManAdapter.ViewHolderMan(view)
    }
    override fun onBindViewHolder(holder: ViewHolderMan, position: Int) {
        val currentManArticle = articleManList[position]
        val repoMan = ArticleManRepository()

        Glide.with(context).load(Uri.parse(currentManArticle.imageUrl)).into(holder.articleImage)

        holder.articleName.text = currentManArticle.name
        holder.articleDescription.text = currentManArticle.description
        holder.articlePrice.text = currentManArticle.price.toString()

        if(currentManArticle.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        holder.starIcon.setOnClickListener {
            currentManArticle.liked = !currentManArticle.liked
            repoMan.updateArticleMan(currentManArticle)
        }
    }
    override fun getItemCount(): Int = articleManList.size
}