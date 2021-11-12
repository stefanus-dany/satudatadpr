package com.satudata.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.satudata.dashboard.databinding.FragmentDashboardBinding

//import com.satudata.dashboard.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val imageList = ArrayList<SlideModel>() // Create image list

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        val scaleTypes = ScaleTypes.FIT
        imageList.add(SlideModel(R.drawable.one, "The animal population decreased by 58 percent in 42 years.", scaleTypes))
        imageList.add(SlideModel(R.drawable.two, "Elephants and tigers may become extinct.", scaleTypes))
        imageList.add(SlideModel(R.drawable.three, "And people do that.", scaleTypes))

        binding.imageSlider.setImageList(imageList)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}