package com.example.artur.hellosorteiox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_lista.*

class ListaActivity : AppCompatActivity() {

    private lateinit var rvNomes: RecyclerView
    //private lateinit var nomes: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        rvNomes = lista_nomes
        val intent = intent
        val nomes = intent.getStringArrayListExtra("nomes")

        rvNomes.adapter = NomeAdapter(nomes, this)
        val layoutManager = LinearLayoutManager(this)
        rvNomes.layoutManager = layoutManager
    }


}
