package hnu.multimedia.sololifetalk.ui.talk

import java.util.Date

data class TalkModel(
    val title: String,
    val content: String,
    val uid: String,
    val dateTime: Date
)
