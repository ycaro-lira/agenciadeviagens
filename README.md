# agenciadeviagens
Sistema de Agência de Viagens
Este é um projeto de exemplo para um Sistema de Agência de Viagens, que inclui classes para representar Clientes, Passagens e Compras.

Classes UML
*Cliente
A classe Cliente representa os clientes da agência de viagens. Ela possui os seguintes atributos:

id_Cliente (String): O identificador único do cliente.
nome (String): O nome do cliente.
email (String): O endereço de e-mail do cliente.
senha (String): A senha do cliente.

*Passagem
A classe Passagem representa as passagens disponíveis para compra. Ela possui os seguintes atributos:

id_passagem (String): O identificador único da passagem.
saindo (String): O local de partida da viagem.
indo (String): O destino da viagem.
valor (String): O valor da passagem.

*Compra
A classe Compra representa as compras realizadas pelos clientes. Ela possui os seguintes atributos:

id_compra (String): O identificador único da compra.
data (String): A data da compra.
passagem (Passagem): A passagem associada à compra.
cliente (Cliente): O cliente que fez a compra.
