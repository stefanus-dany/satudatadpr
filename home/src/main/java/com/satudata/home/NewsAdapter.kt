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
import com.satudata.home.HomeFragment.Companion.EXTRA_NEWS_CATEGORY
import com.satudata.home.HomeFragment.Companion.EXTRA_NEWS_IMAGE
import com.satudata.home.HomeFragment.Companion.EXTRA_NEWS_SOURCE
import com.satudata.home.HomeFragment.Companion.EXTRA_NEWS_TITLE
import com.satudata.home.databinding.ItemNewsBinding
import com.satudata.services.model.BeritaModel
import com.satudata.views.extensions.setSafeOnClickListener

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var dataNews = ArrayList<BeritaModel>()

    fun setdata(data: List<BeritaModel>?) {
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

        fun bind(data: BeritaModel) {
            with(binding) {
                ivNews.loadImage(data.gambar_berita)
                tvTitleNews.text = data.judul_berita
            }
            itemView.setSafeOnClickListener {
                val mBundle = Bundle()
                mBundle.putString(EXTRA_NEWS_TITLE, data.judul_berita)
                mBundle.putString(EXTRA_NEWS_IMAGE, data.gambar_berita)
                mBundle.putString(EXTRA_NEWS_BODY, data.isi_berita)
                mBundle.putString(EXTRA_NEWS_CATEGORY, data.kategori_berita)
                mBundle.putString(EXTRA_NEWS_SOURCE, data.source)
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