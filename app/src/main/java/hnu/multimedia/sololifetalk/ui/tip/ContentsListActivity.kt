package hnu.multimedia.sololifetalk.ui.tip

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import hnu.multimedia.sololifetalk.R
import hnu.multimedia.sololifetalk.databinding.ActivityContentsListBinding

class ContentsListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityContentsListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val list = mutableListOf<ContentModel>()
        list.add(ContentModel("title1", "url1"))
        list.add(ContentModel("title1", "url1"))
        list.add(ContentModel("title1", "url1"))

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = ContentAdapter(list)
    }
}