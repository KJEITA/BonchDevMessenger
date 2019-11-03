package bonch.dev.school.Models

import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.Calendar.getInstance

data class Message(
    val messageId: Int,
    val messageText: String,
    val sentDate: Date,
    val isUser: Boolean
)

class MessageLab() {
    val messageList: MutableList<Message>

    init {
        messageList = mutableListOf()
        for (i in 0..99) {
            var b = true
            if (i % 2 == 0) b = false
            var message = Message(i, "Title #$i", Calendar.getInstance().time, b)
            messageList.add(message)
        }
    }
}