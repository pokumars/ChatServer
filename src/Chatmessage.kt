import java.time.LocalDateTime
//Oheneba Poku-Marboah 1706214

//defines all the messages entered in a format as chatmessages and
//Turns all chatmessages to string
class ChatMessage (val user:String, val message: String, val timeNow: LocalDateTime) {
    override fun toString(): String {
        return "$timeNow% $user# said $message "

    }
}