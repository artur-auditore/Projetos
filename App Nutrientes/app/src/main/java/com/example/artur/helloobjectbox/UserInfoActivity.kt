package com.example.artur.helloobjectbox

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.artur.helloobjectbox.Adapter.PessoasAdapter

import kotlinx.android.synthetic.main.activity_user_info.*
import java.text.DecimalFormat

class UserInfoActivity : AppCompatActivity() {

    companion object {
        const val NOME = "nome"
        const val TBM = "tbm"
    }
    private lateinit var textSaudacao: TextView
    private lateinit var textTBM: TextView
    private lateinit var textAltura: TextView
    private lateinit var textPeso: TextView
    private lateinit var textoSelecione: TextView
    private lateinit var nomeAtual: String
    private var tbmAtual: Double = 0.0
    private var result: Double = 0.0
    private lateinit var buttonDetalhes: Button

    private lateinit var botoes: RadioGroup
    var decimalFormat = DecimalFormat("#####.##")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        setUpViews()
        saudacao()
        calcularTBM()
    }

    private fun setUpViews() {
        textSaudacao = text_saudacao
        textAltura = text_info_altura
        textPeso = text_info_peso
        textTBM = text_tbm

        botoes = botoes_radio_group
        textoSelecione = text_selecione
        buttonDetalhes = button_detalhes
    }

    private fun saudacao(){
        nomeAtual = intent.getStringExtra(NOME)
        val saudacao = "Olá $nomeAtual, abaixo estão suas " +
                "informações e sua taxa metabólica basal"
        textSaudacao.text = saudacao
    }

    fun calculoTBM(peso: Double, altura: Double): Double{
        return ((11.3 * peso) + (16 * altura) + 901)
    }

    @SuppressLint("SetTextI18n")
    fun calcularTBM(){

        val peso = intent.getDoubleExtra(PessoasAdapter.PESO, 0.0)
        val altura = intent.getDoubleExtra(PessoasAdapter.ALTURA, 0.0)

        textPeso.text = "Peso: $peso kg"
        textAltura.text = "Altura: $altura m"
        tbmAtual = calculoTBM(peso, altura)
        textTBM.text = "${decimalFormat.format(tbmAtual)} kcal"

    }

    @SuppressLint("SetTextI18n")
    fun btnSelecionado(view: View){
        val checked = (view as RadioButton).isChecked
        var estiloVida = 0.0

        when(view.id){

            R.id.sedentario ->{
                if (checked) estiloVida = 1.0
            }

            R.id.levemente_ativo ->{
                if (checked) estiloVida = 1.11
            }

            R.id.moderadamente_ativo -> {
                if (checked) estiloVida = 1.25
            }
            R.id.muito_ativo ->{
                if (checked) estiloVida = 1.48
            }
        }
        result = tbmAtual * estiloVida
        textTBM.text = "${decimalFormat.format(result)} kcal"
        buttonDetalhes.visibility = View.VISIBLE
    }

    fun detalhes(view: View){
        val intent = Intent(this, DetalhesActivity::class.java)
        intent.putExtra(NOME, nomeAtual)
        intent.putExtra(TBM, result)

        startActivity(intent)
    }
}
