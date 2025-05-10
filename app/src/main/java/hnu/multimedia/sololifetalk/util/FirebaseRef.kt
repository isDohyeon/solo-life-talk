package hnu.multimedia.sololifetalk.util

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object {
        private val database = Firebase.database
        val contents = database.getReference("contents")
        val bookmarks = database.getReference("bookmarks")
        val talk = database.getReference("talks")
    }
}