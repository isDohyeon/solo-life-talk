package hnu.multimedia.sololifetalk.ui.bookmarks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import hnu.multimedia.sololifetalk.databinding.FragmentBookmarkBinding
import hnu.multimedia.sololifetalk.ui.tip.ContentAdapter
import hnu.multimedia.sololifetalk.ui.tip.ContentModel
import hnu.multimedia.sololifetalk.util.FirebaseRef

class BookmarkFragment : Fragment() {

    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }
    private val bookmarks = mutableListOf<String>()
    private val list = mutableListOf<ContentModel>()
    private val keyList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getBookmarks()
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = ContentAdapter(list, keyList, bookmarks)
        return binding.root
    }

    private fun getBookmarks() {
        val ref = FirebaseRef.bookmarks.child(Firebase.auth.currentUser?.uid.toString())
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarks.clear()
                for (dataModel in snapshot.children) {
                    bookmarks.add(dataModel.key.toString())
                }
                getContents()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("ContentsListActivity", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }

    private fun getContents() {
        val ref = FirebaseRef.contents
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                keyList.clear()
                for (dataModel in snapshot.children) {
                    if (bookmarks.contains(dataModel.key.toString())) {
                        val value = dataModel.getValue(ContentModel::class.java)
                        value?.let { list.add(value) }
                        keyList.add(dataModel.key.toString())
                    }
                }
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("ContentsListActivity", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }
}