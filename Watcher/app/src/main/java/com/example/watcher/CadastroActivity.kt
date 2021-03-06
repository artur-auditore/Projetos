package com.example.watcher

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.watcher.Model.Usuario
import com.example.watcher.dal.App
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_cadastro.*
import com.example.watcher.Model.Usuario_

class CadastroActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editUsername: EditText
    private lateinit var editSenha: EditText
    private lateinit var editConfirmSenha: EditText

    private lateinit var usuarioBox: Box<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        usuarioBox = App.ObjectBox.boxStore.boxFor(Usuario::class.java)
        bind()
    }

    private fun bind(){
        editNome = edit_nome
        editUsername = edit_username
        editEmail = edit_email
        editSenha = edit_senha
        editConfirmSenha = edit_confirm_senha
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_salvar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) { R.id.op_salvar -> cadastrar() }
        return super.onOptionsItemSelected(item)
    }

    private fun cadastrar(){

        val nome = editNome.text.toString()
        val username = editUsername.text.toString()
        val email = editEmail.text.toString()
        val senha = editSenha.text.toString()
        val confirmSenha = editConfirmSenha.text.toString()

        val result = usuarioBox.query()
            .equal(Usuario_.email, email)
            .build()
            .find()

        if (senha != confirmSenha){

            Toast.makeText(this, "As senhas devem ser iguais", Toast.LENGTH_LONG).show()

        } else if (nome.trim() == "" || username.trim() == "" || email.trim() == "" ||
                senha.trim() == "" || confirmSenha.trim() == ""){

            Toast.makeText(this, "Preecha todos os dados!", Toast.LENGTH_LONG).show()

        } else if (result.size > 0) {

            Toast.makeText(this, "E-mail já cadastrado!", Toast.LENGTH_LONG).show()

        } else {

            val usuario = Usuario(nome, username, email, senha)
            usuarioBox.put(usuario)
            logar(usuario)
            finish()
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun logar(usuario: Usuario){
        val sharedPreferences = getSharedPreferences("w.file", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(LoginActivity.KEY, usuario.id)
        editor.commit()
        startActivity(Intent(this, TimeLineActivity::class.java))
    }
}
