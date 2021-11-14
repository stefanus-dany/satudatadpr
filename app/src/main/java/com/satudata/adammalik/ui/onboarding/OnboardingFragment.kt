package com.satudata.adammalik.ui.onboarding

import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.satudata.adammalik.R
import com.satudata.adammalik.databinding.FragmentOnboardingBinding
import com.satudata.adammalik.ui.adapter.SliderAdapter
import com.satudata.views.extensions.setSafeOnClickListener


class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private lateinit var listTitle: Array<String>
    private lateinit var listSubTitle: Array<String>
    private var dots = arrayOfNulls<TextView>(3)
    private lateinit var adapter: SliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        //onboarding message
        listTitle = arrayOf("Informasi Korupsi", "Grafik", "Kinerja Penegakan Korupsi")
        listSubTitle = arrayOf(
            "Merangkum informasi penting terkait kasus korupsi.",
            "Menggunakan berbagai grafik dan penjelasan yang mudah dipahami.",
            "Demi meningkatnya kinerja penegakan korupsi serta SDM yang mumpuni."
        )

        adapter = SliderAdapter(listTitle, listSubTitle)
        binding.viewPagerOnboarding.adapter = adapter
        startAutoSlider(listTitle.size)

        binding.viewPagerOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedIndicator(position)
                super.onPageSelected(position)
            }
        })

        dotsIndicator()

        binding.btnLogin.setSafeOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }

        binding.btnRegister.setSafeOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_registerFragment)
        }

        return binding.root
    }

//    private fun makeCurrentFragment(fragment: Fragment) {
//        fragmentManager?.beginTransaction()?.apply {
//            replace(R.id.fl_wrapper, fragment)
//            addToBackStack(null)
//            commit()
//        }
//    }

    //handler for time onborading move to another view
    val mHandler: Handler = Handler()
    private var runnable: Runnable? = null
    private fun startAutoSlider(count: Int) {
        runnable = Runnable {
            var pos: Int = binding.viewPagerOnboarding.currentItem
            pos += 1
            if (pos >= count) pos = 0
            binding.viewPagerOnboarding.currentItem = pos
            runnable?.let { mHandler.postDelayed(it, 4000) }
        }
        mHandler.postDelayed(runnable!!, 4000)
    }

    private fun selectedIndicator(position: Int) {
        for (i in dots.indices) {
            if (i == position) {
                //many colors
//                dots[i]?.setTextColor(list[position])
                //white only color
                dots[i]?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            } else {
                dots[i]?.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.md_deep_orange_200
                    )
                )
            }
        }
    }

    private fun dotsIndicator() {
        for (i in 0 until 3) {
            dots[i] = TextView(requireContext())
            dots[i]?.text = Html.fromHtml("&#9679")
            dots[i]?.textSize = 18F
            binding.dotsContainer.addView(dots[i])
        }
    }

    override fun onPause() {
        super.onPause()
        if (runnable != null) mHandler.removeCallbacks(runnable!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}