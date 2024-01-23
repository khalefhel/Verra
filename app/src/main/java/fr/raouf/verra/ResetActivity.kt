package fr.raouf.verra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException

class ResetActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.editTextEmail)

        val resetButton: Button = findViewById(R.id.buttonReset)
        resetButton.setOnClickListener {
            resetPassword()
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }

    private fun resetPassword() {
        val email = emailEditText.text.toString()

        if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("ResetActivity", "E-mail de réinitialisation envoyé avec succès.")
                        // Affichez un message à l'utilisateur indiquant que l'e-mail a été envoyé
                    } else {
                        handleResetPasswordError(task.exception)
                    }
                }
        } else {
            // Affichez un message demandant à l'utilisateur de saisir une adresse e-mail valide
            Log.e("ResetActivity", "Adresse e-mail invalide.")
        }
    }

    private fun handleResetPasswordError(exception: Exception?) {
        when (exception) {
            is FirebaseAuthActionCodeException -> {
                // Gestion spécifique de FirebaseAuthActionCodeException si nécessaire
                Log.e("ResetActivity", "Erreur de code d'action Firebase : ${exception.message}")
            }

            is FirebaseException -> {
                // Gestion générale des erreurs Firebase
                Log.e("ResetActivity", "Erreur Firebase : ${exception.message}")
            }

            else -> {
                // Gestion des autres types d'erreurs
                Log.e("ResetActivity", "Erreur inattendue : ${exception?.message}")
            }
        }
    }
}