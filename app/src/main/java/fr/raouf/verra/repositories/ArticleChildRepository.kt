package fr.raouf.verra.repositories

import ArticleChildModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.raouf.verra.repositories.ArticleChildRepository.Singleton.articleChildList
import fr.raouf.verra.repositories.ArticleChildRepository.Singleton.databaseRef

class ArticleChildRepository {
    object Singleton {
        val databaseRef = FirebaseDatabase.getInstance().getReference("article")
        val articleChildList = arrayListOf<ArticleChildModel>()
    }

    fun updateData(callback: () -> Unit) {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articleChildList.clear()

                for (dsc in snapshot.children){
                    val articleChild = dsc.getValue(ArticleChildModel::class.java)

                    if (articleChild != null) {
                        articleChildList.add(articleChild)
                    }
                }
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun updateArticleChild(articleChild: ArticleChildModel) = databaseRef.child(articleChild.id).setValue(articleChild)

}