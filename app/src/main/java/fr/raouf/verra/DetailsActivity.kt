package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.raouf.verra.fragments.ChildFragment
import fr.raouf.verra.fragments.ManFragment
import fr.raouf.verra.fragments.WomenFragment
import fr.raouf.verra.repositories.ArticleChildRepository
import fr.raouf.verra.repositories.ArticleManRepository
import fr.raouf.verra.repositories.ArticleRepository
import fr.raouf.verra.repositories.ArticleWomenRepository

class DetailsActivity : AppCompatActivity() {

    lateinit var  btn_return_home: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val repoMan = ArticleManRepository()
        val repoWomen = ArticleWomenRepository()
        val repoChild = ArticleChildRepository()

        fun setupNavigation() {
            loadFragment { ManFragment(this) }
            val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
            navigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.details_page_man -> loadFragment { ManFragment(this) }
                    R.id.details_page_women -> loadFragment { WomenFragment(this) }
                    R.id.details_page_child -> loadFragment { ChildFragment(this) }
                }
                return@setOnNavigationItemSelectedListener true
            }
        }

        repoMan.updateDataDetails {
            loadFragment { ManFragment(this) }
            setupNavigation()
        }
        repoWomen.updateDataDetails {
            loadFragment { WomenFragment(this) }
            setupNavigation()
        }
        repoChild.updateDataDetails {
            loadFragment { ChildFragment(this) }
            setupNavigation()
        }

        btn_return_home = findViewById(R.id.btn_return_home)

        // Click pour retourner à l'accueil
        btn_return_home.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                startActivity(it)
            }
            finish()
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