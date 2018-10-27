package Model

class Urna{

    val candidatos = arrayListOf<Candidato>()
    val eleitores = arrayListOf<Eleitor>()
    var votos = 0

    fun candidatos(){

        for (candidato: Candidato in candidatos){

            this.candidatos[1].nome = "Jair Bolsonaro"
            this.candidatos[1].numero = 17
            this.candidatos[1].partido = "PSL"

            this.candidatos[2].nome = "Ferndando Haddad"
            this.candidatos[2].numero = 13
            this.candidatos[2].partido = "PT"
        }
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

    fun votar(numeroCandidato: Int){
        for (candidato: Candidato in candidatos)
            if (numeroCandidato == candidato.numero)
                candidato.votos++
        this.votos++
    }

    fun estatisticas(): String {
        return "Quantidade de votos: " + votos
    }

    fun verEleitores(): String {
        var dados = ""
        for (eleitor: Eleitor in eleitores){
            dados += eleitor.nome + " - " + eleitor.titulo
        }
        return dados
    }
}