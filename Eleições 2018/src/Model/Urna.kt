package Model

class Urna{

    val candidato1 = Candidato(); val candidato2 = Candidato()
    val eleitores = arrayListOf<Eleitor>()
    var votos = 0
    var votosBrancos = 0
    var votosNulos = 0

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

        when {
            numeroCandidato == candidato1.numero -> candidato1.votos++
            numeroCandidato == candidato2.numero -> candidato2.votos++
            numeroCandidato.trim() == "" -> votosBrancos++
            numeroCandidato == "00" -> votosNulos++
        }
        this.votos++
    }



    fun estatisticas(): String {
        return "Quantidade de votos: " + this.votos  +
                "\n"+ this.candidato1.nome + ": " + this.candidato1.votos +
                "\n" + this.candidato2.nome + ": " + this.candidato2.votos +
                "\nVotos Brancos: " + this.votosBrancos +
                "\nVotos Nulos: " + this.votosNulos +
                "\nVencedor: " + vencedorParcial()
    }

    fun vencedorParcial(): String{
        //Não é 51% dos votos, basta ser maior

        return when {
            this.candidato1.votos > this.candidato2.votos -> this.candidato1.nome
            this.candidato1.votos < this.candidato2.votos -> this.candidato2.nome
            else -> "Ninguém/Empate"
        }
    }

    fun verEleitores(): String {
        var dados = ""
        for (eleitor: Eleitor in eleitores){
            dados += eleitor.nome + " - " + eleitor.titulo + "\n"
        }
        return dados
    }
}