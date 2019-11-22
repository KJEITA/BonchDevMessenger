package bonch.dev.school.UI.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import bonch.dev.school.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.net.HttpRetryException

class SignInActivity : AppCompatActivity() {

    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        emailText = findViewById(R.id.email_sign_in_edit_text)
        passwordText = findViewById(R.id.password_sign_in_edit_text)

        mDataBase = FirebaseDatabase.getInstance()
        mReference = mDataBase.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
    }

    fun signIn(view: View) {
        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (email != "" && password != "") {
            try {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(SignInActivity@ this, MainAppActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Неверный логин или пароль",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }catch (err:HttpRetryException){

            }
        }
    }

    fun signUp(view: View) {
        val intent = Intent(SignInActivity@ this, SignUpActivity::class.java)
        startActivity(intent)
    }
}
