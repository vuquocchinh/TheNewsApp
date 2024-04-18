package com.example.thenewsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thenewsapp.R
import com.example.thenewsapp.databinding.ItemNewsBinding
import com.example.thenewsapp.model.News

class NewsAdapter(private val context: Context, private var news: List<News>, private val listener: (News, Int) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    class NewsHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    fun update(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val new = news[position]
        holder.binding.articleDateTime.text = new.pubDate
        holder.binding.articleDescription.text = new.description
        holder.binding.articleTitle.text = new.title
        Glide.with(context).load(new.imageURL)
            .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.binding.articleImage)
        holder.binding.container.setOnClickListener {
            listener(new, position)
        }

    }


}