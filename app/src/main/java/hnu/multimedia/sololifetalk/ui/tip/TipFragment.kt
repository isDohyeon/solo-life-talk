package hnu.multimedia.sololifetalk.ui.tip

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.sololifetalk.databinding.FragmentTipBinding

class TipFragment : Fragment() {

    private var _binding: FragmentTipBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTipBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.imageView0.setOnClickListener { startContentsListActivity(0) }
        binding.imageView1.setOnClickListener { startContentsListActivity(1) }
        binding.imageView2.setOnClickListener { startContentsListActivity(2) }
        binding.imageView3.setOnClickListener { startContentsListActivity(3) }
        binding.imageView4.setOnClickListener { startContentsListActivity(4) }
        binding.imageView5.setOnClickListener { startContentsListActivity(5) }
        binding.imageView6.setOnClickListener { startContentsListActivity(6) }
        binding.imageView7.setOnClickListener { startContentsListActivity(7) }

        return root
    }

    private fun startContentsListActivity(category: Int) {
        val intent = Intent(context, ContentsListActivity::class.java)
        intent.putExtra("category", category)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}