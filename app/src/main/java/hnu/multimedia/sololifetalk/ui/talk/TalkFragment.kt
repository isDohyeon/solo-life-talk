package hnu.multimedia.sololifetalk.ui.talk

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.sololifetalk.databinding.FragmentTalkBinding

class TalkFragment : Fragment() {

    private val binding by lazy { FragmentTalkBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.imageViewWrite.setOnClickListener {
            val intent = Intent(context, TalkWriteActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}