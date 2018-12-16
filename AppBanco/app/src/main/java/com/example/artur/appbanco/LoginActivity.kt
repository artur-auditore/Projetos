package com.example.artur.appbanco

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var buttonCadastro: Button
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setUpViews()

        buttonCadastro.setOnClickListener {
            cadastrar()
        }
    }

    private fun setUpViews() {
        buttonCadastro = button_cadastro
        buttonLogin = button_login
    }

    private fun cadastrar(){
        startActivity(Intent(this, CadastroActivity::class.java))
    }
}
