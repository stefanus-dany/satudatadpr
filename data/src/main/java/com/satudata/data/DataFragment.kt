package com.satudata.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.satudata.data.databinding.FragmentDataBinding
import com.satudata.services.api.RetrofitServer
import com.satudata.services.response.data.PopulationResponse
import de.codecrafters.tableview.TableView
import de.codecrafters.tableview.model.TableColumnDpWidthModel
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response


class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    private lateinit var viewModel: DataViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val populationData: ArrayList<Array<String>> = arrayListOf()

//    val DATA_TO_SHOW =
//        arrayOf(arrayOf("This", "is", "a", "test"), arrayOf("and", "a", "second", "test"))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[DataViewModel::class.java]

        _binding = FragmentDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding.tableView.columnCount = 3

        val columnModel = TableColumnDpWidthModel(context, 4, 200)
        columnModel.setColumnWidth(0, 250)
        columnModel.setColumnWidth(1, 200)
        columnModel.setColumnWidth(2, 100)
        columnModel.setColumnWidth(3, 100)
//        val columnModel = TableColumnWeightModel(3)
//        columnModel.setColumnWeight(1, 1)
//        columnModel.setColumnWeight(2, 1)
//        columnModel.setColumnWeight(3, 1)
//        columnModel.setColumnWeight(4, 1)
//        columnModel.setColumnWeight(3, 1)
//        columnModel.setColumnWeight(4, 1)
//        columnModel.setColumnWeight(5, 1)
//        columnModel.setColumnWeight(6, 1)
//        columnModel.setColumnWeight(7, 1)

        binding.tableView.columnModel = columnModel
//        getPopulation()
//        observeDataPopulation()
//        observeDataRekapitulasi()
        observeDataDPT()
//        val tableView: TableView<Array<String>> =
//            binding.tableView as TableView<Array<String>>
////        tableView.dataAdapter = SimpleTableDataAdapter(requireContext(), tmpData)
//        tableView.headerAdapter =
//            SimpleTableHeaderAdapter(requireContext(), "Provinsi", "Tahun", "Total")
//        tableView.setColumnComparator(0, CarProducerComparator())
//        binding.tableView.setColumnComparator(1, comparatorString())
        return root
    }

    private fun observeDataPopulation() {
        viewModel.getPopulation().observe(viewLifecycleOwner) {
            val columnModel = TableColumnDpWidthModel(context, 3, 200)
            columnModel.setColumnWidth(0, 300)
            columnModel.setColumnWidth(1, 100)
            columnModel.setColumnWidth(2, 100)
            binding.tableView.columnModel = columnModel

            val tableView: TableView<Array<String>> =
                binding.tableView as TableView<Array<String>>
            tableView.dataAdapter =
                SimpleTableDataAdapter(requireContext(), it)
            tableView.headerAdapter =
                SimpleTableHeaderAdapter(
                    context,
                    "Provinsi",
                    "Tahun",
                    "Total"
                )

        }
    }

    private fun observeDataRekapitulasi() {
        viewModel.getRekapitulasi().observe(viewLifecycleOwner) {
            val columnModel = TableColumnDpWidthModel(context, 4, 200)
            columnModel.setColumnWidth(0, 250)
            columnModel.setColumnWidth(1, 200)
            columnModel.setColumnWidth(2, 100)
            columnModel.setColumnWidth(3, 100)
            binding.tableView.columnModel = columnModel

            val tableView: TableView<Array<String>> =
                binding.tableView as TableView<Array<String>>
            tableView.dataAdapter =
                SimpleTableDataAdapter(requireContext(), it)
            tableView.headerAdapter =
                SimpleTableHeaderAdapter(
                    context,
                    "Provinsi",
                    "Nama Pemilu",
                    "Tahun",
                    "Total"
                )

        }
    }

    private fun observeDataDPT() {
        viewModel.getDPT().observe(viewLifecycleOwner) {
            val columnModel = TableColumnDpWidthModel(context, 3, 200)
            columnModel.setColumnWidth(0, 300)
            columnModel.setColumnWidth(1, 100)
            columnModel.setColumnWidth(2, 100)
            binding.tableView.columnModel = columnModel

            val tableView: TableView<Array<String>> =
                binding.tableView as TableView<Array<String>>
            tableView.dataAdapter =
                SimpleTableDataAdapter(requireContext(), it)
            tableView.headerAdapter =
                SimpleTableHeaderAdapter(
                    context,
                    "Provinsi",
                    "Tahun",
                    "Total"
                )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getPopulation() {
        lifecycleScope.launch(Dispatchers.Default) {
            val api = RetrofitServer().getInstance()
            api.getEmployees()
                .enqueue(object : Callback<PopulationResponse> {
                    override fun onResponse(
                        call: retrofit2.Call<PopulationResponse>,
                        response: Response<PopulationResponse>
                    ) {
                        if (response.isSuccessful) {
                            val tmpArray: MutableList<Array<String>> = arrayListOf()
                            for (i in response.body()?.data?.indices!!) {
                                val namaProvinsi = response.body()!!.data[i].nama_provinsi
                                val tahun = response.body()!!.data[i].tahun
                                val total = response.body()!!.data[i].total.toString()
                                val result: Array<String> = arrayOf(namaProvinsi, tahun, total)
                                tmpArray.add(result)
                            }
                            populationData.addAll(tmpArray)
                            val tableView: TableView<Array<String>> =
                                binding.tableView as TableView<Array<String>>
                            tableView.dataAdapter =
                                SimpleTableDataAdapter(requireContext(), populationData)
                            tableView.headerAdapter =
                                SimpleTableHeaderAdapter(
                                    requireContext(),
                                    "Provinsi",
                                    "Tahun",
                                    "Total"
                                )

                        } else Toast.makeText(
                            requireContext(),
                            response.errorBody()!!.toString(),
                            Toast.LENGTH_SHORT
                        ).show() // this will tell you why your api doesnt work most of time


                    }

                    override fun onFailure(call: retrofit2.Call<PopulationResponse>, t: Throwable) {
                        Log.e("tues", "onFailure: ${t.message}")
                    }

                })
        }

    }

}