package com.example.splitexpenses

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitexpenses.databinding.FragmentTransactionsBinding
import com.example.splitexpenses.model.SplitViewModel
import com.google.android.material.textfield.MaterialAutoCompleteTextView


class TransactionsFragment : Fragment() {

    private val myViewModel: SplitViewModel by activityViewModels()
    private lateinit var binding: FragmentTransactionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        val checkrecycle = binding.listNames
        val transrecycle = binding.listTransactions
        checkrecycle.layoutManager = LinearLayoutManager(requireContext())
        transrecycle.layoutManager = LinearLayoutManager(requireContext())
        checkrecycle.adapter = CheckboxAdapter(myViewModel)
        transrecycle.adapter = TransactionAdapter(myViewModel)
        return binding.root
    }

    private var current_selected: String = ""

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val items = myViewModel.member_name.value!!.keys.toList()

        val adapter = ArrayAdapter(requireContext(), R.layout.name, items)

        (binding.menu.editText as? MaterialAutoCompleteTextView)?.setAdapter(adapter)
        binding.addTransaction.setOnClickListener {
            add_transaction()
        }
        binding.AmountEditText.setOnKeyListener { view, keycode, _ ->
            handleKeyEvent(
                view,
                keycode
            )
        }
        binding.DropdownMenu.setOnItemClickListener { parent, view, position, id ->
            current_selected = parent.getItemAtPosition(position).toString()
        }
        binding.viewSummary.setOnClickListener {
            findNavController().navigate(R.id.action_transactionsFragment2_to_summaryFragment)
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    private fun add_transaction() {
        val amt = binding.AmountEditText.editableText.toString()
        myViewModel.spendBy(amt, current_selected)

        binding.listTransactions.adapter?.notifyDataSetChanged()
        binding.DropdownMenu.listSelection = 0
    }

}