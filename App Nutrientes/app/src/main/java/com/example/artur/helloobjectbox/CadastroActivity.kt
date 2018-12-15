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

    companion object {
        const val ID = "usuarioId"
        const val DEFAUT_VALUE = -1
    }

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
        usuarioBox = ObjectBox.boxStore.boxFor(Usuario::class.java)
        usuario = Usuario()

        val intent = intent
        val id = intent.getLongExtra(ID, DEFAUT_VALUE.toLong())

        editNome = edit_nome
        editPeso = edit_peso
        editAltura = edit_altura


        if (id != DEFAUT_VALUE.toLong()){
            usuario = usuarioBox.get(id)
            editNome.setText(usuario.nome)
            editAltura.setText(usuario.altura.toString())
            editPeso.setText(usuario.peso.toString())
        }
    }

    fun adicionar(view: View){

        val nome = editNome.text.toString()
        val peso = editPeso.text.toString()
        val altura = editAltura.text.toString()

        usuario.nome = nome
        usuario.peso = peso.toDouble()
        usuario.altura = altura.toDouble()
        usuarioBox.put(usuario)
        finish()
    }
}
