package fr.raouf.verra.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.raouf.verra.R
import fr.raouf.verra.adapter.ArticleAdapter

class ArticleFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view =  inflater.inflate(R.layout.home_page, container, false)

            // recuperer le recyclerview
            val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
            horizontalRecyclerView.adapter = ArticleAdapter()

            return view
        }
}