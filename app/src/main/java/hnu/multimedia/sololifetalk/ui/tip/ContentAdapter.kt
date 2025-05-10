package hnu.multimedia.sololifetalk.ui.tip

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import hnu.multimedia.sololifetalk.R
import hnu.multimedia.sololifetalk.databinding.ContentItemBinding

class ContentAdapter(
    private val list: List<ContentModel>,
    private val keyList: List<String>,
    private val bookmarks: MutableList<String>
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    private lateinit var database : FirebaseDatabase

    class ViewHolder(val binding: ContentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ContentItemBinding.inflate(inflater, parent, false)
        database = Firebase.database
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewTitle.text = list[position].title
        Glide.with(holder.binding.root.context)
            .load(list[position].imageURL)
            .into(holder.binding.imageViewPicture)

        holder.binding.root.setOnClickListener {
            val intent = Intent(holder.binding.root.context, ContentShowActivity::class.java)
            intent.putExtra("url", list[position].webURL)
            holder.binding.root.context.startActivity(intent, null)
        }

        holder.binding.imageViewBookmark.setOnClickListener {
            val ref = database.getReference("bookmarks")
            val loginUid = Firebase.auth.currentUser?.uid.toString()
            val docUid = keyList[position]
            ref.child(loginUid).child(docUid).setValue(true)
        }

        if (bookmarks.contains(keyList[position])) {
            holder.binding.imageViewBookmark.setImageResource(R.drawable.bookmark_color)
        } else {
            holder.binding.imageViewBookmark.setImageResource(R.drawable.bookmark_white)
        }
    }
}