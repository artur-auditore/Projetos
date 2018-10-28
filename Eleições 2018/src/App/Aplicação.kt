package App

import Model.Urna

fun main(args: Array<String>) {

    val urna = Urna()
    urna.candidatos()

    val menu = "**** Eleições 2018 ****" +
            "\n1 - Novo Eleitor" +
            "\n2 - Votar" +
            "\n3 - Estatísticas" +
            "\n4 - Ver eleitores" +
            "\n0 - Sair"

    println(menu)
    loop@ while (true){
        print("Opção: ")
        val opcao = readLine()!!.toString()

        when(opcao){

            "1" ->{
                print("Nome: ")
                val nome = readLine()!!.toString()
                print("Numero do Titulo: ")
                val titulo = readLine()!!.toString()

                if(nome.trim() == "" || titulo.trim() == "") {
                    println("Erro. Digite um nome ou título válido!")
                } else{
                    urna.novoEleitor(nome, titulo)
                    println("Criado.")
                }
            }
            "2" ->{
                print("Número do Título: ")
                val numeroTitulo = readLine()!!.toString()

                if(urna.check(numeroTitulo)){

                    print("Número do Candidato: ")
                    val numeroCandidato = readLine()!!.toString()
                    urna.votar(numeroCandidato)
                    println("FIM!")

                } else {
                    println("Digite um título válido!")
                }

            }
            "3" ->{
                println(urna.estatisticas())
            }
            "4" ->{
                println(urna.verEleitores())
            }
            "0" ->{
                print("Encerrado.")
                break@loop
            }
            else ->{
                println("Digite uma opção válida!")
            }
        }
    }
}