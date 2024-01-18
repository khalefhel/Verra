package fr.raouf.verra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.raouf.verra.fragments.AddArticleFragment

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // charger notre ActicleRepository
        val repo = ArticleRepository()

        // mettre à jour la liste d'articles
        repo.updateData {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container_test, AddArticleFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}