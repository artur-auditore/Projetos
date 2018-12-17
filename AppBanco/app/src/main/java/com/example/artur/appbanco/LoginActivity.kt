package com.example.artur.appbanco

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1
    }

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
        startActivityForResult(Intent(this, CadastroActivity::class.java), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val numeroConta = intent.getIntExtra(CadastroActivity.NUMERO_CONTA, 0)
                val nome = intent.getStringExtra(CadastroActivity.NOME_USUARIO)

                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Número da conta")
                alertDialog.setMessage("$nome o número da sua conta é $numeroConta")
                alertDialog.setNeutralButton("OK"){ _, _ ->

                }
                alertDialog.create().show()
            }
        }
    }
}
