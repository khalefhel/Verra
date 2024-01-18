package fr.raouf.verra

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.raouf.verra.ArticleRepository.Singleton.articleList
import fr.raouf.verra.ArticleRepository.Singleton.databaseRef
import fr.raouf.verra.ArticleRepository.Singleton.downloadUri
import fr.raouf.verra.ArticleRepository.Singleton.storageReference
import java.util.UUID

class ArticleRepository {
    object Singleton {
        // Donner le lien pour acceder au backet
        private val BUCKET_URL: String = "gs://verra-d8488.appspot.com"

        // se connecter à notre espace de stackage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // se connecter a la reference "article"
        val databaseRef = FirebaseDatabase.getInstance().getReference("article")

        // créer une liste qui va contenir nos articles
        val articleList = arrayListOf<ArticleModel>()

        // contenir le lien de l'image courante
        var downloadUri: Uri? = null
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

    // créer une fonction pour envoyer des fichiers sur le storage
    fun uploadImage(file: Uri, callback: () -> Unit) {
        // verifier que ce fichier n'est pas null
        if (file != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            // demarrer la tache de l'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                // s'il y a eu un probleme lors de l'envoi du fichier
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener { task ->
                // verifier si tout a bien fonctionné
                if (task.isSuccessful) {
                    // reccuperer l'image
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    // mettre a jour un objet article en bdd
    fun updateArticle(article: ArticleModel) = databaseRef.child(article.id).setValue(article)

    // inserer un nouvel article en bdd
    fun insertArticle(article: ArticleModel) = databaseRef.child(article.id).setValue(article)
}