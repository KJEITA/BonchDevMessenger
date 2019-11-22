package bonch.dev.school.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Message(
    val messageId: Int,
    val messageText: String,
    val sentDate: String,
    val isUser: Boolean
) : Parcelable {

    class MessageLab() {
        val messageList: MutableList<Message>

        init {

            messageList = mutableListOf()
                /*var b = true

                var message = Message(0, "Title #$0", Calendar.getInstance().time, b)
                messageList.add(message)*/

        }
    }
}