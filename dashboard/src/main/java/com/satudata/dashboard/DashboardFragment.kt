package com.satudata.dashboard

import android.R
import android.animation.ValueAnimator
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.satudata.dashboard.databinding.FragmentDashboardBinding
import com.satudata.views.extensions.setSafeOnClickListener


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
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

//        val dashboardFragment = DashboardFragment()
//        makeCurrentFragment(dashboardFragment)
//
        binding.runningTextHeatmap.ellipsize = TextUtils.TruncateAt.MARQUEE
        binding.runningTextHeatmap.isSelected = true
        binding.tvMore.setSafeOnClickListener {
            findNavController().navigate(com.satudata.dashboard.R.id.action_dashboardFragment_to_heatmapFragment)
        }

        binding.ivHeatmap.setSafeOnClickListener {
            findNavController().navigate(com.satudata.dashboard.R.id.action_dashboardFragment_to_heatmapFragment)
        }

        //animation for each dash1, dash2, dash3, and dash4
        hideView(binding.layoutDash1)
        hideView2(binding.layoutDash2)
        hideView3(binding.layoutDash3)
        hideView4(binding.layoutDash4)


        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            resources.getStringArray(com.satudata.dashboard.R.array.drop_down_dashboard_array)
        )
//        binding.spinnerDashboard.adapter = adapter
//        binding.spinnerDashboard.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    when (position) {
//                        1 -> makeCurrentFragment(BarChartFragment())
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//
//            }


        return binding.root
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(com.satudata.dashboard.R.id.fl_container_chart, fragment)
            addToBackStack(null)
            commit()
        }
    }

    //animation for counter number dash1, dash2, dash3, and dash4
    private fun startCountAnimation() {
        val animatorDash1 = ValueAnimator.ofInt(0, 273500000)
        animatorDash1.duration = 2500
        animatorDash1.addUpdateListener {
            try {
                binding.tvBodyDash1.text = it.animatedValue.toString()
            } catch (e: NullPointerException) {

            }
        }
        animatorDash1.start()

        val animatorDash2 = ValueAnimator.ofInt(0, 201301000)
        animatorDash2.duration = 2500
        animatorDash2.addUpdateListener {
            try {
                binding.tvBodyDash2.text = it.animatedValue.toString()
            } catch (e: NullPointerException) {

            }
        }
        animatorDash2.start()

        val animatorDash3 = ValueAnimator.ofInt(0, 199301000)
        animatorDash3.duration = 2500
        animatorDash3.addUpdateListener {
            try {
                binding.tvBodyDash3.text = it.animatedValue.toString()
            } catch (e: NullPointerException) {

            }
        }
        animatorDash3.start()

        val animatorDash4 = ValueAnimator.ofInt(0, 2000000)
        animatorDash4.duration = 2500
        animatorDash4.addUpdateListener {
            try {
                binding.tvBodyDash4.text = it.animatedValue.toString()
            } catch (e: NullPointerException) {

            }
        }
        animatorDash4.start()
    }

    //animation for each dash1, dash2, dash3, and dash4
    private fun hideView(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(
            requireContext(),
            com.satudata.resources.R.anim.enter_and_fadein_from_right
        )
        //use this to make it longer:  animation.setDuration(1000);
        animation.duration = 1000
        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                //start counter number dash
                startCountAnimation()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
//                view.visibility = View.GONE
            }
        })
        view.startAnimation(animation)
    }

    private fun hideView2(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(
            requireContext(),
            com.satudata.resources.R.anim.enter_and_fadein_from_left
        )
        //use this to make it longer:  animation.setDuration(1000);
        animation.duration = 1000
        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                //start counter number dash
                startCountAnimation()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
//                view.visibility = View.GONE
            }
        })
        view.startAnimation(animation)
    }

    private fun hideView3(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(
            requireContext(),
            com.satudata.resources.R.anim.enter_and_fadein_from_right
        )
        //use this to make it longer:  animation.setDuration(1000);
        animation.duration = 1000
        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                //start counter number dash
                startCountAnimation()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
//                view.visibility = View.GONE
            }
        })
        view.startAnimation(animation)
    }

    private fun hideView4(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(
            requireContext(),
            com.satudata.resources.R.anim.enter_and_fadein_from_left
        )
        //use this to make it longer:  animation.setDuration(1000);
        animation.duration = 1000
        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                //start counter number dash
                startCountAnimation()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
//                view.visibility = View.GONE
            }
        })
        view.startAnimation(animation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}