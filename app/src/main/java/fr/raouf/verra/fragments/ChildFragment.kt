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
import fr.raouf.verra.adapter.ArticleChildAdapter
import fr.raouf.verra.adapter.ArticleManAdapter
import fr.raouf.verra.repositories.ArticleChildRepository.Singleton.articleChildList
import fr.raouf.verra.repositories.ArticleManRepository

class ChildFragment(
    private val context: DetailsActivity,
    private val currentChildCategory: String
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.collection_fragment, container, false)

        val filteredArticles = articleChildList.filter { it.category == currentChildCategory }

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ArticleChildAdapter(context, filteredArticles, R.layout.article_horizontal)
        horizontalRecyclerView.addItemDecoration(ArticaleItemDecoration())
        return view
    }
}