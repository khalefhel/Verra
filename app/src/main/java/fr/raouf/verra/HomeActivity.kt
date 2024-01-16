package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.raouf.verra.fragments.HomeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var onglet_man: Button
    lateinit var btn_Logout: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize Firebase Auth
        auth = Firebase.auth

        onglet_man = findViewById(R.id.onglet_man)
        btn_Logout = findViewById(R.id.btn_Logout)

        // charger notre ActicleRepository
        val repo = ArticleRepository()

        // mettre à jour la liste d'articles
        repo.updateData {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }

        onglet_man.setOnClickListener {
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
    }
}