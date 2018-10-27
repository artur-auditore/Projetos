package Model

class Eleitor{
    var nome = ""
    var titulo = ""
    var voto = false

    fun votar(): Boolean{
        this.voto = true
        return this.voto
    }
}