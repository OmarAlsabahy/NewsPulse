package com.example.newspulse.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspulse.ClickListners.OnclickListener
import com.example.newspulse.Models.Article
import com.example.newspulse.R
import com.example.newspulse.databinding.NewsitemBinding
class NewsAdapter(private val articles:List<Article> , private val onclickListener: OnclickListener):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

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
        holder.binding.root.setOnClickListener {
            onclickListener.onclick(articles[position].url)
        }
    }



    inner class ViewHolder(val binding:NewsitemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article) {
            binding.newTitle.text = article.title
            binding.description.text = article.description
            binding.authorName.text = article.author
            Glide.with(binding.root)
                .load(article.urlToImage)
                .placeholder(R.drawable.baseline_image_not_supported_24)
                .into(binding.newImage)
        }

    }

}