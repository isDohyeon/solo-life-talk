package hnu.multimedia.sololifetalk.ui.talk

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.sololifetalk.databinding.ActivityTalkDetailBinding
import hnu.multimedia.sololifetalk.util.FirebaseRef
import hnu.multimedia.sololifetalk.util.MyUtils.Companion.formatToString

class TalkDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTalkDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val key = intent.getStringExtra("key")
        key?.let { getTalkModel(key.toString()) }
        binding.textViewTitle.text = key
    }

    private fun getTalkModel(key: String) {
        val ref = FirebaseRef.talks.child(key)
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(TalkModel::class.java)
                binding.textViewTitle.text = value?.title
                binding.textViewDate.text = value?.dateTime?.formatToString()
                binding.textViewContent.text = value?.content
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TalkDetailActivity", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }
}