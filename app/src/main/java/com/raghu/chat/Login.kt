package com.raghu.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var etemail :EditText
    private lateinit var etpassword:EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var mauth :FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_login)

        etemail=findViewById(R.id.email)
        etpassword=findViewById(R.id.password)
        btnLogin=findViewById(R.id.btlogin)
        btnSignUp=findViewById(R.id.btsignup)
        mauth =FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener{
            val intent= Intent(this,signup::class.java)
            startActivity(intent)
            finish()

        }

        btnLogin.setOnClickListener{

            val email =etemail.text.toString()
            val password =etpassword.text.toString()

            login(email,password)
        }

    }

    private fun login(email: String, password: String) {

        mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){ task ->

            if (task.isSuccessful) {
                Toast.makeText(this,"Successfully Logged in", Toast.LENGTH_LONG).show()
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()


            }else
            {


                Toast.makeText(this,"User not found or internet error", Toast.LENGTH_LONG).show()
            }


        }



    }
}