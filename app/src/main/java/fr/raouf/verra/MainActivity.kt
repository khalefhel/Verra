package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import fr.raouf.verra.fragments.HomeFragment
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var btnConnect: Button
    lateinit var btnRegister: Button
    lateinit var edit_Email: EditText
    lateinit var edit_Password: EditText
    lateinit var error: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        btnConnect = findViewById(R.id.btnConnect)
        btnRegister = findViewById(R.id.btnRegister)
        edit_Email = findViewById(R.id.edit_Email)
        edit_Password = findViewById(R.id.edit_Password)
        error = findViewById(R.id.error)
    }
    override fun onStart() {
        super.onStart()

        btnRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        btnConnect.setOnClickListener {

            edit_Email.error = null
            edit_Password.error = null

            val email = edit_Email.text.toString()
            val password = edit_Password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                if (password.isEmpty()) {
                    edit_Password.error = "Mot de passe est obligatoire"
                }
                if (email.isEmpty()) {
                    edit_Email.error = "Email est obligatoire"
                }
            } else {
                signIn(email, password)
            }
        }

        val button_tst: View = findViewById(R.id.button_tst)
        button_tst.setOnClickListener {
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
    }

    fun signIn(email: String, password: String) {
        Log.d("signIn", "signIn user....")

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {task ->
            if (task.isSuccessful) {
                Intent(this, HomeActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            } else {
                edit_Password.error = "Mot de passe incorrect"
            }
        }
    }
}
