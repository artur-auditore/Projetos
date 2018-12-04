package com.example.artur.hellosorteiox

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_nome.view.*

class NomeAdapter(private val nomes: List<String>,
                  private val context: Context): Adapter<NomeAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(nome: String){
            val name = itemView.item_nome
            name.text = nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nome, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nomes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nome = nomes[position]
        holder.bind(nome)
    }

}

