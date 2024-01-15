package fr.raouf.verra.db

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import fr.raouf.verra.R
import fr.raouf.verra.RegisterActivity

class AddUsers (
    private val context: RegisterActivity
) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.activity_register, container, false)

        // recuperer le boutton pour charger users
        val pickupButton = view?.findViewById<Button>(R.id.btnSave)

        //

        return view
    }
}