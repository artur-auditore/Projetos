package com.example.watcher

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watcher.Model.Usuario
import com.example.watcher.Model.Usuario_
import com.example.watcher.dal.App
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        const val KEY = "key"
    }

    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignup: Button

    private lateinit var usuarioBox: Box<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuarioBox = App.ObjectBox.boxStore.boxFor(Usuario::class.java)
        bind()
        cadastar()
        entrar()
    }

    private fun bind(){
        editEmail = edit_email_login
        editSenha = edit_senha_login
        buttonLogin = button_login
        buttonSignup = button_cadastro
    }

    private fun cadastar() {
        buttonSignup.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }

    private fun logar(usuario: Usuario){
        val sharedPreferences = getSharedPreferences("w.file", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(KEY, usuario.id)
        editor.apply()
        startActivity(Intent(this, TimeLineActivity::class.java))
    }

    private fun entrar(){
        buttonLogin.setOnClickListener {it ->
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            val result = usuarioBox.query()
                .equal(Usuario_.username, email)
                .equal(Usuario_.email, email)
                .equal(Usuario_.senha, senha)
                .build()
                .find()

            if (result.size > 0) {
                logar(result[0])
            }else {
                editSenha.text.clear()
                Toast.makeText(this, "Email e/ou senha incorreto(s)!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}
