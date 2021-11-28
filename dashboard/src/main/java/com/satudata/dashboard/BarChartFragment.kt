package com.satudata.dashboard

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.satudata.dashboard.databinding.FragmentBarChartBinding
import com.github.mikephil.charting.formatter.LargeValueFormatter

class BarChartFragment : Fragment() {

    private var _binding: FragmentBarChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBarChartBinding.inflate(inflater, container, false)

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

        val barDataSet = BarDataSet(corruptionData, "Data Korupsi")
//        barDataSet.colors = ColorTemplate.MATERIAL_COLORS
//        barDataSet.setValueTextColors(Color.BLACK)
        barDataSet.valueTextSize = 16f

        val barDataSetPemilu = BarDataSet(pemiluData, "Data Pemilu")
        barDataSetPemilu.color = Color.GRAY
//        barDataSet.colors = ColorTemplate.MATERIAL_COLORS
//        barDataSet.setValueTextColors(Color.BLACK)
        barDataSetPemilu.valueTextSize = 16f

        val years = arrayOf("2018", "2019", "2020", "2021")
        val xAxis : XAxis = binding.barChart.xAxis
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
            description.text = "Bar Chart Korupsi"
            animateY(2000)
            isDragEnabled = true
            setVisibleXRangeMaximum(3f)

            val barSpace = 0.05f
            val groupSpace = 0.58f
            barData.barWidth = 0.16f

            getXAxis().axisMinimum = 0f
            getXAxis().axisMaximum = 0+binding.barChart.barData.getGroupWidth(groupSpace, barSpace)*7
            axisLeft.axisMinimum = 0f
            groupBars(0f, groupSpace, barSpace)
            invalidate()
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}