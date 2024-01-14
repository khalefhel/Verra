package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import fr.raouf.verra.fragments.HomeFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvHello = findViewById<TextView>(R.id.tvHello)

        // 1: recuperer l'email envoyé par l'activityMain
        val email = intent.getStringExtra("email")

        // 2: Afficher l'email dans le tvHello
        tvHello.text = "Bienvenu : $email"

        // charger notre ActicleRepository
        val repo = ArticleRepository()

        // mettre à jour la liste d'articles
        repo.updateData {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }

        val onglet_man: View = findViewById(R.id.onglet_man)
        onglet_man.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }
}