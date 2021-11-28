package com.satudata.home

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.satudata.home.databinding.FragmentHomeBinding
import com.satudata.views.extensions.setSafeOnClickListener

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

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
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        val scaleTypes = ScaleTypes.FIT
        imageList.add(
            SlideModel(
                R.drawable.one,
                "The animal population decreased by 58 percent in 42 years.",
                scaleTypes
            )
        )
        imageList.add(
            SlideModel(
                R.drawable.two,
                "Elephants and tigers may become extinct.",
                scaleTypes
            )
        )
        imageList.add(SlideModel(R.drawable.three, "And people do that.", scaleTypes))

        binding.imageSlider.setImageList(imageList)

        binding.runningTextHeatmap.ellipsize = TextUtils.TruncateAt.MARQUEE
        binding.runningTextHeatmap.isSelected = true

        binding.tvMoreHeatmap.setSafeOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_heatmapFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}