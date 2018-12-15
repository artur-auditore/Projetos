package com.example.artur.helloobjectbox.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.artur.helloobjectbox.Model.Usuario
import com.example.artur.helloobjectbox.R
import kotlinx.android.synthetic.main.pessoa_item.view.*
import android.widget.Toast
import com.example.artur.helloobjectbox.CadastroActivity
import com.example.artur.helloobjectbox.UserInfoActivity
import io.objectbox.Box


class PessoasAdapter(private val nomes: MutableList<Usuario>,
                     private val usuariosBox: Box<Usuario>,
                     private val context: Context): RecyclerView.Adapter<PessoasAdapter.ViewHolder>() {

    companion object {
        val NOME = "nome"
        val ALTURA = "altura"
        val PESO = "peso"
        val ID = "usuarioId"
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
            intent.putExtra(NOME, usuario.nome)
            intent.putExtra(PESO, usuario.peso)
            intent.putExtra(ALTURA, usuario.altura)

            context.startActivity(intent)
        }

        popMenu(holder.itemView, usuario, position)
    }

    private fun popMenu(itemView: View, usuario: Usuario, position: Int) {

        itemView.setOnLongClickListener { view ->
            val popup = PopupMenu(context, view)
            popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->

                when (item.itemId) {
                    R.id.editar -> editar(usuario, position)
                    R.id.remover -> remover(usuario, position)
                }

                false
            }

            popup.show()

            true
        }
    }

    private fun editar(usuario: Usuario, position: Int) {
        val intent = Intent(context, CadastroActivity::class.java)
        intent.putExtra(ID, usuario.id)
        context.startActivity(intent)
        notifyItemChanged(position)
    }

    private fun remover(usuario: Usuario, position: Int) {
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Excluir")
        alertDialog.setMessage("Deseja realmente excluir ${usuario.nome}?")

        alertDialog.setPositiveButton("SIM") { _, _ ->

            this.nomes.remove(usuario)
            this.usuariosBox.remove(usuario)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            Toast.makeText(context, "${usuario.nome} apagado", Toast.LENGTH_SHORT).show()
        }
        alertDialog.setNegativeButton("NÃO") { _, _ ->
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
        }
        alertDialog.create().show()
    }
}
