package fr.raouf.verra.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import fr.raouf.verra.ArticleModel
import fr.raouf.verra.MainActivity
import fr.raouf.verra.R
import fr.raouf.verra.adapter.ArticaleItemDecoration
import fr.raouf.verra.adapter.ArticleAdapter

class HomeFragment(
    private val context: MainActivity
) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Créer une liste qui va stocker ces articles
        val articlelist = arrayListOf<ArticleModel>()

        // Enregistrer un premier article dans notre liste (pissenlit)
        articlelist.add(
            ArticleModel(
                "POLO À RAYURES EN PETIT PIQUÉ DE COTON",
                "POLO RALPH LAUREN",
                "https://cdn.pixabay.com/photo/2016/10/03/20/39/bebe-1712791_1280.jpg",
                false,
                80.0,
            ))
        articlelist.add(
            ArticleModel(
                "BABY CLASSIC - JEAN DROIT",
                "JEAN RALPH LAUREN",
                "https://cdn.pixabay.com/photo/2018/04/20/05/09/looking-3335108_1280.jpg",
                false,
                60.0,
            ))
        articlelist.add(
            ArticleModel(
                "HERITAGE COURT UNISEX - BASKETS BASSES",
                "CHAUSSURE RALPH LAUREN",
                "https://cdn.pixabay.com/photo/2018/11/30/00/31/baby-shoes-3846824_1280.jpg",
                false,
                120.0,
            ))

        // Récupérer le RecyclerView depuis la mise en page
        val recyclerViewHorizontal = findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        //val recyclerViewVertical = findViewById<RecyclerView>(R.id.vertical_recycler_view)

        // Créer une instance de l'adaptateur
        val articleAdapter1 = ArticleAdapter(context, articlelist, R.layout.article_horizontal)
        //val articleAdapter2 = ArticleAdapter(R.layout.article_vertical)

        // Définir l'adaptateur sur le RecyclerView
        recyclerViewHorizontal.adapter = articleAdapter1
        recyclerViewHorizontal.addItemDecoration(ArticaleItemDecoration())
        //recyclerViewVertical.adapter = articleAdapter2
    }
}