package hnu.multimedia.sololifetalk.ui.talk

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import hnu.multimedia.sololifetalk.databinding.ActivityTalkWriteBinding
import hnu.multimedia.sololifetalk.util.FirebaseRef
import hnu.multimedia.sololifetalk.util.MyUtils

class TalkWriteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTalkWriteBinding.inflate(layoutInflater) }
    private var uri = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonWrite.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()
            val uid = Firebase.auth.currentUser?.uid.toString()
            val pushRef = FirebaseRef.talks.push()
            FirebaseRef.talks.push()
                .setValue(TalkModel(title, content, uid, MyUtils.getCurrentDateTime()))
            Firebase.storage.reference.child("${pushRef.key.toString()}.jpg").putFile(uri)
            finish()
        }

        val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            _uri ->
            _uri.let {
                uri = _uri
                binding.imageViewPhoto.setImageURI(uri)
            }
        }

        binding.imageViewPhoto.setOnClickListener {
            launcher.launch("image/*")
        }
    }
}