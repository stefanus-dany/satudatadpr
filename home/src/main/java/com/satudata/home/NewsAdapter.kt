package com.satudata.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.satudata.home.HomeFragment.Companion.EXTRA_NEWS_BODY
import com.satudata.home.HomeFragment.Companion.EXTRA_NEWS_IMAGE
import com.satudata.home.HomeFragment.Companion.EXTRA_NEWS_TITLE
import com.satudata.home.databinding.ItemNewsBinding
import com.satudata.views.extensions.setSafeOnClickListener

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var dataNews = ArrayList<NewsEntity>()

    fun setdata(data: List<NewsEntity>?) {
        if (data == null) return
        this.dataNews.clear()
        this.dataNews.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemNews =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemNews)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = dataNews[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = dataNews.size

    class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: NewsEntity) {
            with(binding) {
                ivNews.loadImage(data.newsImage)
                tvTitleNews.text = data.newsTitle
            }
            itemView.setSafeOnClickListener {
                val mBundle = Bundle()
                mBundle.putString(EXTRA_NEWS_TITLE, data.newsTitle)
                mBundle.putString(EXTRA_NEWS_IMAGE, data.newsImage)
                mBundle.putString(EXTRA_NEWS_BODY, data.newsBody)
                it.findNavController().navigate(R.id.action_homeFragment_to_newsDetailFragment, mBundle)
            }
        }

        private fun ImageView.loadImage(url: String?) {
            Glide.with(this.context)
                .load(url)
                .apply(RequestOptions().override(500, 500))
                .centerCrop()
                .into(this)
        }
    }
}