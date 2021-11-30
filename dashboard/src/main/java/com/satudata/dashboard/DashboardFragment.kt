package com.satudata.dashboard

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
//        binding.runningTextHeatmap.ellipsize = TextUtils.TruncateAt.MARQUEE
//        binding.runningTextHeatmap.isSelected = true
//        binding.ivHeatmap.setSafeOnClickListener {
//            findNavController().navigate(R.id.)
//        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}