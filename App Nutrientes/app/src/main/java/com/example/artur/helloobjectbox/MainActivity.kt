package com.example.artur.helloobjectbox

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.artur.helloobjectbox.Adapter.PessoasAdapter
import com.example.artur.helloobjectbox.Model.Usuario
import com.example.artur.helloobjectbox.dal.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var usuariobox: Box<Usuario>
    private lateinit var rvUsuarios: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuariobox = ObjectBox.boxStore.boxFor(Usuario::class.java)
        rvUsuarios = rv_usuarios
    }

    override fun onResume() {
        super.onResume()

        val usuarios = usuariobox.all
        val usuarioAdapter = PessoasAdapter(usuarios, usuariobox, this)
        rvUsuarios.adapter = usuarioAdapter
        rvUsuarios.layoutManager = LinearLayoutManager(this)
        rvUsuarios.hasFixedSize()
    }

    fun novoUsuario(view: View){
        startActivity(Intent(this, CadastroActivity::class.java))
    }
}
