package com.example.artur.trelloapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.artur.trelloapp.Modelo.Quadro
import kotlinx.android.synthetic.main.item_quadro.view.*

class QuadroAdapter(private val quadros: MutableList<Quadro>,
                    private val context: Context): RecyclerView.Adapter<QuadroAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(quadro: Quadro){
            val nome = itemView.text_nome

            nome.text = quadro.nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_quadro, parent, false))
    }

    override fun getItemCount(): Int {
        return quadros.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quadro = quadros[position]
        holder.bind(quadro)
    }
}