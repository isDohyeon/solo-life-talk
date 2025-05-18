package hnu.multimedia.sololifetalk.ui.talk

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import hnu.multimedia.sololifetalk.databinding.ActivityTalkWriteBinding
import hnu.multimedia.sololifetalk.util.FirebaseRef
import hnu.multimedia.sololifetalk.util.MyUtils

class TalkEditActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTalkWriteBinding.inflate(layoutInflater) }
    private var oldUri = Uri.EMPTY
    private var newUri = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textViewTop.text = "자취톡 수정"
        binding.buttonWrite.text = "수정"

        val key = intent.getStringExtra("key") ?: ""
        getTalkModel(key)
        getPhoto(key)

        binding.buttonWrite.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()
            val uid = Firebase.auth.currentUser?.uid.toString()
            FirebaseRef.talks.child(key).setValue(
                TalkModel(title, content, uid, MyUtils.getCurrentDateTime()))
            if (oldUri != newUri) {
                Firebase.storage.reference.child("${key}.jpg").putFile(newUri)
            }
            finish()
        }


        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            _uri ->
            _uri.let {
                newUri = _uri
                binding.imageViewPhoto.setImageURI(_uri)
            }
        }
        binding.imageViewPhoto.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun getTalkModel(key: String) {
        val ref = FirebaseRef.talks.child(key)
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue(TalkModel::class.java)
                binding.editTextTitle.setText(value?.title)
                binding.editTextContent.setText(value?.content)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TalkEditActivity", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }

    private fun getPhoto(key: String) {
        val imageReference = Firebase.storage.reference.child("$key.jpg")
        imageReference.downloadUrl.addOnSuccessListener {
                uri -> Glide.with(this).load(uri).into(binding.imageViewPhoto)
            oldUri = uri
        }
    }
}