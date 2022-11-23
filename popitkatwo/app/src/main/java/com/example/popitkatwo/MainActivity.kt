package com.example.popitkatwo

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.popitkatwo.R
import com.example.popitkatwo.generalactivity

class MainActivity : AppCompatActivity() {
    public final var APP_PREFERENCES = "mysettings"

    lateinit var settings:SharedPreferences
    lateinit var editPass: EditText
    lateinit var editLog: EditText
    lateinit var editEmail: EditText
    var hasVisited:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get preferences

        settings = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        // get and check login
        val login : String = settings.getString("login", "") ?: ""
        val email : String = settings.getString("email", "") ?: ""
        hasVisited = login.isNotEmpty()

        editPass = findViewById((R.id.editpas))
        editLog = findViewById((R.id.editlog))
        editEmail = findViewById((R.id.editema))

        if(hasVisited)
        {
            editLog.setText(login)
            editEmail.setText(email)
        }


    }


    fun NextButton(view: View) {
        val hasLogin = editLog.text.toString().isNotEmpty()
        val hasPassword = editPass.text.toString().isNotEmpty()
        val hasEmail = editEmail.text.toString().isNotEmpty()

        when {
            !hasLogin || !hasPassword || !hasEmail ->
                AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Незаполнены поля!!!")
                    .setPositiveButton("Ок",null)
                    .create()
                    .show()
            !hasVisited -> {
                val login = editLog.getText().toString()
                val password = editPass.getText().toString()
                val email = editEmail.getText().toString()
                val prefEditor = settings.edit()
                prefEditor.putString("login", login)
                prefEditor.putString("password", password)
                prefEditor.putString("email", email)
                prefEditor.apply()
                val intent = Intent(this, generalactivity::class.java)
                startActivity((intent))
            }
            else -> {
                var code = editPass.getText().toString();
                var getpas = settings.getString("password","nopas")
                if (code == getpas)
                {
                    val intent = Intent(this, generalactivity::class.java)
                    startActivity((intent))
                }
                else {
                    AlertDialog.Builder(this)
                        .setTitle("Ошибка")
                        .setMessage("Неверный пароль!")
                        .setPositiveButton("Ок",null)
                        .create()
                        .show()
                }

            }
        }
    }
    fun clear(view: View) {
        val e: SharedPreferences.Editor = settings.edit()
        e.clear()
        e.apply()
    }

}