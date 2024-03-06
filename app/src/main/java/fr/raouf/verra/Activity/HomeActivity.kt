package fr.raouf.verra.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.raouf.verra.PaymentActivity
import fr.raouf.verra.R
import fr.raouf.verra.fragments.HomeFragment
import fr.raouf.verra.repositories.ArticleRepository

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private var currentCategory: String = ""
    lateinit var btn_test: ImageButton
    lateinit var btn_browse: Button
    lateinit var btn_Logout: ImageButton
    lateinit var btn_Cart: ImageButton
    lateinit var btn_paypal: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize Firebase Auth
        auth = Firebase.auth

        btn_test = findViewById(R.id.btn_test)
        btn_browse = findViewById(R.id.btn_browse)
        btn_Logout = findViewById(R.id.btn_Logout)
        btn_Cart = findViewById(R.id.btn_Cart)

        // charger notre ActicleRepository
        val repo = ArticleRepository()

        // Mettre à jour la liste d'articles pour chaque catégorie et conteneur
        loadFragment(repo, "HAUTS", R.id.fragment_container1)
        loadFragment(repo, "BAS", R.id.fragment_container2)
        loadFragment(repo, "SHOES", R.id.fragment_container3)

        btn_browse.setOnClickListener {
            Intent(this, DetailsActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

        btn_Logout.setOnClickListener {
            val auth = Firebase.auth
            auth.signOut()
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

        btn_test.setOnClickListener {
            Intent(this, TestActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

        btn_Cart.setOnClickListener {
            Intent(this, CartActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

        btn_paypal = findViewById(R.id.tst_paypal)
        btn_paypal.setOnClickListener {
            Intent(this, PaymentActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }

    // Fonction pour charger un fragment dans un conteneur spécifique
    private fun loadFragment(repo: ArticleRepository, currentCategory: String, containerId: Int) {
        repo.updateData {
            val transaction = supportFragmentManager.beginTransaction()
            val fragment = HomeFragment(this, currentCategory)
            transaction.replace(containerId, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}