package bonch.dev.school.UI.fragments

import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.R
import bonch.dev.school.ui.message_recycler_items

class ChatFragmenst : Fragment() {

    private lateinit var MessageRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        MessageRecyclerView = view.findViewById(R.id.message_recycler_view)
        MessageRecyclerView.layoutManager = LinearLayoutManager(container!!.context)

        MessageRecyclerView.adapter = message_recycler_items()

        return view
    }
}