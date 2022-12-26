package com.raghu.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class appinfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()

        setContentView(R.layout.activity_appinfo)


        val mTextView = findViewById<TextView>(R.id.tvaboutme)
        mTextView.movementMethod = LinkMovementMethod.getInstance()
    }
}