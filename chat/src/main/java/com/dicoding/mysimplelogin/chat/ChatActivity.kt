package com.dicoding.mysimplelogin.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.dicoding.tugas.core.SessionManager
import com.dicoding.tugas.core.UserRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val tvChat = findViewById<TextView>(R.id.tv_chat)
        val sesi = SessionManager(this)
        val userRepository = UserRepository.getInstance(sesi)
        val fb = findViewById<FloatingActionButton>(R.id.fab_cat)
        tvChat.text = "Hello ${userRepository.getUser()}! \n Welcome to Chat Feature"

        fb.setOnClickListener {
            try {
                moveToChatActivity()
            } catch (e: Exception){
                Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun moveToChatActivity() {
        startActivity(Intent(this, Class.forName("com.dicoding.mysimplelogin.chat.ChatActivity")))
    }
    }
