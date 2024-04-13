package com.example.loginandsignupusingfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignupusingfirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignLA.setOnClickListener {
            val intent = Intent(this@LoginActivity , signupActivity::class.java)
            startActivity(intent)
            finish()
        }

        /// initialize FireBase
        auth = FirebaseAuth.getInstance()
        //// Work on Login Activity

        //// apply on Click listner on Button Login
        binding.btnLoginLA.setOnClickListener {
            UserLogin() // here we call function

        }

    }
    //////////// here we create function for login
    private fun UserLogin(){
        val UserEmail : String = binding.edtUserEmail.text.toString()
        val UserPassword : String = binding.password.text.toString()

        if (UserEmail.isEmpty() || UserPassword.isEmpty()){
            Toast.makeText(this, "Please fill All Details", Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(UserEmail,UserPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                        // when login is success then ggo to main Activity
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                    }else{
                        Toast.makeText(this, "Login  Faild Try Again ${task.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}