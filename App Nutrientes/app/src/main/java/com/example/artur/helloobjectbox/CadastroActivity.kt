package com.example.artur.helloobjectbox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.artur.helloobjectbox.Model.Usuario
import com.example.artur.helloobjectbox.dal.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    private lateinit var editNome: EditText
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var usuarioBox: Box<Usuario>
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setUpViews()
    }

    private fun setUpViews() {
        editNome = edit_nome
        editPeso = edit_peso
        editAltura = edit_altura

        usuarioBox = ObjectBox.boxStore.boxFor(Usuario::class.java)
    }

    fun adicionar(view: View){

        val nome = editNome.text.toString()
        val peso = editPeso.text.toString()
        val altura = editAltura.text.toString()

        usuario = Usuario(nome, peso.toDouble(), altura.toDouble())
        usuarioBox.put(usuario)
        finish()
    }
}
