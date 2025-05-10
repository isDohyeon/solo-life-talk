package hnu.multimedia.sololifetalk.ui.tip

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import hnu.multimedia.sololifetalk.R
import hnu.multimedia.sololifetalk.databinding.ActivityContentsListBinding

class ContentsListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityContentsListBinding.inflate(layoutInflater) }
    private val bookmarks = mutableListOf<String>()
    private lateinit var database : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val database = Firebase.database
        val myRef = database.getReference("contents")
        // firebase의 realtime database에 데이터 넣기
//        myRef.push().setValue(ContentModel(
//            "도시락에 빠질 수 없는 ✿유부초밥✿ 레시피",
//            "https://recipe1.ezmember.co.kr/cache/recipe/2022/09/29/87411c51ac208e2d37e7f28c29b43e501.jpg",
//            "https://www.10000recipe.com/recipe/6988334"
//        ))
//        myRef.push().setValue(ContentModel(
//            "하루의 마무리는 역시 야식이지 ｏｏ야식 추천메뉴8가지",
//            "https://recipe1.ezmember.co.kr/cache/recipe/2022/09/23/ac74dbf3eb77097a1442492efa0d275c1.jpg",
//            "https://www.10000recipe.com/recipe/6987988"
//        ))
//        myRef.push().setValue(ContentModel(
//            "감자 1박스 뽀개기 가능한 7가지 ☆감자요리☆",
//            "https://recipe1.ezmember.co.kr/cache/recipe/2022/09/23/484ba19948fd9d8bec99c5f8ddc9ecc61.jpg",
//            "https://www.10000recipe.com/recipe/6987503"
//        ))
//        myRef.push().setValue(ContentModel(
//            "전은 다맛있지! 추석을 더 특별하게 즐기는 10가지 레시피",
//            "https://recipe1.ezmember.co.kr/cache/recipe/2022/07/20/e0b923d7f82b3c5b71ee3eb84acb8da01_s.jpg",
//            "https://www.10000recipe.com/recipe/6987032"
//        ))
//        myRef.push().setValue(ContentModel(
//            "'우유'가 들어가 더 부드럽고 고소한 우유 활용 레시피",
//            "https://recipe1.ezmember.co.kr/cache/recipe/2022/08/08/478325d9601d2e6d0ff65d3dd5b6f1521_s.jpg",
//            "https://www.10000recipe.com/recipe/6986287"
//        ))
//        myRef.push().setValue(ContentModel(
//            "WoW!! 모두에게 박수받을 10가지 캠핑요리",
//            "https://recipe1.ezmember.co.kr/cache/recipe/2022/08/10/e1e95e13aff4d92cd93439d910c81c3e1_s.jpg",
//            "https://www.10000recipe.com/recipe/6985914"
//        ))
//        myRef.push().setValue(ContentModel(
//            "❁톡톡톡, 캔옥수수의대변신! 옥수수요리모음집❁",
//            "https://recipe1.ezmember.co.kr/cache/recipe/2022/07/21/538dafbae7aa3eb41ed3e970f08379231_s.jpg",
//            "https://www.10000recipe.com/recipe/6985381"
//        ))

        val list = mutableListOf<ContentModel>()
        val keyList = mutableListOf<String>()

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                keyList.clear()
                for (dataModel in snapshot.children) {
                    val value = dataModel.getValue(ContentModel::class.java)
                    value?.let { list.add(value) }
                    keyList.add(dataModel.key.toString())
                }
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("ContentsListActivity", "onCancelled: $error")
            }
        }

        myRef.addValueEventListener(postListener)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = ContentAdapter(list, keyList, bookmarks)

        getBookmarks()
    }

    private fun getBookmarks() {
        database = Firebase.database
        val ref = database.getReference("bookmarks").child(Firebase.auth.currentUser?.uid.toString())
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                bookmarks.clear()
                for (dataModel in snapshot.children) {
                    bookmarks.add(dataModel.key.toString())
                }
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("ContentsListActivity", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }
}