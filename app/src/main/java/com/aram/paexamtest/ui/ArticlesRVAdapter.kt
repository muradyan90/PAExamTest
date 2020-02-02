package com.aram.paexamtest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aram.paexamtest.databinding.ItemLayoutBinding

class ArticlesRVAdapter : ListAdapter<Article,ArticlesRVAdapter.ArticleViewHolder>(ArticleDiffCalback()) {

var listener: RecyclerViewEventsListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            listener,
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )

    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getArticle(position: Int): Article{
        return getItem(position)
    }
    class ArticleDiffCalback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    inner class ArticleViewHolder(
        private val listener: RecyclerViewEventsListener?,
        private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article){
            binding.article = article

            binding.root.setOnClickListener{
                listener?.onItemClick(article)
            }

            binding.itemLiked.setOnClickListener{
                listener?.onLickPress(article)
            }

            if (itemCount - adapterPosition == 2){
                listener?.onScroll()
            }
        }
    }

    interface RecyclerViewEventsListener{
        fun onScroll()
        fun onLickPress(article: Article)
        fun onItemClick(article: Article)
    }
}