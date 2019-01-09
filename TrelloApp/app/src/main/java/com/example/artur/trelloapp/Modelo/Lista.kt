package com.example.artur.trelloapp.Modelo

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Lista (var nome: String){
    @Id var id: Long = 0
}