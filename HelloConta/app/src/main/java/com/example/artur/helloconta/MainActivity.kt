package com.example.artur.helloconta

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var saldo: Double = 0.0
    private lateinit var textSaldo: TextView
    private lateinit var editDeposito: EditText
    private lateinit var editSaque: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
    }

    private fun setUpViews(){
        textSaldo = text_saldo
        editDeposito = edit_valor_deposito
        editSaque = edit_valor_saque
    }

    private fun obterValorDeposito(): Double{
        val entrada = editDeposito.text.toString()
        var valor = 0.0
        if (!entrada.trim().isEmpty()) valor = entrada.toDouble()
        else Toast.makeText(this, "Digite um valor!", Toast.LENGTH_SHORT).show()

        return valor
    }

    private fun obterValorSaque(): Double{
        val entrada = editSaque.text.toString()
        var valor = 0.0
        if (!entrada.trim().isEmpty()) valor = entrada.toDouble()
        else Toast.makeText(this, "Digite um valor!", Toast.LENGTH_SHORT).show()

        return valor
    }

    fun sacar(view: View){
        if(obterValorSaque() > saldo){
            Toast.makeText(this, "Digite um valor válido!", Toast.LENGTH_SHORT).show()
        } else{

            saldo -= obterValorSaque()
            textSaldo.text = saldo.toString()
        }
    }

    fun depositar(view: View){
        if (obterValorDeposito() in 10.0..3000.0){

            textSaldo.text = saldo.toString()
            saldo += obterValorDeposito()
        } else {
            Toast.makeText(this, "Digite um valor válido!", Toast.LENGTH_SHORT).show()
        }
    }
}
