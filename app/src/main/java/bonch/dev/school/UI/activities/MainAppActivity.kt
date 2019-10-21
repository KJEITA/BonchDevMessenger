package bonch.dev.school.UI.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import bonch.dev.school.R
import bonch.dev.school.UI.fragments.ChatFragmenst
import bonch.dev.school.UI.fragments.PasswordFragment
import bonch.dev.school.UI.fragments.ProfileFragment

class MainAppActivity : AppCompatActivity() {
    val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)


        fragmentManager.beginTransaction().add(R.id.mainFragment, ChatFragmenst())
            .commit()

    }

    fun replaceFragment(view: View) {
        fragmentManager.beginTransaction().replace(R.id.mainFragment, ProfileFragment())
            .addToBackStack("thirdFragment")
            .commit()
    }

    fun changePassword(view: View) {
        PasswordFragment().show(supportFragmentManager, "dlg")
    }
}
