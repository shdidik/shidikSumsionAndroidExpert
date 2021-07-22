package com.dicoding.tugas.ReactiveLogin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.dicoding.tugas.MainActivity
import com.dicoding.tugas.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var broadcastReceiver: BroadcastReceiver
    private var emailValid = false
    private var passwordValid = false
    private var passwordConfirmationValid = false

    private lateinit var btnRegister: Button
    private lateinit var edEmail: TextInputEditText
    private lateinit var edPassword: TextInputEditText
    private lateinit var edConfirmPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        edEmail = findViewById(R.id.ed_email)
        edPassword = findViewById(R.id.ed_password)
        edConfirmPassword = findViewById(R.id.ed_confirm_password)
        btnRegister = findViewById(R.id.btn_register)



        validateButton()
        btnRegister.setOnClickListener(this)



        edEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateEmail()
            }
        })

        edPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword()
            }
        })

        edConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePasswordConfirmation()
            }
        })
    }

    private fun registerBroadCastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                when (intent.action) {
                    Intent.ACTION_POWER_CONNECTED -> {
                        tv_power_status.text = getString(R.string.power_connected)
                    }
                    Intent.ACTION_POWER_DISCONNECTED -> {
                        tv_power_status.text = getString(R.string.power_disconnected)
                    }
                }
            }
        }
        val intentFilter = IntentFilter()
        intentFilter.apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        registerReceiver(broadcastReceiver, intentFilter)

    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_register -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else -> {
                // else condition
            }
        }
    }
    fun validateEmail() {
        // jika password tidak valid tampilkan peringatan
        val input = edEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            emailValid = false
            showEmailExistAlert(true)
        } else {
            emailValid = true
            showEmailExistAlert(false)
        }
        validateButton()
    }

    fun validatePassword() {
        // jika password < 6 karakter tampilkan peringatan
        val input = edPassword.text.toString()
        if (input.length < 6) {
            passwordValid = false
            showPasswordMinimalAlert(true)
        } else {
            passwordValid = true
            showPasswordMinimalAlert(false)
        }
        validateButton()
    }

    fun validatePasswordConfirmation() {
        // jika konfirmasi password tidak sesuai tampilkan peringatan
        val input = edConfirmPassword.text.toString()
        if (input != edPassword.text.toString()) {
            passwordConfirmationValid = false
            showPasswordConfirmationAlert(true)
        } else {
            passwordConfirmationValid = true
            showPasswordConfirmationAlert(false)
        }
        validateButton()
    }

    private fun validateButton() {
        // jika semua field sudah terisi, enable button submit
        if (emailValid && passwordValid && passwordConfirmationValid) {
            btnRegister.isEnabled = true

            btnRegister.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))


        } else {
            btnRegister.isEnabled = false
            btnRegister.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        }
    }

    private fun showEmailExistAlert(isNotValid: Boolean) {
        edEmail.error = if (isNotValid) getString(R.string.email_not_valid) else null
    }

    private fun showPasswordMinimalAlert(isNotValid: Boolean) {
        edPassword.error = if (isNotValid) getString(R.string.password_not_valid) else null
    }

    private fun showPasswordConfirmationAlert(isNotValid: Boolean) {
        edConfirmPassword.error = if (isNotValid) getString(R.string.password_not_same) else null
    }

}

