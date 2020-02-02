package com.aram.paexamtest.ui


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import com.aram.paexamtest.R
import com.aram.paexamtest.databinding.FragmentAllArticlesBinding
import com.aram.paexamtest.vievmodels.AllArticlesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent

/**
 * A simple [Fragment] subclass.
 */
class AllArticlesFragment : Fragment(), KoinComponent,
    ArticlesRVAdapter.RecyclerViewEventsListener {

    private lateinit var binding: FragmentAllArticlesBinding
    private lateinit var adapter: ArticlesRVAdapter
    private val viewModel: AllArticlesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_articles, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        adapter = ArticlesRVAdapter()
        binding.recyclerView.adapter = adapter
        adapter.listener = this
        setItemTouchHelper()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun setItemTouchHelper() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteArticle(adapter.getArticle(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(binding.recyclerView)
    }

    override fun onScroll() {
viewModel.getArticles("2")
    }

    override fun onLickPress(article: Article) {
        viewModel.likeArticle(article)
    }

    override fun onItemClick(article: Article) {
        findNavController().navigate(AllArticlesFragmentDirections.actionAllArticlesFragmentToDetailsFragment(article))
    }

}
