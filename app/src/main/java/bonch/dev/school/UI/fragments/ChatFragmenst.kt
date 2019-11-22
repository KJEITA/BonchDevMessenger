package bonch.dev.school.UI.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bonch.dev.school.UI.Message_recycler_items
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*


class ChatFragmenst : Fragment() {

    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    private lateinit var MessageRecyclerView: RecyclerView
    lateinit var messageRecyclerItems: Message_recycler_items

    var actualMessageId = 1

    lateinit var items: MutableList<bonch.dev.school.Models.Message>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(bonch.dev.school.R.layout.fragment_chat, container, false)

        mDataBase = FirebaseDatabase.getInstance()
        mReference = mDataBase.reference.child("Messages")
        mAuth = FirebaseAuth.getInstance()

        //actualMessageId = mReference.database.

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

        mReference.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    items = mutableListOf()
                    actualMessageId = (p0.child("act").child("count").value as Long).toInt()
                    var i = 1
                    while (i <= actualMessageId) {


                        var mess = bonch.dev.school.Models.Message(
                            i,
                            p0.child(i.toString()).child("message").value as String,
                            p0.child(i.toString()).child("Date").value as String,
                            p0.child(i.toString()).child("isUser").value as Boolean
                        )
                        items.add(mess)
                        //messageRecyclerItems.refreshData(mess)

                        i++
                    }
                    messageRecyclerItems.updateData(items)
                    MessageRecyclerView.adapter!!.notifyDataSetChanged()
                    //items = messageRecyclerItems.messageList
                }
            })
        return view
    }

    fun sendMessage(view: View) {

        val text_et = view.findViewById<TextView>(bonch.dev.school.R.id.message_et)

        if (!text_et.text.toString().contentEquals("")) {
            actualMessageId++

            //messageRecyclerItems.refreshData(mess)
            //MessageRecyclerView.adapter!!.notifyDataSetChanged()
            MessageRecyclerView.scrollToPosition(messageRecyclerItems.messageList.size - 1)
            //items = messageRecyclerItems.messageList

            val currentUserDb = mReference.child(actualMessageId.toString())
            currentUserDb.child("isUser").setValue(true)
            currentUserDb.child("message").setValue(text_et.text.toString())

            val formatDate = SimpleDateFormat("hh:mm")
            currentUserDb.child("Date").setValue(formatDate.format(Date()))


            val actCountMessage = mReference.child("act")
            actCountMessage.child("count").setValue(actualMessageId)

            text_et.text = ""
        }
    }


    /*override fun onPause() {
        super.onPause()

        mBundleRecyclerViewState = Bundle()
        mBundleRecyclerViewState?.putParcelableArrayList("CHATS", ArrayList<Parcelable>(items))
    }*/

    /*override fun onResume() {
        super.onResume()

        if (mBundleRecyclerViewState != null) {
            items =
                mBundleRecyclerViewState?.getParcelableArrayList<bonch.dev.school.Models.Message>("CHATS")!!
            messageRecyclerItems.notifyItemInserted(items.size - 1)
            messageRecyclerItems.updateData(items.toMutableList())
            MessageRecyclerView.adapter!!.notifyDataSetChanged()
            message_recycler_view.scrollToPosition(items.size - 1)
        }
    }

    companion object {

        private var mBundleRecyclerViewState: Bundle? = null

        @JvmStatic
        fun newInstance() = ChatFragmenst
    }*/
}