package bonch.dev.school.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.Models.MessageLab
import bonch.dev.school.UI.Message_recycler_items
import java.util.*


class ChatFragmenst : Fragment() {

    private lateinit var MessageRecyclerView: RecyclerView
    lateinit var messageRecyclerItems: Message_recycler_items

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(bonch.dev.school.R.layout.fragment_chat, container, false)

        MessageRecyclerView = view.findViewById(bonch.dev.school.R.id.message_recycler_view)

        val linearLayoutManager = LinearLayoutManager(container!!.context)
        linearLayoutManager.stackFromEnd = true
        MessageRecyclerView.layoutManager = linearLayoutManager

        messageRecyclerItems = Message_recycler_items()

        MessageRecyclerView.adapter = messageRecyclerItems

        val btn = view.findViewById<Button>(bonch.dev.school.R.id.send_message_button)
        btn.setOnClickListener {
            sendMessage(view)
        }
        return view
    }

    fun sendMessage(view: View) {

        val text_et = view.findViewById<TextView>(bonch.dev.school.R.id.message_et)

        if (!text_et.text.toString().contentEquals("")) {

            var mess = bonch.dev.school.Models.Message(
                102,
                text_et.text.toString(),
                Calendar.getInstance().time,
                true
            )
            text_et.text = ""
            messageRecyclerItems.refreshData(mess)

            MessageRecyclerView.adapter!!.notifyDataSetChanged()

            MessageRecyclerView.scrollToPosition(messageRecyclerItems.messageList.size - 1)
            //Toast.makeText(context, MessageLab().messageList.size.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}