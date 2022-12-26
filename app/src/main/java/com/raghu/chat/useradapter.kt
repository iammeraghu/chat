package com.raghu.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class useradapter(val context: Context,val userlist :ArrayList<user>): RecyclerView.Adapter<useradapter.userviewholder>()

{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userviewholder {

        val view:View =LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return userviewholder(view)

    }

    override fun onBindViewHolder(holder: userviewholder, position: Int) {

        val currentuser=userlist[position]
        holder.textname.text=currentuser.name

        holder.itemView.setOnClickListener{
            val intent = Intent(context,chat::class.java)
            intent.putExtra("username",currentuser.name)
            intent.putExtra("recid",currentuser.uid)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return userlist.size

    }
    class userviewholder(itemView: View) : ViewHolder(itemView)
    {
        val textname =itemView.findViewById<TextView>(R.id.usertv)


    }


}