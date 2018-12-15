package com.example.artur.helloobjectbox.Model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class Usuario {
    var nome: String = ""
    var peso: Double = 0.0
    var altura: Double = 0.0
    @Id var id: Long = 0
}

