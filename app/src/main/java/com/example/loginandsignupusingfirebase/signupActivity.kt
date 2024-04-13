package com.example.loginandsignupusingfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandsignupusingfirebase.databinding.ActivityLoginBinding
import com.example.loginandsignupusingfirebase.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginSA.setOnClickListener {
            val intent = Intent(this@signupActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        ////////////// Fire we code for Signup

        ////////////// Initialize Fire Base
        auth = FirebaseAuth.getInstance()
        //////////////// Set on Click Listner on Button SignUp
        binding.btnSignSA.setOnClickListener {
            UserSignup()              /// here we call function
        }
    }
    ////////// Create Function UserSign
    private fun UserSignup() {
        /// her we get all value
        val UserEmail : String = binding.userEmailId.text.toString()
        val UserName : String = binding.userName.text.toString()
        val UserPassword : String = binding.password.text.toString()
        val UserRepeatPassword : String = binding.repeatPassword.text.toString()

        // here we fill all box bt the user
        if (UserName.isEmpty() || UserEmail.isEmpty() || UserPassword.isEmpty() || UserRepeatPassword.isEmpty()){
            Toast.makeText(this, "Please Fill All Details", Toast.LENGTH_SHORT).show()
        }else if(UserPassword != UserRepeatPassword){
            Toast.makeText(this, "Repeat Password is Must be Same", Toast.LENGTH_SHORT).show()
        }else{
            auth.createUserWithEmailAndPassword(UserEmail,UserPassword)
                .addOnCompleteListener(this) {task->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Registration Successfully", Toast.LENGTH_SHORT).show()
                        // when register is successfully then we go to login page
                        startActivity(Intent(this@signupActivity,LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Registration Faild Try Again ${task.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}