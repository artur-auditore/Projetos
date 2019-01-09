package com.example.artur.trelloapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.artur.trelloapp.Modelo.Lista
import io.objectbox.Box

class ListasActivity : AppCompatActivity() {

    companion object {
        const val NOME = "nome"
    }

    private lateinit var listasBox: Box<Lista>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listas)
        title = intent.getStringExtra(NOME)
    }
}
