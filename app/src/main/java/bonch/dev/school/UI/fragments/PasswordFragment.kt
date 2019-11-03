package bonch.dev.school.UI.fragments

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import bonch.dev.school.R

class PasswordFragment : DialogFragment() {
    private lateinit var button_ok: Button
    private lateinit var button_cancel: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_password, container, false)
        return v
    }
}