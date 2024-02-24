package fr.raouf.verra.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fr.raouf.verra.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPhone: EditText
    lateinit var editPassword: EditText
    lateinit var editConfirmPassword: EditText
    lateinit var btnRegister: Button
    lateinit var registerError: TextView
    lateinit var editVisibility: ImageView
    lateinit var editConfirmVisibility: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        editPhone = findViewById(R.id.editPhone)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        registerError = findViewById(R.id.registerError)
        editVisibility = findViewById(R.id.editVisibility)
        editConfirmVisibility = findViewById(R.id.editConfirmVisibility)



        editVisibility.tag = true
        editVisibility.setOnClickListener {
            toggleRegisterPasswordVisibility(editVisibility, editPassword)
        }

        editConfirmVisibility.tag = true
        editConfirmVisibility.setOnClickListener {
            toggleRegisterPasswordVisibility(editConfirmVisibility, editConfirmPassword)
        }

        btnRegister.setOnClickListener {

            initErrors()

            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val phone = editPhone.text.toString()
            val password = editPassword.text.toString()
            val confirmPassword = editConfirmPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                if (name.isEmpty()) {
                    editName.error = "Nom obligatoire"
                }
                if (email.isEmpty()) {
                    editEmail.error = "Email obligatoire"
                }
                if (phone.isEmpty()) {
                    editPhone.error = "Numéro obligatoire"
                }
                if (password.isEmpty()) {
                    editPassword.error = "Mot de passe obligatoire"
                }
            } else {
                if (password != confirmPassword) {
                    editConfirmPassword.error = "Mot de passe incorrect"
                } else {

                    // Création d'un utilisateur dans le module authentification de firebase
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val user = hashMapOf(
                                "Name" to name,
                                "Email" to email,
                                "Phone" to phone,
                            )
                            val currentUser = auth.currentUser
                            // Création de l'utilisateur dans le module firebase
                            val db = Firebase.firestore
                            db.collection("users").document(currentUser!!.uid).set(user).addOnSuccessListener {
                                Intent(this, HomeActivity::class.java).also {
                                    startActivity(it)
                                }
                            }.addOnFailureListener {
                                editConfirmPassword.error = "Une erreur est survenue, veuillez réessayer plus tard !"
                            }
                        } else {
                            editConfirmPassword.error = "Une erreur est survenue, veuillez réessayer plus tard !"
                        }
                    }
                }
            }
        }

        val button_link: View = findViewById(R.id.btnLink)
        button_link.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initErrors() {
        editName.error = null
        editEmail.error = null
        editPhone.error = null
        editPassword.error = null
        btnRegister.error = null
        registerError.error = null
    }
    fun toggleRegisterPasswordVisibility(imageView: ImageView, editText: EditText) {
        val visibilityOff = R.drawable.ic_visibilityoff
        val visibility = R.drawable.ic_visibility

        if (imageView.tag == true) {
            imageView.setImageResource(visibilityOff)
            // Rendre le mot de passe visible
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            imageView.setImageResource(visibility)
            // Masquer le mot de passe
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        // Inverser l'état de la propriété tag
        imageView.tag = !(imageView.tag as Boolean)

        // Mettre à jour le curseur à la fin du texte si nécessaire
        editText.setSelection(editText.text.length)
    }
}