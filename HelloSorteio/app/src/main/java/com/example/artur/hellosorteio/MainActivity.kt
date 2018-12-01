package com.example.artur.hellosorteio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var numSorteado: Int = 0
    private lateinit var numeroSorteado: TextView
    private lateinit var editNumeroInicial: EditText
    private lateinit var editNumeroFinal: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
    }

    private fun setUpViews() {
        editNumeroFinal = edit_valor_final
        editNumeroInicial = edit_valor_inicial
        numeroSorteado = text_numero_sorteado
    }

    fun sorteio(view: View){
        val random = Random()
        numSorteado = random.nextInt(obterValorFinal() - obterValorInicial())
        numeroSorteado.text = numSorteado.toString()
    }

    fun obterValorInicial(): Int{
        val entrada = editNumeroInicial.text.toString()
        var valor = 0
        if (!entrada.trim().isEmpty()) valor = entrada.toInt()
        else Toast.makeText(this, "Digite um valor!", Toast.LENGTH_SHORT).show()

        return valor
    }

    fun obterValorFinal(): Int{
        val entrada = editNumeroFinal.text.toString()
        var valor = 0
        if (!entrada.trim().isEmpty()) valor = entrada.toInt()
        else Toast.makeText(this, "Digite um valor!", Toast.LENGTH_SHORT).show()

        return valor
    }
}
