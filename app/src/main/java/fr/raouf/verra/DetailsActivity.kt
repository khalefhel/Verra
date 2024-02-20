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

    private var currentManCategory: String = ""
    private var currentWomenCategory: String = ""
    private var currentChildCategory: String = ""
    lateinit var  btn_return_home: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val repoMan = ArticleManRepository()
        val repoWomen = ArticleWomenRepository()
        val repoChild = ArticleChildRepository()

        fun setupNavigation() {
            //loadFragment { ManFragment(this, "HAUTS") }
            val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
            navigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.details_page_man -> {
                        showFragmentInContainerMan("HAUTS", R.id.fragment_container_jackets)
                        showFragmentInContainerMan("BAS", R.id.fragment_container_pants)
                        showFragmentInContainerMan("SHOES", R.id.fragment_container_shoes)
                    }
                    R.id.details_page_women -> {
                        showFragmentInContainerWomen("HAUTS", R.id.fragment_container_jackets)
                        showFragmentInContainerWomen("BAS", R.id.fragment_container_pants)
                        showFragmentInContainerWomen("SHOES", R.id.fragment_container_shoes)
                    }
                    R.id.details_page_child -> {
                        showFragmentInContainerChild("HAUTS", R.id.fragment_container_jackets)
                        showFragmentInContainerChild("BAS", R.id.fragment_container_pants)
                        showFragmentInContainerChild("SHOES", R.id.fragment_container_shoes)
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }
        }

        repoWomen.updateDataDetails {
            showFragmentInContainerWomen("HAUTS", R.id.fragment_container_jackets)
            showFragmentInContainerWomen("BAS", R.id.fragment_container_pants)
            showFragmentInContainerWomen("SHOES", R.id.fragment_container_shoes)
            setupNavigation()
        }
        repoChild.updateDataDetails {
            showFragmentInContainerChild("HAUTS", R.id.fragment_container_jackets)
            showFragmentInContainerChild("BAS", R.id.fragment_container_pants)
            showFragmentInContainerChild("SHOES", R.id.fragment_container_shoes)
            setupNavigation()
        }
        repoMan.updateDataDetails {
            showFragmentInContainerMan("HAUTS", R.id.fragment_container_jackets)
            showFragmentInContainerMan("BAS", R.id.fragment_container_pants)
            showFragmentInContainerMan("SHOES", R.id.fragment_container_shoes)
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
    private fun showFragmentInContainerMan(category: String, containerId: Int) {
        currentManCategory = category
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, ManFragment(this, currentManCategory))
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun showFragmentInContainerWomen(category: String, containerId: Int) {
        currentWomenCategory = category
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, WomenFragment(this, currentWomenCategory))
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun showFragmentInContainerChild(category: String, containerId: Int) {
        currentChildCategory = category
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, ChildFragment(this, currentChildCategory))
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun loadFragment(fragmentProvider: (String) -> Fragment) {
        // Mise à jour de la liste d'articles dans le repository
        ArticleRepository().updateData {
            val transaction = supportFragmentManager.beginTransaction()

            // Liste des identifiants de conteneurs fragment à mettre à jour
            val containerIds = listOf(R.id.fragment_container_jackets, R.id.fragment_container_pants, R.id.fragment_container_shoes)

            // Remplacement du fragment pour chaque conteneur
            containerIds.forEach { containerId ->
                val fragment = fragmentProvider.invoke(getCategoryForContainer(containerId))
                transaction.replace(containerId, fragment)
            }

            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun getCategoryForContainer(containerId: Int): String {
        return when (containerId) {
            R.id.fragment_container_jackets -> when (getCurrentFragment()) {
                is ManFragment -> currentManCategory
                is WomenFragment -> currentWomenCategory
                is ChildFragment -> currentChildCategory
                else -> ""
            }
            R.id.fragment_container_pants -> when (getCurrentFragment()) {
                is ManFragment -> currentManCategory
                is WomenFragment -> currentWomenCategory
                is ChildFragment -> currentChildCategory
                else -> ""
            }
            R.id.fragment_container_shoes -> when (getCurrentFragment()) {
                is ManFragment -> currentManCategory
                is WomenFragment -> currentWomenCategory
                is ChildFragment -> currentChildCategory
                else -> ""
            }
            else -> ""
        }
    }

    private fun getCurrentFragment(): Fragment? {
        // Logique pour obtenir le fragment actuellement affiché dans le conteneur
        // Vous pouvez ajuster ceci en fonction de votre gestion des fragments
        return supportFragmentManager.findFragmentById(R.id.fragment_container_jackets)
    }
}
