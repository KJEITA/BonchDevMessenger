package bonch.dev.school.UI.fragments

import android.os.Bundle
import android.text.Editable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import bonch.dev.school.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PasswordFragment : DialogFragment() {
    private lateinit var button_ok: Button
    private lateinit var button_cancel: Button

    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_password, container, false)
        val oldPass = v.findViewById<TextView>(R.id.old_password_edit_text)
        val newOnePass = v.findViewById<TextView>(R.id.old_password_edit_text)
        val newTwoPass = v.findViewById<TextView>(R.id.old_password_edit_text)
        val btn_change = v.findViewById<Button>(R.id.change_pas_button)

        mDataBase = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        mReference = mDataBase.reference.child("Users").child(mAuth.currentUser!!.uid)

        btn_change.setOnClickListener {
            if (newOnePass.text.toString().equals(newTwoPass.text.toString())) {
                mAuth.confirmPasswordReset(oldPass.text.toString(), newOnePass.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Пароль изменен", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Что-то не так, повторите попытку позже", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        return v
    }
}
