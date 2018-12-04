package com.example.artur.hellosorteiox

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import java.util.*

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
        if (this.nomes.size < 2){
            Snackbar.make(view, "Nomes insuficientes para sorteio!", Snackbar.LENGTH_SHORT).show()
        } else {

            nomeAtual = sortear()
            textNomeSorteado.text = nomeAtual
        }
    }

    private fun sortear(): String{
        //nomes.shuffle()
        val random = Random()
        return nomes[random.nextInt(nomes.size - 0)]
    }

    fun addNomes(view: View){
        val nome = editNome.text.toString()

        if (nome.trim() == ""){
            Snackbar.make(view, "Digite um nome válido!", Snackbar.LENGTH_SHORT).show()
        } else {
            this.nomes.add(nome)
            Toast.makeText(this, "$nome adicionado", Toast.LENGTH_SHORT).show()
        }
    }

    fun verNomes(view: View){
        if (this.nomes.size == 0){
            Snackbar.make(view, "Não há nomes!", Snackbar.LENGTH_SHORT).show()
        } else {

            val intent = Intent(this, ListaActivity::class.java)
            intent.putExtra("nomes", nomes)
            startActivity(intent)
        }
    }

}
