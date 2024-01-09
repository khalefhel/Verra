package fr.raouf.verra.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.raouf.verra.ArticleModel
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

        // Utiliser glide pour recuperer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentArticle.imageUrl)).into(holder.articleImage)
    }

}