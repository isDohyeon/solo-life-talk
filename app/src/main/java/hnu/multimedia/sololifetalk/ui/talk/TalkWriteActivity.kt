package hnu.multimedia.sololifetalk.ui.talk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hnu.multimedia.sololifetalk.databinding.ActivityTalkWriteBinding
import hnu.multimedia.sololifetalk.util.FirebaseRef
import hnu.multimedia.sololifetalk.util.MyUtils

class TalkWriteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityTalkWriteBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonWrite.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val content = binding.editTextContent.text.toString()
            val uid = Firebase.auth.currentUser?.uid.toString()
            FirebaseRef.talks.push().setValue(TalkModel(title, content, uid, MyUtils.getCurrentDateTime()))
            finish()
        }
    }
}