package com.satudata.dashboard.heatmap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satudata.dashboard.databinding.ItemListProvinceBinding
import com.satudata.services.model.HeatmapEntity
import com.satudata.views.extensions.setSafeOnClickListener

class HeatmapAdapter(private var mCallback: moveCamera) :
    RecyclerView.Adapter<HeatmapAdapter.HeatmapViewHolder>() {
    private var dataProvince = ArrayList<HeatmapEntity>()

    fun setdata(data: List<HeatmapEntity>?) {
        if (data == null) return
        this.dataProvince.clear()
        this.dataProvince.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeatmapViewHolder {
        val itemHeatmap =
            ItemListProvinceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeatmapViewHolder(itemHeatmap, mCallback)
    }

    override fun onBindViewHolder(holder: HeatmapViewHolder, position: Int) {
        val data = dataProvince[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = dataProvince.size

    class HeatmapViewHolder(
        private val binding: ItemListProvinceBinding,
        private val mCallback: moveCamera
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: HeatmapEntity) {
            with(binding) {
                tvProvince.text = data.nama_provinsi
                tvPopulasi.text = "Jumlah Populasi: ${data.populasi}"
                tvDpt.text = "Jumlah Daftar Pemilih Tetap (DPT): ${data.dpt}"
                tvRekap.text = "Jumlah Rekapitulasi: ${data.rekapitulasi}"
                tvGolput.text = "Jumlah Golongan Putih (Golput): ${data.total_golput}"
            }
            itemView.setSafeOnClickListener {
                mCallback.moveCameraWithAnimation(data.latitude, data.longitude)
            }
        }
    }

    interface moveCamera {
        fun moveCameraWithAnimation(lat: Double, long: Double)
    }
}