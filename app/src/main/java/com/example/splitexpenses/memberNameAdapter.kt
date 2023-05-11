import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.splitexpenses.R
import com.example.splitexpenses.model.SplitViewModel


class memberNameAdapter(private var viewm: SplitViewModel) :
    RecyclerView.Adapter<memberNameAdapter.memberNameViewHolder>() {
    class memberNameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.name_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): memberNameViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.name, parent, false)
        return memberNameViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: memberNameViewHolder, position: Int) {
        var count = 0
        for (i in viewm.member_name.value!!.keys) {
            if (count == position) {
                holder.textView.text = i
                break
            }
            count++
        }


    }

    override fun getItemCount() = viewm.member_name.value!!.size
}