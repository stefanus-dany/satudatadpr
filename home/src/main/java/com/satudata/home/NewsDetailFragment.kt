package com.satudata.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.satudata.home.databinding.FragmentNewsDetailBinding
import com.satudata.views.extensions.setSafeOnClickListener

class NewsDetailFragment : Fragment() {

    private var _binding : FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)

        binding.fabBack.setSafeOnClickListener {
            activity?.onBackPressed()
        }

        val newsTitle = arguments?.getString(HomeFragment.EXTRA_NEWS_TITLE)
        val newsImage = arguments?.getString(HomeFragment.EXTRA_NEWS_IMAGE)
        val newsBody = arguments?.getString(HomeFragment.EXTRA_NEWS_BODY)

        binding.tvTitleNews.text = newsTitle
        binding.ivNews.loadImage(newsImage)
        binding.tvBodyNews.setText(newsBody)

        return binding.root
    }

    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().override(500, 500))
            .centerCrop()
            .into(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        arguments = null
    }
}