package com.raghu.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signup : AppCompatActivity() {


    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var etname: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mauth: FirebaseAuth
    private lateinit var dbref:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)

        etname = findViewById(R.id.username)
        etemail = findViewById(R.id.email)
        etpassword = findViewById(R.id.password)
        btnSignUp = findViewById(R.id.btsignup)
        mauth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {

            val email = etemail.text.toString()
            val password = etpassword.text.toString()
            val name=etname.text.toString()

            sup(email, password,name)
        }


    }

    private fun sup(email: String, password: String,name:String) {

        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                mauth.currentUser?.let { addnewuser(name,email, it.uid) }


                    Toast.makeText(this,"Successfully created new account",Toast.LENGTH_LONG).show()
                val intent= Intent(this,Login::class.java)
                startActivity(intent)
                finish()

            }else
            {
                Toast.makeText(this,"Error,try again",Toast.LENGTH_LONG).show()

            }

        }
    }

    private fun addnewuser(name: String, email: String, uid: String) {

        dbref=FirebaseDatabase.getInstance().getReference()

        dbref.child("users").child(uid).setValue(user(name, email, uid))



    }
}