package com.satudata.home

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satudata.home.databinding.FragmentHomeBinding
import com.satudata.services.model.BeritaModel
import com.satudata.views.extensions.setSafeOnClickListener
import android.os.Parcelable

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var newsAdapter: NewsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //save onpause
    private var category = 0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        sharedPreferences = this.activity?.getSharedPreferences(
            "sharedPrefsDetailUser",
            Context.MODE_PRIVATE
        ) as SharedPreferences

        val profileName = sharedPreferences.getString("profil_name", null)
        binding.tvHi.text = "Hi, $profileName"
        observeDataBerita()

        binding.cvCategoryPemilu2024.setSafeOnClickListener {
            binding.rvNews.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            setColorCategories(it)
            homeViewModel._newsCategory.value = 0
            category = 0
            observeDataBerita()
            Log.e("cekCategory", "observeDataBerita: ${homeViewModel.newsCategory.value}")
            binding.tvCategoryPemilu2024.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

        binding.cvCategoryDprri.setSafeOnClickListener {
            binding.rvNews.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            setColorCategories(it)
            homeViewModel._newsCategory.value = 1
            category = 1
            observeDataBerita()
            Log.e("cekCategory", "observeDataBerita: ${homeViewModel.newsCategory.value}")
            binding.tvCategoryDprri.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

        binding.cvCategoryKpu.setSafeOnClickListener {
            binding.rvNews.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            setColorCategories(it)
            homeViewModel._newsCategory.value = 2
            category = 2
            observeDataBerita()
            Log.e("cekCategory", "observeDataBerita: ${homeViewModel.newsCategory.value}")
            binding.tvCategoryKpu.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

        binding.cvCategorySatudataDprri.setSafeOnClickListener {
            binding.rvNews.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            setColorCategories(it)
            homeViewModel._newsCategory.value = 3
            category = 3
            observeDataBerita()
            Log.e("cekCategory", "observeDataBerita: ${homeViewModel.newsCategory.value}")
            binding.tvCategorySatudataDprri.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        when (homeViewModel.newsCategory.value) {
            0 -> binding.cvCategoryPemilu2024.performClick()
            1 -> binding.cvCategoryDprri.performClick()
            2 -> binding.cvCategoryKpu.performClick()
            3 -> binding.cvCategorySatudataDprri.performClick()
        }
    }

    private fun setColorCategories(view: View?) {
        binding.cvCategoryPemilu2024.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(),
            com.satudata.resources.R.color.md_grey_200
        )
        binding.tvCategoryPemilu2024.setTextColor(
            AppCompatResources.getColorStateList(
                requireContext(),
                com.satudata.resources.R.color.color_primary
            )
        )

        binding.cvCategoryDprri.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(),
            com.satudata.resources.R.color.md_grey_200
        )
        binding.tvCategoryDprri.setTextColor(
            AppCompatResources.getColorStateList(
                requireContext(),
                com.satudata.resources.R.color.color_primary
            )
        )

        binding.cvCategoryKpu.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(),
            com.satudata.resources.R.color.md_grey_200
        )
        binding.tvCategoryKpu.setTextColor(
            AppCompatResources.getColorStateList(
                requireContext(),
                com.satudata.resources.R.color.color_primary
            )
        )

        binding.cvCategorySatudataDprri.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(),
            com.satudata.resources.R.color.md_grey_200
        )

        binding.tvCategorySatudataDprri.setTextColor(
            AppCompatResources.getColorStateList(
                requireContext(),
                com.satudata.resources.R.color.color_primary
            )
        )

        //cardview news change color if clicked
        val cvColorFrom =
            ContextCompat.getColor(requireContext(), com.satudata.resources.R.color.md_grey_200)
        val cvColorTo =
            ContextCompat.getColor(requireContext(), com.satudata.resources.R.color.color_primary)

        val cvColorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), cvColorFrom, cvColorTo)
        cvColorAnimation.duration = 200 // milliseconds

        cvColorAnimation.addUpdateListener { animator ->
            view?.backgroundTintList = ColorStateList.valueOf(animator.animatedValue as Int)
        }
        cvColorAnimation.start()

    }

    private fun observeDataBerita() {
        homeViewModel.getBerita().observe(viewLifecycleOwner) {
            newsAdapter = NewsAdapter()
            with(binding.rvNews) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = this@HomeFragment.newsAdapter
            }
            val dataList = mutableListOf<BeritaModel>()

            Log.e("cekCategory", "observeDataBerita: ${homeViewModel.newsCategory.value}")
            when (homeViewModel.newsCategory.value) {
                0 -> {
                    for (i in it.indices) {
                        if (it[i].kategori_berita == "#Pemilu2024") {
                            dataList.add(it[i])
                            newsAdapter.setdata(dataList)
                        }
                    }
                }

                1 -> {
                    for (i in it.indices) {
                        if (it[i].kategori_berita == "DPR RI") {
                            dataList.add(it[i])
                            newsAdapter.setdata(dataList)
                        }
                    }
                }

                2 -> {
                    for (i in it.indices) {
                        if (it[i].kategori_berita == "KPU") {
                            dataList.add(it[i])
                            newsAdapter.setdata(dataList)
                        }
                    }
                }

                3 -> {
                    for (i in it.indices) {
                        if (it[i].kategori_berita == "Satu Data DPR RI") {
                            dataList.add(it[i])
                            newsAdapter.setdata(dataList)
                        }
                    }
                }
            }
            binding.rvNews.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }

    }


    companion object {
        val EXTRA_NEWS_TITLE = "extra_news_title"
        val EXTRA_NEWS_IMAGE = "extra_news_image"
        val EXTRA_NEWS_BODY = "extra_news_body"
        val EXTRA_NEWS_CATEGORY = "extra_news_category"
        val EXTRA_NEWS_SOURCE = "extra_news_source"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}