package com.example.artur.appbanco

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.artur.appbanco.dal.ObjectBox
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_cadastro.*
import java.util.*

class CadastroActivity : AppCompatActivity() {

    companion object {
        const val NUMERO_CONTA = "numeroConta"
        const val NOME_USUARIO = "nome"
    }

    private lateinit var editNome: EditText
    private lateinit var editCPF: EditText
    private lateinit var editSenha: EditText
    private lateinit var editConfirmSenha: EditText

    private lateinit var conta: Conta
    private lateinit var contasBox: Box<Conta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setUpViews()
    }

    private fun setUpViews() {
        editNome = edit_cad_nome
        editCPF = edit_cad_cpf
        editSenha = edit_cad_senha
        editConfirmSenha = edit_cad_confirm_senha

        contasBox = ObjectBox.boxStore.boxFor(Conta::class.java)
        conta = Conta("", "", "")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {R.id.op_concluir -> concluir()}
        return super.onOptionsItemSelected(item)
    }

    private fun concluir(){
        val nome = editNome.text.toString()
        val cpf = editCPF.text.toString()
        val senha = editSenha.text.toString()
        val confirmSenha = editConfirmSenha.text.toString()

        if (senha != confirmSenha){
            Toast.makeText(this, "As senhas devem ser iguais", Toast.LENGTH_SHORT).show()
        } else {
            conta = Conta(nome, cpf, senha)
            conta.numero = gerarNumeroConta()

            val intent = Intent()
            intent.putExtra(NUMERO_CONTA, conta.numero)
            intent.putExtra(NOME_USUARIO, conta.titular)
            setResult(Activity.RESULT_OK, intent)
            contasBox.put(conta)
            finish()
        }

    }

    private fun gerarNumeroConta(): Int{
        val ramdom = Random()
        return ramdom.nextInt(9999 - 1111)
    }

    private fun alertNumeroConta(){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Número da conta")
        alertDialog.setMessage("${conta.titular} o número da sua conta é ${conta.numero}")
        alertDialog.setNeutralButton("OK"){ _, _ ->

        }
        alertDialog.create().show()
    }
}
