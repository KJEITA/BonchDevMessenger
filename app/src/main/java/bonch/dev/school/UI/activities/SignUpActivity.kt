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

class SignUpActivity : AppCompatActivity() {

    private lateinit var mDataBase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    private lateinit var nameText: EditText
    private lateinit var emailText: EditText
    private lateinit var passwordText: EditText
    private lateinit var passwordConfirmText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        nameText = findViewById(R.id.login_et)
        emailText = findViewById(R.id.email_sign_up_et)
        passwordText = findViewById(R.id.password_sign_up_et)
        passwordConfirmText = findViewById(R.id.password_confirm_et)

        mDataBase = FirebaseDatabase.getInstance()
        mReference = mDataBase.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()
    }

    fun signUp(view: View) {
        val name = nameText.text.toString()
        val email = emailText.text.toString()
        val password = passwordText.text.toString()
        val passwordConfirm = passwordConfirmText.text.toString()

        if (password.equals(passwordConfirm)) {
            if (name != "" && email != "" && password != "" && passwordConfirm != "") {
                try{
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser!!.uid
                            val currentUserDb = mReference.child(user)
                            currentUserDb.child("name").setValue(name)
                            currentUserDb.child("password").setValue(password)
                            currentUserDb.child("actEmail").setValue(0)
                            currentUserDb.child("email").setValue(email)

                            val intent = Intent(SignUpActivity@ this, MainAppActivity::class.java)
                            startActivity(intent)
                        } else {

                        }
                    }
            }catch (err: HttpRetryException){

            }
            }
        } else {
            Toast.makeText(applicationContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        }
    }
}
