package com.example.lec5

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsername: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etUsername = findViewById(R.id.etUsername)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun setupListeners() {
        btnRegister.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            val errorMessage = validateInput(fullName, email, username, phone, password, confirmPassword)
            if (errorMessage != null) {
                showSnackbar(errorMessage)
            } else {
                val user = User(fullName, email, username, phone, password)
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("USER_DATA", user)
                }
                startActivity(intent)
            }
        }

        btnCancel.setOnClickListener {
            clearFields()
        }
    }

    private fun validateInput(
        fullName: String,
        email: String,
        username: String,
        phone: String,
        pass: String,
        confirmPass: String
    ): String? {
        if (fullName.isEmpty() || email.isEmpty() || username.isEmpty() || phone.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            return "Vui lòng nhập đầy đủ tất cả các trường!"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Email không hợp lệ!"
        }
        if (phone.length < 10) {
            return "Số điện thoại không hợp lệ!"
        }
        if (pass.length < 6) {
            return "Mật khẩu phải có ít nhất 6 ký tự!"
        }
        if (pass != confirmPass) {
            return "Mật khẩu xác nhận không khớp!"
        }
        return null
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(findViewById(R.id.main), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun clearFields() {
        etFullName.text?.clear()
        etEmail.text?.clear()
        etUsername.text?.clear()
        etPhone.text?.clear()
        etPassword.text?.clear()
        etConfirmPassword.text?.clear()
        showSnackbar("Đã xoá trắng các trường nhập!")
    }
}
