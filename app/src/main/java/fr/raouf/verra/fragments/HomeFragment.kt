package fr.raouf.verra.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.raouf.verra.repositories.ArticleRepository.Singleton.articleList
import fr.raouf.verra.Activity.HomeActivity
import fr.raouf.verra.R
import fr.raouf.verra.adapter.ArticaleItemDecoration
import fr.raouf.verra.adapter.ArticleAdapter

class HomeFragment(
    private val context: HomeActivity,
    private val currentCategory: String
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.collection_fragment, container, false)

        val filteredArticles = articleList.filter { it.category == currentCategory }

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ArticleAdapter(context, filteredArticles, R.layout.article_horizontal)
        horizontalRecyclerView.addItemDecoration(ArticaleItemDecoration())

        return view
    }
}