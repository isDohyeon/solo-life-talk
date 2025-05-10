package hnu.multimedia.sololifetalk.ui.talk

import hnu.multimedia.sololifetalk.util.MyUtils
import java.util.Date

data class TalkModel(
    var title: String = "",
    var content: String = "",
    var uid: String = "",
    var dateTime: Date = MyUtils.getCurrentDateTime()
)
