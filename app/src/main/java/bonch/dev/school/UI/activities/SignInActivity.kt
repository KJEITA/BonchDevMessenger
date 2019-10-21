package bonch.dev.school.UI.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bonch.dev.school.R
import bonch.dev.school.UI.fragments.ChatFragmenst

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun signIn(view:View){
        val intent = Intent(SignInActivity@this, MainAppActivity::class.java)
        startActivity(intent)
    }

    fun signUp(view:View){
        val intent = Intent(SignInActivity@this, SignUpActivity::class.java)
        startActivity(intent)
    }
}
