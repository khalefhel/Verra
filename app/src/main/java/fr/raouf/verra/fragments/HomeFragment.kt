package fr.raouf.verra.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.raouf.verra.ArticleModel
import fr.raouf.verra.HomeActivity
import fr.raouf.verra.R
import fr.raouf.verra.adapter.ArticleAdapter

class HomeFragment(
    private val context: HomeActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.collection_fragment, container, false)

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

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ArticleAdapter(context, articlelist, R.layout.article_horizontal)

        return view
    }
}