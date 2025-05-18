package hnu.multimedia.sololifetalk.ui.talk

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hnu.multimedia.sololifetalk.R
import hnu.multimedia.sololifetalk.databinding.TalkItemBinding
import hnu.multimedia.sololifetalk.util.MyUtils.Companion.formatToString

class TalkAdapter(
    private val list: List<TalkModel>,
    private val keyList: List<String>
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
        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, TalkDetailActivity::class.java)
            intent.putExtra("key", keyList[position])
            holder.binding.root.context.startActivity(intent)
        }
        val writerUid = list[position].uid
        val loginUid = Firebase.auth.currentUser?.uid
        if (writerUid == loginUid) {
            holder.binding.root.setBackgroundResource(R.color.paleYellow)
        }
    }
}