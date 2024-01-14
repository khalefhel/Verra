package fr.raouf.verra.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.raouf.verra.ArticleModel
import fr.raouf.verra.ArticleRepository.Singleton.articleList
import fr.raouf.verra.HomeActivity
import fr.raouf.verra.R
import fr.raouf.verra.adapter.ArticaleItemDecoration
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

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ArticleAdapter(context, articleList, R.layout.article_horizontal)
        horizontalRecyclerView.addItemDecoration(ArticaleItemDecoration())

        return view
    }
}