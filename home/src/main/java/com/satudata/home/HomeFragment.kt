package com.satudata.home

//import com.satudata.home.databinding.FragmentHomeBinding

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satudata.home.databinding.FragmentHomeBinding
import com.satudata.views.extensions.setSafeOnClickListener


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var newsAdapter: NewsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        newsAdapter = NewsAdapter()
        with(binding.rvNews) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@HomeFragment.newsAdapter
        }

        val listNews = arrayListOf<NewsEntity>()
        listNews.add(
            NewsEntity(
                newsId = 0,
                newsTitle = "Mikhael mencalonkan diri di pilpres 2024?",
                newsImage = "https://media-exp1.licdn.com/dms/image/C5603AQFG9T5qtcdaLA/profile-displayphoto-shrink_200_200/0/1630460515650?e=1643846400&v=beta&t=q8zCEvEd49xTemeyiUA0yCQ0k1c1aZRSi8VoSOeyq9w",
                newsBody = "Ini punya mikhael\nWhat is Lorem Ipsum?\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                        "\n" +
                        "Why do we use it?\n" +
                        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                        "\n" +
                        "\n" +
                        "Where does it come from?\n" +
                        "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                        "\n" +
                        "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                        "\n" +
                        "Where can I get some?\n" +
                        "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" +
                        "\n" +
                        "5\n" +
                        "\tparagraphs\n" +
                        "\twords\n" +
                        "\tbytes\n" +
                        "\tlists\n" +
                        "\tStart with 'Lorem\n" +
                        "ipsum dolor sit amet...'\n"
            )
        )

        listNews.add(
            NewsEntity(
                newsId = 1,
                newsTitle = "Wow! Jonathan mencari wakil untuk mengungguli lawannya!",
                newsImage = "https://media-exp1.licdn.com/dms/image/C5603AQGL7Sj0cqsOmg/profile-displayphoto-shrink_200_200/0/1604813790197?e=1643846400&v=beta&t=hqpnvdAOn4fNo-R5nLkkv3VvW6WS9RdWBzsGd1Tfk0Y",
                newsBody = "Ini punya jonathan\nWhat is Lorem Ipsum?\n" +
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                        "\n" +
                        "Why do we use it?\n" +
                        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
                        "\n" +
                        "\n" +
                        "Where does it come from?\n" +
                        "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
                        "\n" +
                        "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
                        "\n" +
                        "Where can I get some?\n" +
                        "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" +
                        "\n" +
                        "5\n" +
                        "\tparagraphs\n" +
                        "\twords\n" +
                        "\tbytes\n" +
                        "\tlists\n" +
                        "\tStart with 'Lorem\n" +
                        "ipsum dolor sit amet...'\n"
            )
        )
//        listNews.add(
//            NewsEntity(
//                newsId = 2,
//                newsTitle = "Tsara: UU akan direvisi, Pemilu 2024 akan semakin canggih!",
//                newsImage = "https://media-exp1.licdn.com/dms/image/C5603AQEZ2bAkv68SPw/profile-displayphoto-shrink_400_400/0/1615552636422?e=1643846400&v=beta&t=N8PESVw60zppdUepCDwP8Chr_rq-rFxzlSRpyL5hA5w",
//                newsBody = "Ini punya tsara\nWhat is Lorem Ipsum?\n" +
//                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
//                        "\n" +
//                        "Why do we use it?\n" +
//                        "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).\n" +
//                        "\n" +
//                        "\n" +
//                        "Where does it come from?\n" +
//                        "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
//                        "\n" +
//                        "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.\n" +
//                        "\n" +
//                        "Where can I get some?\n" +
//                        "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.\n" +
//                        "\n" +
//                        "5\n" +
//                        "\tparagraphs\n" +
//                        "\twords\n" +
//                        "\tbytes\n" +
//                        "\tlists\n" +
//                        "\tStart with 'Lorem\n" +
//                        "ipsum dolor sit amet...'\n"
//            )
//        )
        newsAdapter.setdata(listNews)

        binding.cvCategoryPemilu2024.setSafeOnClickListener {
            setColorCategories(it)
            binding.tvCategoryPemilu2024.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

        binding.cvCategoryDprri.setSafeOnClickListener {
            setColorCategories(it)
            binding.tvCategoryDprri.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

        binding.cvCategorySetjenDprri.setSafeOnClickListener {
            setColorCategories(it)
            binding.tvCategorySetjenDprri.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

        binding.cvCategorySatudataDprri.setSafeOnClickListener {
            setColorCategories(it)
            binding.tvCategorySatudataDprri.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    com.satudata.resources.R.color.color_secondary
                )
            )
        }

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

//        val imageList = ArrayList<SlideModel>() // Create image list
//
//// imageList.add(SlideModel("String Url" or R.drawable)
//// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title
//
//        val scaleTypes = ScaleTypes.FIT
//        imageList.add(
//            SlideModel(
//                R.drawable.one,
//                "The animal population decreased by 58 percent in 42 years.",
//                scaleTypes
//            )
//        )
//        imageList.add(
//            SlideModel(
//                R.drawable.two,
//                "Elephants and tigers may become extinct.",
//                scaleTypes
//            )
//        )
//        imageList.add(SlideModel(R.drawable.three, "And people do that.", scaleTypes))

//        binding.imageSlider.setImageList(imageList)
//
//        binding.runningTextHeatmap.ellipsize = TextUtils.TruncateAt.MARQUEE
//        binding.runningTextHeatmap.isSelected = true
//
//        binding.tvMoreHeatmap.setSafeOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_heatmapFragment)
//        }

        return binding.root
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

        binding.cvCategorySetjenDprri.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(),
            com.satudata.resources.R.color.md_grey_200
        )
        binding.tvCategorySetjenDprri.setTextColor(
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

    companion object {
        val EXTRA_NEWS_TITLE = "extra_news_title"
        val EXTRA_NEWS_IMAGE = "extra_news_image"
        val EXTRA_NEWS_BODY = "extra_news_body"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}