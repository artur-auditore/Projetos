package Model

class Eleitor{
    var nome = ""
    var titulo = ""
    var votar = false

    fun votar(): Boolean{
        this.votar = true
        return this.votar
    }
}