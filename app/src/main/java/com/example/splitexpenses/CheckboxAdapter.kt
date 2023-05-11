package com.example.splitexpenses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.splitexpenses.model.SplitViewModel

class CheckboxAdapter(private var viewm: SplitViewModel) :
    RecyclerView.Adapter<CheckboxAdapter.CheckboxViewHolder>() {
    class CheckboxViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val CheckBox: CheckBox = view.findViewById(R.id.checkbox_item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckboxViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.checkbox_item, parent, false)
        return CheckboxViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return viewm.member_name.value!!.size
    }

    override fun onBindViewHolder(holder: CheckboxViewHolder, position: Int) {
        var count = 0
        for (i in viewm.member_name.value!!.keys) {

            if (count == position) {
                holder.CheckBox.setOnClickListener {
                    if (!holder.CheckBox.isChecked) {
                        viewm._owedBy.value!!.remove(holder.CheckBox.text.toString())
                    } else {
                        viewm._owedBy.value!!.add(holder.CheckBox.text.toString())
                    }
                }
                holder.CheckBox.text = i
                break
            }
            count++
        }
    }
}