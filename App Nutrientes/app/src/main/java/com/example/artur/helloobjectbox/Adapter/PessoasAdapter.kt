package com.example.artur.helloobjectbox.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.artur.helloobjectbox.Model.Usuario
import com.example.artur.helloobjectbox.R
import kotlinx.android.synthetic.main.pessoa_item.view.*
import android.widget.Toast
import com.example.artur.helloobjectbox.UserInfoActivity
import io.objectbox.Box


class PessoasAdapter(private val nomes: MutableList<Usuario>,
                     private val usuariosBox: Box<Usuario>,
                     private val context: Context): RecyclerView.Adapter<PessoasAdapter.ViewHolder>() {

    object constants{
        val NOME = "nome"
        val ALTURA = "altura"
        val PESO = "peso"
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bind(usuario: Usuario){
            val nome = itemView.text_nome
            val peso = itemView.text_peso
            val altura = itemView.text_altura

            nome.text = usuario.nome
            peso.text = "Peso: ${usuario.peso} kg"
            altura.text = "Altura: ${usuario.altura} m"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pessoa_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nomes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = nomes[position]
        holder.bind(usuario)

        holder.itemView.setOnClickListener { item ->
            val intent = Intent(context, UserInfoActivity::class.java)
            intent.putExtra(constants.NOME, usuario.nome)
            intent.putExtra(constants.PESO, usuario.peso)
            intent.putExtra(constants.ALTURA, usuario.altura)

            context.startActivity(intent)
        }
    }


    //todo Deixar pra depois
    private fun remover(view: View, usuario: Usuario, position: Int): Boolean {
        val alert = AlertDialog.Builder(this.context)

        alert.setTitle("Excluir")
        alert.setMessage("Deseja realmente excluir ${usuario.nome}?")

        alert.setPositiveButton("SIM") { dialog, which ->

            this.nomes.remove(usuario)
            this.usuariosBox.remove(usuario)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            Snackbar.make(view, "${usuario.nome} removido.", Snackbar.LENGTH_SHORT).show()
        }

        alert.setNegativeButton("NÃO") { dialog, which ->
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show() }

        alert.create().show()

        return false
    }
}
