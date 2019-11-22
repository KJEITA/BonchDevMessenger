package bonch.dev.school.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.Models.Message
import bonch.dev.school.R

class Message_recycler_items : RecyclerView.Adapter<Message_recycler_items.MessageHolder>() {

    public val messageLab = Message.MessageLab()

    var messageList = messageLab.messageList


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageHolder {
        var view: View

        //messageList = mutableListOf()

        if (viewType == 1)
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.other_message_item, parent, false)
        else
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_message_item, parent, false)

        return MessageHolder(view)
    }

    public fun refreshData(mess: Message) {
        messageList.add(mess)
    }

    public fun updateData(list: MutableList<Message>) {
        messageList = list
    }

    override fun getItemViewType(position: Int): Int {
        if (messageList[position].isUser)
            return 0
        else
            return 1
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(position)
    }

    inner class MessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            val idTextView = itemView.findViewById<TextView>(R.id.text_message)
            val timeTextView = itemView.findViewById<TextView>(R.id.time_message)
            val t = messageList[position].messageText
            idTextView.text = t
            timeTextView.text = messageList[position].sentDate
        }
    }
}