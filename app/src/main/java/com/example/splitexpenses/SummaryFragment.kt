package com.example.splitexpenses

import SummaryAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitexpenses.databinding.FragmentSummaryBinding
import com.example.splitexpenses.model.SplitViewModel

class SummaryFragment : Fragment() {
    private lateinit var binding: FragmentSummaryBinding
    private val MyViewModel: SplitViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, true) {
            findNavController().navigate(R.id.action_summaryFragment_to_startPageFragment2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MyViewModel.updateTransactions()
        binding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding.listTransactions.layoutManager = LinearLayoutManager(requireActivity())
        binding.listTransactions.adapter = SummaryAdapter(MyViewModel)
        return binding.root
    }


}