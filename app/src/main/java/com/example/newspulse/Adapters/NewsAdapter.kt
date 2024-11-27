package com.example.newspulse.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspulse.Models.Article
import com.example.newspulse.databinding.NewsitemBinding
class NewsAdapter(private val articles:List<Article>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (articles.isEmpty()){
            return 0
        }else{
            return articles.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }



    inner class ViewHolder(private val binding:NewsitemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article) {
            binding.newTitle.text = article.title
            binding.description.text = article.description
            binding.authorName.text = article.author
            Glide.with(binding.root)
                .load(article.urlToImage)
                .into(binding.newImage)
        }

    }

}