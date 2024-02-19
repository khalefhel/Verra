package fr.raouf.verra.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.raouf.verra.models.ArticleManModel

class ArticleManRepository {
    object Singleton {
        val databaseRefMan = FirebaseDatabase.getInstance().getReference("articleMan")
        val articleManList = arrayListOf<ArticleManModel>()
    }
    fun updateDataDetails(callback: (Boolean) -> Unit) {
        ArticleManRepository.Singleton.databaseRefMan.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Singleton.articleManList.clear()

                for (dsw in snapshot.children){
                    val articleMan = dsw.getValue(ArticleManModel::class.java)

                    if (articleMan != null) {
                        Singleton.articleManList.add(articleMan)
                    }
                }
                callback(true)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false)
                Log.e("ArticleManRepository", "Erreur lors de la lecture depuis la base de données : ${error.message}")
            }
        })
    }
    fun updateArticleMan(articleMan: ArticleManModel) = Singleton.databaseRefMan.child(articleMan.id).setValue(articleMan)
}
