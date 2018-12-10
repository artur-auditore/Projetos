package com.example.artur.helloobjectbox.Model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Usuario (var nome: String,
                    var peso: Double,
                    var altura: Double){
    @Id var id: Long = 0
}

