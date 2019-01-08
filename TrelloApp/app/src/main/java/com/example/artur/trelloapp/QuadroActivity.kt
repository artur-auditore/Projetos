package com.example.artur.trelloapp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.artur.trelloapp.Modelo.Quadro
import com.example.artur.trelloapp.dal.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_quadro.*
import kotlinx.android.synthetic.main.novo_quadro.view.*

class QuadroActivity : AppCompatActivity() {

    private lateinit var quadrosBox: Box<Quadro>
    private lateinit var rvQuadros: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quadro)

        quadrosBox = ObjectBox.boxStore.boxFor(Quadro::class.java)
        setUpViews()
        floatingActionButton.setOnClickListener {
            novoQuadro()
        }

    }

    private fun setUpViews(){
        floatingActionButton = fab_novo_quadro
        rvQuadros = rv_quadros
    }

    override fun onResume() {
        super.onResume()

        val quadrosAdapter = QuadroAdapter(quadrosBox.all, this)
        rvQuadros.adapter = quadrosAdapter
        rvQuadros.layoutManager = GridLayoutManager(this, 2)
        rvQuadros.hasFixedSize()
    }

    @SuppressLint("InflateParams")
    private fun novoQuadro(){
        val alertDialog = AlertDialog.Builder(this)
        val viewDialog = layoutInflater.inflate(R.layout.novo_quadro, null)

        alertDialog.setView(viewDialog)
        alertDialog.setTitle("Novo Quadro")

        alertDialog.setPositiveButton("CRIAR"){ _, _ ->
            val editnome = viewDialog.edit_nome_quadro
            val nome = editnome.text.toString()
            if (nome.trim() == ""){
                Toast.makeText(this, "Nome inválido", Toast.LENGTH_SHORT).show()
            } else{
                val quadro = Quadro(nome)
                quadrosBox.put(quadro)
            }
        }
        alertDialog.setNegativeButton("CANCELAR"){ _, _ ->}
        alertDialog.show()
    }
}
