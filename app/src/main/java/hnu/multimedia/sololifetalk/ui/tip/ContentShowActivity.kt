package hnu.multimedia.sololifetalk.ui.tip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.sololifetalk.databinding.ActivityContentShowBinding

class ContentShowActivity : AppCompatActivity() {

    private val binding by lazy { ActivityContentShowBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val url = intent.getStringExtra("url")
        binding.webView.loadUrl(url.toString())
    }
}