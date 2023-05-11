package com.example.splitexpenses.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplitViewModel : ViewModel() {
    val allTransactions = mutableListOf<String>()
    private val _member_name = MutableLiveData(linkedMapOf("" to 0.0))
    val member_name: LiveData<LinkedHashMap<String, Double>> = _member_name
    val _owedBy = MutableLiveData(mutableListOf(""))
    var owedBy: LiveData<MutableList<String>> = _owedBy
    var first = true
    var netTransactions = MutableLiveData(mutableListOf(""))
    fun addMember(name: String) {
        if (first) {
            _member_name.value!!.clear()
            first = false
        }
        _member_name.value!![name] = 0.0
    }

    fun spendBy(amount: String, by: String) {
        if (owedBy.value!!.size == 1 || by == "" || amount == "") {
            return
        }
        val div = ((amount.toDouble()) * 1.0) / (owedBy.value!!.size - 1)
        _member_name.value!![by] = _member_name.value!![by]!! + amount.toDouble()
        for (name in owedBy.value!!) {
            if (name == "") {
                continue
            }
            _member_name.value!![name] = _member_name.value!![name]!! - div
        }
        allTransactions.add("$by spends ${amount.toInt()} owed by ${owedBy.value!!.joinToString(" ")}")
    }

    fun minCashFlowRec(amount: MutableList<Double>, names: List<String>) {

        val mxCredit = amount.indexOf(amount.toList().max())
        val mxDebit = amount.indexOf(amount.toList().min())
        if ((amount[mxCredit] < 1.0) && (amount[mxDebit] > -1.0))
            return

        val min = minOf(-amount[mxDebit], amount[mxCredit])
        amount[mxCredit] -= min
        amount[mxDebit] += min
        netTransactions.value!!.add("${names[mxDebit]} pays ${min.toInt()} to ${names[mxCredit]} ")

        minCashFlowRec(amount, names)
    }

    fun inc(huh: Int) {
        var flag = true
        for (i in _member_name.value!!.keys) {
            if (flag) {
                flag = false
                continue
            }
            _member_name.value!![i] = _member_name.value!![i]!! + huh
        }
    }

    fun updateTransactions() {
        val amount = mutableListOf<Double>()
        val names = mutableListOf<String>()

        for ((key, value) in _member_name.value!!) {
            names.add(key)
            amount.add(value)
        }

        minCashFlowRec(amount, names)
    }

}


