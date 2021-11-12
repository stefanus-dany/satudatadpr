package com.satudata.adammalik.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.satudata.adammalik.R
import com.satudata.adammalik.databinding.FragmentOnboardingBinding
import com.satudata.dashboard.databinding.FragmentDashboardBinding

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        val scaleTypes = ScaleTypes.FIT
        imageList.add(SlideModel(com.satudata.dashboard.R.drawable.one, "The animal population decreased by 58 percent in 42 years.", scaleTypes))
        imageList.add(SlideModel(com.satudata.dashboard.R.drawable.two, "Elephants and tigers may become extinct.", scaleTypes))
        imageList.add(SlideModel(com.satudata.dashboard.R.drawable.three, "And people do that.", scaleTypes))

        binding.imageSliderOnboarding.setImageList(imageList)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}