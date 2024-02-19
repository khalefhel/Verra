package fr.raouf.verra.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.raouf.verra.DetailsActivity
import fr.raouf.verra.R
import fr.raouf.verra.adapter.ArticaleItemDecoration
import fr.raouf.verra.adapter.ArticleManAdapter
import fr.raouf.verra.adapter.ArticleWomenAdapter
import fr.raouf.verra.repositories.ArticleManRepository
import fr.raouf.verra.repositories.ArticleWomenRepository
import fr.raouf.verra.repositories.ArticleWomenRepository.Singleton.articleWomenList

class WomenFragment(
    private val context: DetailsActivity,
    private val currentWomenCategory: String
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.collection_fragment, container, false)

        val filteredArticles = articleWomenList.filter { it.category == currentWomenCategory }

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ArticleWomenAdapter(context, filteredArticles, R.layout.article_horizontal)
        horizontalRecyclerView.addItemDecoration(ArticaleItemDecoration())

        return view
    }
}