package com.satudata.data

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

    //default category and year
    private var year = "2014"
    private var category = "Populasi"
    private var golputRekapCategory = "Pemilihan Legislatif"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val populationData: ArrayList<Array<String>> = arrayListOf()

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

        //adapter for spinner category
        val adapterYear = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            resources.getStringArray(com.satudata.resources.R.array.drop_down_year_list)
        )
        binding.spinnerDataYear.adapter = adapterYear
        binding.spinnerDataYear.onItemSelectedListener =
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
                            when (category) {
                                "Populasi" -> {
                                    observeDataPopulation(year)
                                }

                                "DPT" -> {
                                    observeDataDPT(year)
                                }

                                "Rekap" -> {
                                    observeDataRekapitulasi(year, golputRekapCategory)
                                }

                                "Golput" -> {
                                    observeDataGolput(year, golputRekapCategory)
                                }
                            }
                        }
                        1 -> {
                            year = "2019"
                            when (category) {
                                "Populasi" -> {
                                    observeDataPopulation(year)
                                }

                                "DPT" -> {
                                    observeDataDPT(year)
                                }

                                "Rekap" -> {
                                    observeDataRekapitulasi(year, golputRekapCategory)
                                }

                                "Golput" -> {
                                    observeDataGolput(year, golputRekapCategory)
                                }
                            }
                        }
                    }


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

        //adapter for spinner category
        val adapterCategory = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            resources.getStringArray(com.satudata.resources.R.array.drop_down_data_list)
        )
        binding.spinnerDataCategory.adapter = adapterCategory
        binding.spinnerDataCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            observeDataPopulation(year)
                            category = "Populasi"
                            binding.tvTitleTableData.text = "Data Populasi"
                            binding.spinnerDataGolputRekapCategory.visibility = View.GONE
                        }
                        1 -> {
                            observeDataDPT(year)
                            category = "DPT"
                            binding.tvTitleTableData.text = "Data Daftar Pemilih Tetap (DPT)"
                            binding.spinnerDataGolputRekapCategory.visibility = View.GONE
                        }
                        2 -> {
                            observeDataRekapitulasi(year, golputRekapCategory)
                            category = "Rekap"
                            binding.tvTitleTableData.text = "Data Rekapitulasi"
                            binding.spinnerDataGolputRekapCategory.visibility = View.VISIBLE
                        }
                        3 -> {
                            observeDataGolput(year, golputRekapCategory)
                            category = "Golput"
                            binding.tvTitleTableData.text = "Data Golongan Putih (Golput)"
                            binding.spinnerDataGolputRekapCategory.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

        //adapter for spinner category
        val adapterGolputRekapCategory = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            resources.getStringArray(com.satudata.resources.R.array.drop_down_golput_category)
        )
        binding.spinnerDataGolputRekapCategory.adapter = adapterGolputRekapCategory
        binding.spinnerDataGolputRekapCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                            golputRekapCategory = "Pemilihan Legislatif"
                            observeDataGolput(year, golputRekapCategory)
                            binding.spinnerDataGolputRekapCategory.visibility = View.VISIBLE
                        }
                        1 -> {
                            golputRekapCategory = "Pemilihan Presiden"
                            observeDataGolput(year, golputRekapCategory)
                            binding.spinnerDataGolputRekapCategory.visibility = View.VISIBLE
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

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

        observeDataPopulation(year)
        return root
    }

    private fun observeDataPopulation(year: String) {
        viewModel.getPopulation(year).observe(viewLifecycleOwner) {
            val columnModel = TableColumnDpWidthModel(context, 2, 200)
            columnModel.setColumnWidth(0, 250)
//            columnModel.setColumnWidth(1, 100)
            columnModel.setColumnWidth(1, 200)
            binding.tableView.columnModel = columnModel

            val tableView: TableView<Array<String>> =
                binding.tableView as TableView<Array<String>>
            tableView.dataAdapter =
                SimpleTableDataAdapter(requireContext(), it)
            tableView.headerAdapter =
                SimpleTableHeaderAdapter(
                    context,
                    "Provinsi",
//                    "Tahun",
                    "Total"
                )
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun observeDataRekapitulasi(year: String, category: String) {
        viewModel.getRekapitulasi(year, category).observe(viewLifecycleOwner) {
            val columnModel = TableColumnDpWidthModel(context, 2, 200)
            columnModel.setColumnWidth(0, 250)
//            columnModel.setColumnWidth(1, 200)
//            columnModel.setColumnWidth(2, 100)
            columnModel.setColumnWidth(1, 200)
            binding.tableView.columnModel = columnModel

            val tableView: TableView<Array<String>> =
                binding.tableView as TableView<Array<String>>
            tableView.dataAdapter =
                SimpleTableDataAdapter(requireContext(), it)
            tableView.headerAdapter =
                SimpleTableHeaderAdapter(
                    context,
                    "Provinsi",
//                    "Nama Pemilu",
//                    "Tahun",
                    "Total"
                )
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun observeDataGolput(year: String, category: String) {
        viewModel.getGolput(year, category).observe(viewLifecycleOwner) {
            val columnModel = TableColumnDpWidthModel(context, 2, 200)
            columnModel.setColumnWidth(0, 250)
//            columnModel.setColumnWidth(1, 200)
//            columnModel.setColumnWidth(2, 100)
            columnModel.setColumnWidth(1, 200)
            binding.tableView.columnModel = columnModel

            val tableView: TableView<Array<String>> =
                binding.tableView as TableView<Array<String>>
            tableView.dataAdapter =
                SimpleTableDataAdapter(requireContext(), it)
            tableView.headerAdapter =
                SimpleTableHeaderAdapter(
                    context,
                    "Provinsi",
//                    "Nama Pemilu",
//                    "Tahun",
                    "Total Golput"
                )
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun observeDataDPT(year: String) {
        viewModel.getDPT(year).observe(viewLifecycleOwner) {
            val columnModel = TableColumnDpWidthModel(context, 2, 200)
            columnModel.setColumnWidth(0, 250)
//            columnModel.setColumnWidth(1, 100)
            columnModel.setColumnWidth(1, 200)
            binding.tableView.columnModel = columnModel

            val tableView: TableView<Array<String>> =
                binding.tableView as TableView<Array<String>>
            tableView.dataAdapter =
                SimpleTableDataAdapter(requireContext(), it)
            tableView.headerAdapter =
                SimpleTableHeaderAdapter(
                    context,
                    "Provinsi",
//                    "Tahun",
                    "Total"
                )
            binding.progressBar.visibility = View.GONE
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