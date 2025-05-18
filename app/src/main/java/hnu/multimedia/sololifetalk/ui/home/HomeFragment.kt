package hnu.multimedia.sololifetalk.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hnu.multimedia.sololifetalk.auth.IntroActivity
import hnu.multimedia.sololifetalk.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val email = Firebase.auth.currentUser?.email ?: ""
        val greeting = "안녕하세요! ${email} 님"
        binding.textViewHelloMessage.text = greeting

        binding.buttonLogout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(context, IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        return binding.root
    }
}