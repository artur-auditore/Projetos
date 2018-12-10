package com.example.artur.helloobjectbox

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detalhes.*
import java.text.DecimalFormat

class DetalhesActivity : AppCompatActivity() {

    object constants{
        const val NOME = "nome"
        const val TBM = "TBM"
    }
    private lateinit var textNome: TextView
    private lateinit var textTBM: TextView
    private lateinit var textProteinasKcal: TextView
    private lateinit var textCarbKcal: TextView
    private lateinit var textGordurasKcal: TextView
    private lateinit var textProteinasG: TextView
    private lateinit var textCarbG: TextView
    private lateinit var textGordurasG: TextView

    var decimalFormat = DecimalFormat("#####.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        setUpViews()
        informacoes()
    }

    @SuppressLint("SetTextI18n")
    private fun informacoes(){

        val nome = intent.getStringExtra(constants.NOME)
        textNome.text = nome
        val tbm = decimalFormat.format(intent.getDoubleExtra(UserInfoActivity.constants.TBM, 0.0))
        textTBM.text = "$tbm kcal"

        textProteinasKcal.text = decimalFormat.format(calculoKcal(tbm.toDouble(), 0.15))
        textCarbKcal.text = decimalFormat.format(calculoKcal(tbm.toDouble(), 0.6))
        textGordurasKcal.text = decimalFormat.format(calculoKcal(tbm.toDouble(), 0.25))

        textProteinasG.text = decimalFormat.format(calculoGramas(calculoKcal(tbm.toDouble(), 0.15), 4))
        textCarbG.text = decimalFormat.format(calculoGramas(calculoKcal(tbm.toDouble(), 0.6), 4))
        textGordurasG.text = decimalFormat.format(calculoGramas(calculoKcal(tbm.toDouble(), 0.25), 9))
    }

    private fun setUpViews() {
        textNome = nome_usuario
        textTBM = text_tbm_tela3

        textProteinasKcal = text_proteinas_kcal
        textCarbKcal = text_carb_kcal
        textGordurasKcal = text_gorduras_kcal

        textProteinasG = text_proteinas_g
        textCarbG = text_carbs_g
        textGordurasG = text_gorduras_g
    }

    private fun calculoKcal(tbm: Double, porcentagem: Double): Double{
        return tbm * porcentagem
    }

    private fun calculoGramas(tbm: Double, valor: Int): Double{
        return tbm/valor
    }
}
