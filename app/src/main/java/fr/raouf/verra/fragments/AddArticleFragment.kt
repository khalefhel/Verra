package fr.raouf.verra.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import fr.raouf.verra.repositories.ArticleRepository
import fr.raouf.verra.repositories.ArticleRepository.Singleton.downloadUri
import fr.raouf.verra.R
import fr.raouf.verra.Activity.TestActivity
import fr.raouf.verra.models.ArticleModel
import java.util.UUID

class AddArticleFragment(
    private val context: TestActivity
) : Fragment() {

    private var file:Uri? = null
    private var uploaderImage:ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_article, container, false)

        // recuperer uploadedImage pour lui associer son composant
        uploaderImage = view?.findViewById(R.id.preview_image)

        // recuperer le bouton pour charger l'image
        val pickupImageButton = view?.findViewById<Button>(R.id.upload_btn)

        // lorsqu'on click dessus ça ouvre les images de téléphone
        pickupImageButton?.setOnClickListener { pickupImage() }

        // recuperer le bouton confirmer
        val confirmButton = view?.findViewById<Button>(R.id.confirm_button)
        confirmButton?.setOnClickListener {
            sendForm(view)
        }

        return view
    }

    private fun sendForm(view: View) {
        val repo = ArticleRepository()
        repo.uploadImage(file!!) {
            val articleName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val articleDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val downloadImageUrl = downloadUri.toString()
            val articlePrice = view.findViewById<EditText>(R.id.price_input).text.toString().toDouble()


            // Créer un nouvel objet ArticleModel
            val article = ArticleModel(
                UUID.randomUUID().toString(),
                articleDescription,
                articleName,
                downloadImageUrl,
                articlePrice
            )

            // Envoyer en bdd
            repo.insertArticle(article)
        }
    }
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Traitement de l'URI de l'image ici
        if (uri != null) {
            uploaderImage?.setImageURI(uri)
            file = uri
        }
    }
    private fun pickupImage() {
        getContent.launch("image/*")
    }
    @Deprecated("Use the alternative method instead.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 47 && resultCode == Activity.RESULT_OK) {
            // verifier si les données sont nulles
            if (data == null || data.data == null) return

            // reccuperer l'image
            val file = data.data

            // mettre à jour l'aperçu de l'image
            uploaderImage?.setImageURI(file)
        }
    }
}