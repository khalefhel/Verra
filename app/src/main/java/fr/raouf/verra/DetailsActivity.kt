package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.raouf.verra.fragments.ChildFragment
import fr.raouf.verra.fragments.ManFragment
import fr.raouf.verra.fragments.WomenFragment

class DetailsActivity : AppCompatActivity() {

    lateinit var  btn_return_home: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        btn_return_home = findViewById(R.id.btn_return_home)

        // Click pour retourner à l'accueil
        btn_return_home.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

        loadFragment { ManFragment(this) }
        // importer
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.details_page_man -> loadFragment { ManFragment(this) }
                R.id.details_page_women -> loadFragment { WomenFragment(this) }
                R.id.details_page_child -> loadFragment { ChildFragment(this) }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun loadFragment(fragmentProvider: () -> Fragment) {
        // Mise à jour de la liste d'articles dans le repository
        ArticleRepository().updateData {
            val transaction = supportFragmentManager.beginTransaction()

            // Liste des identifiants de conteneurs fragment à mettre à jour
            val containerIds = listOf(R.id.fragment_container_jackets, R.id.fragment_container_pants, R.id.fragment_container_shoes)

            // Remplacement du fragment pour chaque conteneur
            containerIds.forEach { containerId ->
                val fragment = fragmentProvider.invoke() // Appel de la lambda pour obtenir une nouvelle instance du fragment
                transaction.replace(containerId, fragment)
            }

            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}