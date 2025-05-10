package hnu.multimedia.sololifetalk.ui.talk

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.sololifetalk.databinding.TalkItemBinding
import hnu.multimedia.sololifetalk.util.MyUtils.Companion.formatToString

class TalkAdapter(
    private val list: List<TalkModel>
) : RecyclerView.Adapter<TalkAdapter.ViewHolder>() {

    class ViewHolder(val binding: TalkItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = TalkItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewTalkItemTitle.text = list[position].title
        holder.binding.textViewTalkItemContent.text = list[position].content
        holder.binding.textViewTalkItemDate.text = list[position].dateTime.formatToString()
    }
}