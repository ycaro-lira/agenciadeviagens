Algoritmo "projeto de entrega"

Var
    clientes: vetor[1..3] de caractere
    saida: vetor[1..3] de caractere
    destino: vetor[1..3] de caractere
    consulta: vetor[1..3] de caractere
    decisao: caractere
    i: inteiro

Inicio
    escreval("Olá! Seja bem-vindo à nossa agência de viagens. Como posso ajudar você hoje?")

    repita
        escreval("(1) - Cadastro de cliente")
        escreval("(2) - Cadastro de destino")
        escreval("(3) - Consulta de clientes")
        escreval("(4) - Sair do App")
        leia(decisao)
        limpatela

        escolha decisao
            caso "1"
                para i de 1 ate 3 faca
                    se clientes[i] = "" entao
                        escreval("Digite seu nome e sobrenome: ")
                        leia(clientes[i])
                        escreval("Você foi cadastrado com Sucesso! ", clientes[i])
                        interrompa
                    fimse
                fimpara
                limpatela

            caso "2"
                para i de 1 ate 3 faca
                    se saida[i] = "" entao
                        escreval("Saindo de: ")
                        leia(saida[i])
                        escreval("Indo para: ")
                        leia(destino[i])
                        escreval(clientes[i], ", Você está saindo de: ", saida[i], " para: ", destino[i], " Viagem agendada com sucesso!")
                        interrompa
                    fimse
                fimpara
                limpatela

            caso "3"
                escreval("---- Lista de Clientes ----")
                para i de 1 ate 3 faca
                    se clientes[i] <> "" entao
                        escreval(i, ": ", clientes[i], " - Saindo de: ", saida[i], " - Indo para: ", destino[i])
                        fimse
                        fimpara
                escreval("---------------------------")

                caso "4"
                escreval("Obrigado por usar nosso App! Até logo!")
                interrompa


        fimescolha

    ate decisao = "4"
Fimalgoritmo
