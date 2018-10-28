package Model

class Urna{

    val candidato1 = Candidato(); val candidato2 = Candidato()
    val eleitores = arrayListOf<Eleitor>()
    var votos = 0

    fun candidatos(){

        candidato1.nome = "Jair Bolsonaro"
        candidato1.numero = "17"
        candidato1.partido = "PSL"

        candidato2.nome = "Fernando Haddad"
        candidato2.numero = "13"
        candidato2.partido = "PT"

    }

    fun novoEleitor(nome: String, titulo: String){

        val eleitor = Eleitor()
        eleitor.nome = nome
        eleitor.titulo = titulo
        eleitores.add(eleitor)

    }

    fun check(titulo: String): Boolean {
        for (eleitor: Eleitor in eleitores){
            if (titulo == eleitor.titulo)
                return eleitor.votar()
        }
        return false
    }

    fun votar(numeroCandidato: String){
        if (numeroCandidato == candidato1.numero){
            candidato1.votos++
        } else{
            candidato2.votos++
        }
        this.votos++
    }

    fun estatisticas(): String {
        return "Quantidade de votos: " + this.votos  +
                "\n"+ this.candidato1.nome + ": " + this.candidato1.votos +
                "\n" + this.candidato2.nome + ": " + this.candidato2.votos 
    }

    fun verEleitores(): String {
        var dados = ""
        for (eleitor: Eleitor in eleitores){
            dados += eleitor.nome + " - " + eleitor.titulo
        }
        return dados
    }
}