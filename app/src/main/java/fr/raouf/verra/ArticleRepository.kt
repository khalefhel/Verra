package fr.raouf.verra

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.raouf.verra.ArticleRepository.Singleton.articleList
import fr.raouf.verra.ArticleRepository.Singleton.databaseRef
import javax.security.auth.callback.Callback

class ArticleRepository {
    object Singleton {
        // se connecter a la reference "article"
        val databaseRef = FirebaseDatabase.getInstance().getReference("article")

        // créer une liste qui va contenir nos articles
        val articleList = arrayListOf<ArticleModel>()
    }

    fun updateData(callback: () -> Unit) {
        // absorber les données depuis la databaseRef -> liste d'article
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciennes
                articleList.clear()

                // recolter la liste
                for (ds in snapshot.children) {
                    // construire un objet article
                    val article = ds.getValue(ArticleModel::class.java)

                    // verifier que l'article n'est pas null
                    if (article != null) {
                        // ajouter l'article à notre liste
                        articleList.add(article)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    // mettre a jour un objet article en bdd
    fun updateArticle(article: ArticleModel) {
        databaseRef.child(article.id).setValue(article)
    }
}