package fr.raouf.verra.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.raouf.verra.models.ArticleWomenModel
import fr.raouf.verra.repositories.ArticleWomenRepository.Singleton.articleWomenList
import fr.raouf.verra.repositories.ArticleWomenRepository.Singleton.databaseRefWomen

class ArticleWomenRepository {
    object Singleton {
        val databaseRefWomen = FirebaseDatabase.getInstance().getReference("articleWomen")
        val articleWomenList = arrayListOf<ArticleWomenModel>()
    }
    fun updateDataDetails(callback: (Boolean) -> Unit) {
        databaseRefWomen.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articleWomenList.clear()

                for (dsw in snapshot.children){
                    val articleWomen = dsw.getValue(ArticleWomenModel::class.java)

                    if (articleWomen != null) {
                        articleWomenList.add(articleWomen)
                    }
                }
                callback(true)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false)
                Log.e("ArticleWomenRepository", "Erreur lors de la lecture depuis la base de données : ${error.message}")
            }

        })
    }

    fun updateArticleWomen(articleWomen: ArticleWomenModel) = databaseRefWomen.child(articleWomen.id).setValue(articleWomen)
}