package fr.raouf.verra.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.raouf.verra.ArticleModel
import fr.raouf.verra.ArticleRepository
import fr.raouf.verra.HomeActivity
import fr.raouf.verra.MainActivity
import fr.raouf.verra.R

class ArticleAdapter(
    private val context: HomeActivity,
    private val articleList: List<ArticleModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){

    // Boite pour ranger tout les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val articleImage = view.findViewById<ImageView>(R.id.article_item)
        val articleName = view.findViewById<TextView>(R.id.article_item_name)
        val articleDescription = view.findViewById<TextView>(R.id.article_item_description)
        val articlePrice = view.findViewById<TextView>(R.id.article_item_price)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = articleList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Recuperer les informations de l'article
        val currentArticle = articleList[position]

        // Recuperer le repository
        val repo = ArticleRepository()

        // Utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentArticle.imageUrl)).into(holder.articleImage)

        // Recuperer le nom, description et le prix de l'article
        holder.articleName.text = currentArticle.name
        holder.articleDescription.text = currentArticle.description
        holder.articlePrice.text = currentArticle.price.toString()

        // Verifier si l'article à été liké
        if(currentArticle.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        // rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener {
            // inverse si le bouton est like ou non
            currentArticle.liked = !currentArticle.liked

            // mettre a jour l'objet article
            repo.updateArticle(currentArticle)
        }
    }

}