package com.example.artur.appbanco

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Conta (var titular: String,
                  var cpf: String,
                  var senha: String) {
    var saldo: Double = 0.0
    var numero: Int = 0
    @Id var id: Long = 0
}