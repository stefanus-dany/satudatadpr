package com.satudata.dashboard

import android.R
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.satudata.dashboard.databinding.FragmentDashboardBinding
import com.satudata.services.model.HeatmapEntity
import com.satudata.views.extensions.setSafeOnClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var year = "2014"
    private var category = "Pemilihan Legislatif"
    private var countTotalData = HeatmapEntity()

    private lateinit var mBundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        observeDataAnggaran()

        getCountTotalData(init = true)

        getMarqueeText()

        mBundle = Bundle()
        mBundle.putString(EXTRA_YEAR, year)
        mBundle.putString(EXTRA_CATEGORY, category)
        binding.tvMore.setSafeOnClickListener {
            Log.i("category", "onCreateView: $year $category")
            findNavController().navigate(
                com.satudata.dashboard.R.id.action_dashboardFragment_to_heatmapFragment,
                mBundle
            )
        }

        binding.ivHeatmap.setSafeOnClickListener {
            Log.i("category", "onCreateView: $year $category")
            findNavController().navigate(
                com.satudata.dashboard.R.id.action_dashboardFragment_to_heatmapFragment,
                mBundle
            )
        }

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            resources.getStringArray(com.satudata.dashboard.R.array.drop_down_year_list)
        )
        binding.spinnerDashboardYear.adapter = adapter
        binding.spinnerDashboardYear.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            year = "2014"
                            mBundle.putString(EXTRA_YEAR, year)
                            getCountTotalData(init = false)
                            getMarqueeText()
                        }

                        1 -> {
                            year = "2019"
                            mBundle.putString(EXTRA_YEAR, year)
                            getCountTotalData(init = false)
                            getMarqueeText()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

        val adapterCategory = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            resources.getStringArray(com.satudata.resources.R.array.drop_down_golput_category)
        )
        binding.spinnerDashboardCategory.adapter = adapterCategory
        binding.spinnerDashboardCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            category = "Pemilihan Legislatif"
                            mBundle.putString(EXTRA_CATEGORY, category)
                            getCountTotalData(init = false)
                            getMarqueeText()

                        }

                        1 -> {
                            category = "Pemilihan Presiden"
                            mBundle.putString(EXTRA_CATEGORY, category)
                            getCountTotalData(init = false)
                            getMarqueeText()

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }


        return binding.root
    }


    private fun barChart() {
        val corruptionData: ArrayList<BarEntry> = ArrayList()
        corruptionData.add(BarEntry(1f, 400f))
        corruptionData.add(BarEntry(2f, 500f))
        corruptionData.add(BarEntry(3f, 600f))
        corruptionData.add(BarEntry(4f, 700f))

        val pemiluData: ArrayList<BarEntry> = ArrayList()
        pemiluData.add(BarEntry(1f, 700f))
        pemiluData.add(BarEntry(2f, 200f))
        pemiluData.add(BarEntry(3f, 100f))
        pemiluData.add(BarEntry(4f, 50f))

        val barDataSet = BarDataSet(corruptionData, "2014")
//        barDataSet.colors = ColorTemplate.MATERIAL_COLORS
//        barDataSet.setValueTextColors(Color.BLACK)
        barDataSet.valueTextSize = 14f

        val barDataSetPemilu = BarDataSet(pemiluData, "2019")
        barDataSetPemilu.color = Color.GRAY
//        barDataSet.colors = ColorTemplate.MATERIAL_COLORS
//        barDataSet.setValueTextColors(Color.BLACK)
        barDataSetPemilu.valueTextSize = 16f

        val years = arrayOf("2018", "2019", "2020", "2021")
        val xAxis: XAxis = binding.barChart.xAxis
        with(xAxis) {
            valueFormatter = IndexAxisValueFormatter(years)
            setCenterAxisLabels(true)
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            isGranularityEnabled = true

        }

        val barData = BarData(barDataSet, barDataSetPemilu)
        barData.setValueFormatter(LargeValueFormatter())
        binding.barChart.apply {
//            setFitBars(true)
            data = barData
            description.text = "Anggaran Pemilu (dalam triliun)"
            animateY(2000)
            isDragEnabled = true
            setVisibleXRangeMaximum(3f)

            val barSpace = 0.05f
            val groupSpace = 0.58f
            barData.barWidth = 0.16f

            getXAxis().axisMinimum = 0f
            getXAxis().axisMaximum =
                0 + binding.barChart.barData.getGroupWidth(groupSpace, barSpace) * 7
            axisLeft.axisMinimum = 0f
            groupBars(0f, groupSpace, barSpace)
            invalidate()
        }
    }

    private fun getCountTotalData(init: Boolean) {
        dashboardViewModel.getCountTotalData(year, category).observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.Default) {
                countTotalData = it
                Log.i("cpount", "onResponse1: $it")


                withContext(Dispatchers.Main) {
                    binding.scrollView.visibility = View.VISIBLE
                    //animation for each dash1, dash2, dash3, and dash4

                    if (init) {
                        hideView(binding.layoutDash1)
                        hideView2(binding.layoutDash2)
                        hideView3(binding.layoutDash3)
                        hideView4(binding.layoutDash4)
                    } else {
                        startCountAnimation()
                    }
                }

            }


        }


    }

    private fun getMarqueeText(){
        var text = "Jumlah Golput: "
        dashboardViewModel.getGolput(year, category).observe(viewLifecycleOwner){
            for (i in it.indices){
                text += "${it[i].nama_provinsi} (${it[i].total_golput}), "
            }
            binding.runningTextHeatmap.ellipsize = TextUtils.TruncateAt.MARQUEE
            binding.runningTextHeatmap.isSelected = true
            binding.runningTextHeatmap.text =text
        }

    }

    //animation for counter number dash1, dash2, dash3, and dash4
    private fun startCountAnimation() {
        val animatorDash1 = ValueAnimator.ofInt(0, countTotalData.populasi)
        animatorDash1.duration = 2500
        animatorDash1.addUpdateListener {
            try {
                binding.tvBodyDash1.text = it.animatedValue.toString()
            } catch (e: NullPointerException) {

            }
        }
        animatorDash1.start()

        val animatorDash2 = ValueAnimator.ofInt(0, countTotalData.dpt)
        animatorDash2.duration = 2500
        animatorDash2.addUpdateListener {
            try {
                binding.tvBodyDash2.text = it.animatedValue.toString()
            } catch (e: NullPointerException) {

            }
        }
        animatorDash2.start()

        val animatorDash3 = ValueAnimator.ofInt(0, countTotalData.rekapitulasi)
        animatorDash3.duration = 2500
        animatorDash3.addUpdateListener {
            try {
                binding.tvBodyDash3.text = it.animatedValue.toString()
            } catch (e: NullPointerException) {

            }
        }
        animatorDash3.start()

        val animatorDash4 = ValueAnimator.ofInt(0, countTotalData.total_golput)
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

    private fun observeDataAnggaran() {
        dashboardViewModel.getAnggaran().observe(viewLifecycleOwner) {
//            val years = arrayOf("2018", "2019", "2020", "2021")
//            for (i in it.indices) {
//                val namaAnggaran = arrayOf(it[i].nama_anggaran)
//                val namaAnggaran = arrayOf(it[i].nama_anggaran)
//
//            }

            val data2014: ArrayList<BarEntry> = ArrayList()
            var x2014 = 1f
            for (i in it.indices) {
                if (it[i].tahun == "2014") {
                    data2014.add(BarEntry(x2014, it[i].total.toFloat()))
                    x2014++
                }

            }
//            data2014.add(BarEntry(1f, 400f))
//            data2014.add(BarEntry(2f, 500f))
//            data2014.add(BarEntry(3f, 600f))
//            data2014.add(BarEntry(4f, 700f))

            val data2019: ArrayList<BarEntry> = ArrayList()
            var x2019 = 1f
            for (i in it.indices) {
                if (it[i].tahun == "2019") {
                    data2019.add(BarEntry(x2019, it[i].total.toFloat()))
                    x2019++
                }
            }
//            data2019.add(BarEntry(1f, 700f))
//            data2019.add(BarEntry(2f, 200f))
//            data2019.add(BarEntry(3f, 100f))
//            data2019.add(BarEntry(4f, 50f))

            val barDataSet2014 = BarDataSet(data2014, "2014")
//        barDataSet.colors = ColorTemplate.MATERIAL_COLORS
//        barDataSet.setValueTextColors(Color.BLACK)
            barDataSet2014.valueTextSize = 13f

            val barDataSet2019 = BarDataSet(data2019, "2019")
            barDataSet2019.color = Color.GRAY
//        barDataSet.colors = ColorTemplate.MATERIAL_COLORS
//        barDataSet.setValueTextColors(Color.BLACK)
            barDataSet2019.valueTextSize = 13f

            val namaAnggaranTmp: ArrayList<String> = ArrayList()
            for (i in it.indices) {
                namaAnggaranTmp.add(it[i].nama_anggaran)
            }
            val namaAnggaran = namaAnggaranTmp.distinct()
//            val years = arrayOf("2018", "2019", "2020", "2021")
            val xAxis: XAxis = binding.barChart.xAxis
            with(xAxis) {
                valueFormatter = IndexAxisValueFormatter(namaAnggaran)
                setCenterAxisLabels(true)
                position = XAxis.XAxisPosition.TOP
                granularity = 1f
                isGranularityEnabled = true

            }

            val barData = BarData(barDataSet2014, barDataSet2019)
            barData.setValueFormatter(LargeValueFormatter())
            binding.barChart.apply {
                setFitBars(true)
                data = barData
                description.text = "dalam triliun"
                animateY(2000)
                isDragEnabled = true
                setVisibleXRangeMaximum(3f)
//                zoomToCenter(10f, 10f)
//                zoom(0f,0f,3f,3f)
                //(barspace + barwidth) * 2 + groupspace must be 1
                val barSpace = 0.05f
                barData.barWidth = 0.16f
                val groupSpace = 0.58f

                getXAxis().axisMinimum = 0f
                getXAxis().axisMaximum =
                    0 + binding.barChart.barData.getGroupWidth(groupSpace, barSpace) * 5
                axisLeft.axisMinimum = 0f
                groupBars(0f, groupSpace, barSpace)
                invalidate()
            }

        }
    }

    companion object {
        const val EXTRA_YEAR = "extra_year"
        const val EXTRA_CATEGORY = "extra_category"
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}