package com.satudata.data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.satudata.data.databinding.FragmentDataBinding
import de.codecrafters.tableview.TableView
import de.codecrafters.tableview.model.TableColumnWeightModel
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter


class DataFragment : Fragment() {

    private lateinit var data: DataViewModel
    private var _binding: FragmentDataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val DATA_TO_SHOW =
        arrayOf(arrayOf("This", "is", "a", "test"), arrayOf("and", "a", "second", "test"))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        data =
            ViewModelProvider(this).get(DataViewModel::class.java)

        _binding = FragmentDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tableView.columnCount = 4

        val columnModel = TableColumnWeightModel(5)
        columnModel.setColumnWeight(1, 2)
        columnModel.setColumnWeight(2, 3)

        binding.tableView.columnModel = columnModel
        val tableView: TableView<Array<String>> =
            binding.tableView as TableView<Array<String>>
        tableView.dataAdapter = SimpleTableDataAdapter(requireContext(), DATA_TO_SHOW)
        tableView.headerAdapter =
            SimpleTableHeaderAdapter(requireContext(), "This", "is", "a", "test")
//        tableView.setColumnComparator(0, CarProducerComparator())
        binding.tableView.setColumnComparator(1, comparatorString())
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class comparatorString : Comparator<String?>{
    override fun compare(o1: String?, o2: String?): Int {
        if(o1 == null || o2 == null){
            return 0;
        }
        return o1.compareTo(o2)
    }


}

//private class CarProducerComparator : Comparator<String?> {
//    override fun compare(car1: String, car2: String): Int {
//        return car1.getProducer().getName().compareTo(car2.getProducer().getName())
//    }
//}