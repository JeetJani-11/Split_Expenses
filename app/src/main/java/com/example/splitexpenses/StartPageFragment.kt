package com.example.splitexpenses


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splitexpenses.databinding.FragmentStartPageBinding
import com.example.splitexpenses.model.SplitViewModel
import memberNameAdapter

class StartPageFragment : Fragment() {
    private lateinit var binding: FragmentStartPageBinding
    private val MyViewModel: SplitViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartPageBinding.inflate(inflater, container, false)

        val recyc = binding.listNames
        recyc.layoutManager = LinearLayoutManager(requireActivity())
        recyc.adapter = memberNameAdapter(MyViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.nameSubmit.setOnClickListener {

            if (binding.enterNameSubmitText.text.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), "No members added", Toast.LENGTH_SHORT).show()
            } else if (MyViewModel.member_name.value != null) {

                if (MyViewModel.member_name.value!!.containsKey(binding.enterNameSubmitText.text.toString())) {
                    Toast.makeText(
                        requireContext(),
                        "Member name already added",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {

                    MyViewModel.addMember(binding.enterNameSubmitText.text.toString())
                    binding.listNames.adapter?.notifyDataSetChanged()
                }
            }
            binding.enterNameSubmitText.text?.clear()
        }
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_startPageFragment2_to_transactionsFragment2)
        }

    }
}
