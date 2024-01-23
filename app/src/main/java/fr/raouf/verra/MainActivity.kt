package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
    lateinit var edit_Visibility: ImageView
    lateinit var btn_reset: Button
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
        edit_Visibility = findViewById(R.id.edit_Visibility)
        btn_reset = findViewById(R.id.btn_reset)

        edit_Visibility.tag = true
        edit_Visibility.setOnClickListener {
            togglePasswordVisibility()
        }
    }
    override fun onStart() {
        super.onStart()

        btn_reset.setOnClickListener {
            Intent(this, ResetActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }

        btnRegister.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
            finish()
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
                togglePasswordVisibility()
                signIn(email, password)
            }
        }
    }

     fun togglePasswordVisibility() {
         val visibilityOff = R.drawable.ic_visibilityoff
         val visibility = R.drawable.ic_visibility
         val password = edit_Password.text.toString()

         if (password.isNotEmpty()) {
             if (edit_Visibility.tag == true) {
                 edit_Visibility.setImageResource(visibilityOff)
                 // Rendre le mot de passe visible
                 edit_Password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
             } else {
                 edit_Visibility.setImageResource(visibility)
                 // Masquer le mot de passe
                 edit_Password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
             }

             // Inverser l'état de la propriété tag
             edit_Visibility.tag = !(edit_Visibility.tag as Boolean)

             // Mettre à jour le curseur à la fin du texte
             edit_Password.setSelection(edit_Password.text.length)
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
