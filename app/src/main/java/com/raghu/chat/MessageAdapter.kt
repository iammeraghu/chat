package com.raghu.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MessageAdapter(val context: Context,val messagelist :ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val R=1
    var S=2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1)
        {
            val view:View = LayoutInflater.from(context).inflate(com.raghu.chat.R.layout.recieve,parent,false)
            return reciveviewholder(view)

        } else
        {
            val view:View = LayoutInflater.from(context).inflate(com.raghu.chat.R.layout.send,parent,false)
            return sentviewholder(view)
        }





    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentmessage =messagelist[position]
        if (holder.javaClass == sentviewholder::class.java)
        {

            val viewHolder =holder as sentviewholder
            holder.sentmessage.text= currentmessage.message

        }
        else
        {

            val viewHolder = holder as reciveviewholder
            holder.recivedmessage.text=currentmessage.message
        }


    }

    override fun getItemViewType(position: Int): Int {
        val currentpos =messagelist[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentpos.senderid) ) {
            return S
        }


        else return R

    }

    override fun getItemCount(): Int {
      return messagelist.size
    }

    class sentviewholder(itemView :View) :RecyclerView.ViewHolder(itemView)
    {
            val sentmessage =itemView.findViewById<TextView>(R.id.sentmessagetv)
    }
    class reciveviewholder(itemView :View) :RecyclerView.ViewHolder(itemView)
    {
            val recivedmessage =itemView.findViewById<TextView>(R.id.recivedtv)
    }

}