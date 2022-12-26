package com.raghu.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class chat : AppCompatActivity() {

    private lateinit var messagerview:RecyclerView
    private lateinit var messagebox:EditText
    private lateinit var send:ImageView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messagelist:ArrayList<Message>
    private lateinit var mdref:DatabaseReference
    var sroom :String? =null
    var rroom :String?=null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        messagebox=findViewById(R.id.chatbox)
        messagerview= findViewById(R.id.rviewchat)
        mdref=FirebaseDatabase.getInstance().getReference()
        send=findViewById(R.id.send)
        messagelist =ArrayList()
        messageAdapter= MessageAdapter(this,messagelist)

        messagerview.layoutManager =LinearLayoutManager(this)
        messagerview.adapter=messageAdapter


        val senderuid =FirebaseAuth.getInstance().currentUser?.uid
        val reciverid =intent.getStringExtra("recid")
        val name=intent.getStringExtra("username")
        supportActionBar?.title=name

       sroom = reciverid + senderuid
        rroom= senderuid + reciverid


            mdref.child("chats").child(sroom!!).child("messages").addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messagelist.clear()
                    for(i in snapshot.children)
                    {
                        val message =i.getValue(Message::class.java)
                        messagelist.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
            )

        send.setOnClickListener{

        val message =messagebox.text.toString()
        val messageobject =Message(message,senderuid)

         mdref.child("chats").child(sroom!!).child("messages").push().setValue(messageobject).addOnSuccessListener{
             mdref.child("chats").child(rroom!!).child("messages").push().setValue(messageobject)
         }

           messagebox.setText("")

        }

    }
}