package com.satudata.dashboard.heatmap

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import com.satudata.dashboard.R
import com.satudata.dashboard.databinding.FragmentHeatmapBinding
import com.satudata.views.extensions.setSafeOnClickListener
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class HeatmapFragment : Fragment(), OnMapReadyCallback, HeatmapAdapter.moveCamera {

    private var _binding: FragmentHeatmapBinding? = null
    private val binding get() = _binding!!
    private lateinit var heatmapAdapter: HeatmapAdapter
    private lateinit var viewModel: HeatmapViewModel
    private lateinit var bottomSheetBehaviour: BottomSheetBehavior<ViewGroup>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeatmapBinding.inflate(inflater, container, false)

//        val mapFragment = fragmentManager?.findFragmentById(R.id.map_fragment) as SupportMapFragment
        //API key in AndroidManifest home module
        val mapFragment =
            childFragmentManager.findFragmentById(com.satudata.dashboard.R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[HeatmapViewModel::class.java]

        bottomSheetBehaviour = BottomSheetBehavior.from(binding.layoutBottomSheet)
        bottomSheetBehaviour.setBottomSheetCallback(bottomSheetBehaviorCallback)

        heatmapAdapter = HeatmapAdapter(this)
        with(binding.rvProvince) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HeatmapFragment.heatmapAdapter
        }
        heatmapAdapter.setdata(getDataProvince())

        return binding.root
    }

    private val bottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            //nothing
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                binding.txtSearch.clearFocus()
            } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val data = generateHeatMapData()

        val heatMapProvider = HeatmapTileProvider.Builder()
            .weightedData(data) // load our weighted data
            .radius(50) // optional, in pixels, can be anything between 20 and 50
            .maxIntensity(1000.0) // set the maximum intensity
            .build()

        googleMap.addTileOverlay(TileOverlayOptions().tileProvider(heatMapProvider))

        observeDataProvince(googleMap)

        binding.fabBack.setSafeOnClickListener {
            activity?.onBackPressed()
        }

//        binding.btnBali.setSafeOnClickListener {
//            val baliLatLng = LatLng(-8.335517, 115.178447)
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(baliLatLng, 8f))
////            val mapRipple: MapRipple = MapRipple(googleMap, baliLatLng, context)
////
////            mapRipple.startRippleMapAnimation()
//        }
//
//        binding.btnMalang.setSafeOnClickListener {
//            val malangLatLng = LatLng(-7.959599, 112.634526)
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(malangLatLng, 8f))
////            val newarkLatLng = LatLng(40.714086, -74.228697)
////            val newarkMap = GroundOverlayOptions()
////                .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_password))
////                .position(newarkLatLng, 8600f, 6500f)
////            googleMap.addGroundOverlay(newarkMap)
//        }

        googleMap.setMaxZoomPreference(8f)
        googleMap.setMinZoomPreference(5f)

        val mainLatLng = LatLng(-5.082689, 112.101298)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mainLatLng, 5f))
    }

    private fun observeDataProvince(googleMap: GoogleMap) {
        viewModel.lat.observe(viewLifecycleOwner) { lat ->
            viewModel.long.observe(viewLifecycleOwner) { long ->
                val tmpLatLng = LatLng(lat, long)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(tmpLatLng, 8f))
            }
        }


    }

    private fun speak() {
        val move = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        move.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        val locale = Locale("id", "ID")
        move.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale)
        move.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now!")

        try {
            startActivityForResult(move, 100)
        } catch (e: Exception) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            100 -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
//                    binding.searchview.setQuery(result?.get(0)?.toString(), false)
//                    binding.searchview.isIconified = false
                }
            }
        }
    }

    private fun getDataProvince(): ArrayList<HeatmapEntity> {
        val data = ArrayList<HeatmapEntity>()

        val jsonData = getJsonDataFromAsset("district_data.json")
        jsonData?.let {
            for (i in 0 until it.length()) {
                val entry = it.getJSONObject(i)
                val id = entry.getInt("id")
                val name = entry.getString("name")
                val lat = entry.getDouble("latitude")
                val long = entry.getDouble("longitude")
                val total = entry.getLong("total")
                data.add(
                    HeatmapEntity(
                        provinceId = id,
                        provinceName = name,
                        provinceData = total,
                        latitude = lat,
                        longitude = long
                    )
                )
            }
        }

        return data
    }

    private fun generateHeatMapData(): ArrayList<WeightedLatLng> {
        val data = ArrayList<WeightedLatLng>()

        val jsonData = getJsonDataFromAsset("district_data.json")
        jsonData?.let {
            for (i in 0 until it.length()) {
                val entry = it.getJSONObject(i)
                val lat = entry.getDouble("latitude")
                val lon = entry.getDouble("longitude")
                val density = entry.getDouble("density")

                if (density != 0.0) {
                    val weightedLatLng = WeightedLatLng(LatLng(lat, lon), density)
                    data.add(weightedLatLng)
                }
            }
        }

        return data
    }

    private fun getJsonDataFromAsset(fileName: String): JSONArray? {
        return try {
            val jsonString =
                context?.assets?.open(fileName)?.bufferedReader().use { it?.readText() }
            JSONArray(jsonString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun moveCameraWithAnimation(lat: Double, long: Double) {
        viewModel._lat.value = lat
        viewModel._long.value = long
        bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}