package com.example.splitexpenses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splitexpenses.model.SplitViewModel


class TransactionAdapter(var viewm: SplitViewModel) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById(R.id.transaction_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.transactions_item, parent, false)
        return (TransactionViewHolder(layout))
    }

    override fun getItemCount(): Int {
        return viewm.allTransactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val stri = viewm.allTransactions[position]
        holder.text.text = stri
    }


}