package hnu.multimedia.sololifetalk.ui.talk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import hnu.multimedia.sololifetalk.databinding.ActivityTalkDetailBinding
import hnu.multimedia.sololifetalk.ui.talk.comments.CommentAdapter
import hnu.multimedia.sololifetalk.ui.talk.comments.CommentModel
import hnu.multimedia.sololifetalk.util.FirebaseRef
import hnu.multimedia.sololifetalk.util.MyUtils.Companion.formatToString

class TalkDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTalkDetailBinding.inflate(layoutInflater) }
    private var isPhotoAvailable = false
    private val comments = mutableListOf<CommentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val key = intent.getStringExtra("key")
        key?.let {
            getTalkModel(key)
            getPhoto(key)
            getComments(key)
            removePost(key)
            editPost(key)
            writeComments(key)
        }
        binding.recyclerViewComments.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewComments.adapter = CommentAdapter(comments)
    }

    private fun getComments(key: String) {
        val ref = FirebaseRef.comments.child(key)
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                comments.clear()
                for (dataModel in snapshot.children) {
                    val value = dataModel.getValue(CommentModel::class.java)
                    value?.let { comments.add(value) }
                }
                comments.reverse()
                binding.recyclerViewComments.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TalkDetailActivity", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }

    private fun writeComments(key: String) {
        binding.buttonComments.setOnClickListener {
            val email = Firebase.auth.currentUser?.email ?: ""
            val comment = binding.editTextComments.text.toString()
            FirebaseRef.comments.child(key).push().setValue(CommentModel(email, comment))
            binding.editTextComments.setText("")
        }
    }

    private fun editPost(key: String) {
        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, TalkEditActivity::class.java)
            intent.putExtra("key", key)
            startActivity(intent)
            finish()
        }
    }

    private fun removePost(key: String) {
        binding.buttonDelete.setOnClickListener {
            FirebaseRef.talks.child(key).removeValue()
            if (isPhotoAvailable) {
                Firebase.storage.reference.child("$key.jpg").delete()
            }
            FirebaseRef.comments.child(key).removeValue()
            finish()
        }
    }

    private fun getTalkModel(key: String) {
        val ref = FirebaseRef.talks.child(key)
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(TalkModel::class.java)
                binding.textViewTitle.text = value?.title
                binding.textViewDate.text = value?.dateTime?.formatToString()
                binding.textViewContent.text = value?.content
                val writerUid = value?.uid.toString()
                val loginUid = Firebase.auth.currentUser?.uid.toString()
                if (writerUid != loginUid) {
                    binding.buttonEdit.isVisible = false
                    binding.buttonDelete.isVisible = false
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TalkDetailActivity", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }

    private fun getPhoto(key: String) {
        val imageReference = Firebase.storage.reference.child("$key.jpg")
        imageReference.downloadUrl.addOnSuccessListener {
            uri -> Glide.with(this).load(uri).into(binding.imageViewPhoto)
            isPhotoAvailable = true
        }
    }
}