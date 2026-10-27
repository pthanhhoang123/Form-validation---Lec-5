package com.example.lec5

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvFullName = findViewById<TextView>(R.id.tvFullName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvUsername = findViewById<TextView>(R.id.tvUsername)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val user = intent.getSerializableExtra("USER_DATA") as? User
        user?.let {
            tvFullName.text = "Họ và tên: ${it.fullName}"
            tvEmail.text = "Email: ${it.email}"
            tvUsername.text = "Tên đăng nhập: ${it.username}"
            tvPhone.text = "Số điện thoại: ${it.phone}"
        }

        btnLogout.setOnClickListener {
            finish()
        }
    }
}
