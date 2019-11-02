package bonch.dev.school.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.Models.MessageLab
import bonch.dev.school.R

class message_recycler_items: RecyclerView.Adapter<message_recycler_items.MessageHolder>(){

    val messageList = MessageLab().messageList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): message_recycler_items.MessageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent,false)

        return message_recycler_items.MessageHolder(view)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
    }

    class MessageHolder(view: View): RecyclerView.ViewHolder(view)
}