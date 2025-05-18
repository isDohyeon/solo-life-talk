package hnu.multimedia.sololifetalk.ui.talk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.sololifetalk.databinding.FragmentTalkBinding
import hnu.multimedia.sololifetalk.util.FirebaseRef

class TalkFragment : Fragment() {

    private val list = mutableListOf<TalkModel>()
    private val keyList = mutableListOf<String>()
    private val binding by lazy { FragmentTalkBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getTalks()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = TalkAdapter(list, keyList)

        binding.imageViewWrite.setOnClickListener {
            val intent = Intent(context, TalkWriteActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    private fun getTalks() {
        val ref = FirebaseRef.talks
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                keyList.clear()
                for (dataModel in snapshot.children) {
                    val value = dataModel.getValue(TalkModel::class.java)
                    value?.let { list.add(value) }
                    keyList.add(dataModel.key.toString())
                }
                list.reverse()
                keyList.reverse()
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
            override fun onCancelled(error: DatabaseError) {
                Log.w("TalkFragment", "onCancelled: $error")
            }
        }
        ref.addValueEventListener(postListener)
    }
}