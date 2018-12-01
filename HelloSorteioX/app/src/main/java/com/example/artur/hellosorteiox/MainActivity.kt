package com.example.artur.hellosorteiox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import java.util.*
import java.nio.file.Files.size




class MainActivity : AppCompatActivity() {

    private var nomeAtual = ""
    private val nomes = arrayListOf<String>()
    private lateinit var editNome: EditText
    private lateinit var textNomeSorteado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
    }

    private fun setUpViews(){
        editNome = edit_nome
        textNomeSorteado = text_nome_sorteado

    }

    fun sortear(view: View){
        nomeAtual = sortear()
        textNomeSorteado.text = nomeAtual
    }

    private fun sortear(): String{
        nomes.shuffle()
        return nomes[Math.random().toInt() * nomes.size]
    }

    fun addNomes(view: View){
        this.nomes.add("${editNome.text}\n")
        Toast.makeText(this, "${editNome.text} adicionado", Toast.LENGTH_SHORT).show()
    }

    private fun lista(): String{
        var dados = ""
        for (nome in this.nomes){
            dados += "$nome\n"
        }
        return dados
    }

    fun verNomes(view: View){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Nomes")
        alertDialog.setMessage(this.lista())

        if (this.nomes.size == 0){
            Toast.makeText(this, "Não há nomes", Toast.LENGTH_SHORT).show()
        } else {
            alertDialog.create().show()
        }
    }
}
