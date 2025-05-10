package hnu.multimedia.sololifetalk.ui.tip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.sololifetalk.databinding.ContentItemBinding

class ContentAdapter(private val list: List<ContentModel>) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ContentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ContentItemBinding.inflate(inflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewTitle.text = list[position].title
    }

}