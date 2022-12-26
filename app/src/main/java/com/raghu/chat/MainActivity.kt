package com.raghu.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var rview:RecyclerView
    private lateinit var userlist:ArrayList<user>
    private lateinit var adapter:useradapter
    private lateinit var mauth:FirebaseAuth
    private lateinit var dbref: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userlist =ArrayList()
        adapter=useradapter(this,userlist)
        mauth= FirebaseAuth.getInstance()
        dbref=FirebaseDatabase.getInstance().getReference()
        recyclerView=findViewById(R.id.rview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =adapter

        dbref.child("users").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userlist.clear()
                for(i in snapshot.children)
                {

                    val currentuser= i.getValue(user::class.java)

                    if(mauth.currentUser?.uid != currentuser?.uid)
                    {
                    userlist.add(currentuser!!)

                    }

                }

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,"database error", Toast.LENGTH_LONG).show()
            }


        })









    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout)
        {
            mauth.signOut()
            Toast.makeText(this,"Successfully Logged Out", Toast.LENGTH_LONG).show()
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
            finish()
            return true

        }
        if(item.itemId==R.id.info){
            val intent= Intent(this,appinfo::class.java)
            startActivity(intent)



        }
            return true
    }



}