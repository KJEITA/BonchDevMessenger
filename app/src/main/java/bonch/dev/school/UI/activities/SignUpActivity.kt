package bonch.dev.school.UI.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bonch.dev.school.R

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun signUp(view: View){
        val intent = Intent(SignUpActivity@this, MainAppActivity::class.java)
        startActivity(intent)
    }
}
