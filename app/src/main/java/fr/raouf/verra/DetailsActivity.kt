package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import fr.raouf.verra.fragments.DetailsFragment
import fr.raouf.verra.fragments.HomeFragment

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // charger notre ActicleRepository
        val repo = ArticleRepository()

        // mettre à jour la liste d'articles
        repo.updateData {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_jackets, DetailsFragment(this))
            transaction.replace(R.id.fragment_container_pants, DetailsFragment(this))
            transaction.replace(R.id.fragment_container_shoes, DetailsFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}