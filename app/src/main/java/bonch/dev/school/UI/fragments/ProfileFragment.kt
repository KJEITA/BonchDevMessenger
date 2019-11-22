package bonch.dev.school.UI.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import android.widget.Toast
import bonch.dev.school.UI.activities.MainAppActivity
import bonch.dev.school.UI.activities.SignInActivity


class ProfileFragment : Fragment() {
    lateinit var name_et: TextView
    lateinit var email_t: TextView

    lateinit var conf_btn: Button
    lateinit var out_btn: Button



    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(bonch.dev.school.R.layout.fragment_profile, container, false)

        mDataBase = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        mReference = mDataBase.reference.child("Users").child(mAuth.currentUser!!.uid)

        name_et = view.findViewById(bonch.dev.school.R.id.name_edit_text)
        email_t = view.findViewById(bonch.dev.school.R.id.email_text_view)
        conf_btn = view.findViewById(bonch.dev.school.R.id.email_confirm_button)
        out_btn = view.findViewById(bonch.dev.school.R.id.sign_out_button)

        mReference.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    name_et.text = p0.child("name").value as String
                    email_t.text = p0.child("email").value as String
                    if(mAuth.currentUser!!.isEmailVerified){
                        conf_btn.visibility = View.INVISIBLE
                    }
                }
            })



        conf_btn.setOnClickListener {
        val user =
            FirebaseAuth.getInstance().currentUser
        user!!.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()

            }
        }
        }

        out_btn.setOnClickListener {
            mAuth.signOut()

            val intent = Intent(context, SignInActivity::class.java)
            startActivity(intent)
        }

    return view
}
}