import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splitexpenses.R
import com.example.splitexpenses.model.SplitViewModel


class SummaryAdapter(private var viewm: SplitViewModel) :
    RecyclerView.Adapter<SummaryAdapter.summaryViewHolder>() {
    class summaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.name_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): summaryViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.name, parent, false)
        return summaryViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: summaryViewHolder, position: Int) {
        holder.textView.text = viewm.netTransactions.value!!.get(position)
    }

    override fun getItemCount() = viewm.netTransactions.value!!.size
}