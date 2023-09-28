package principal;

import java.util.List;
import java.util.Scanner;
import principal.DAO.ClienteDao;
import principal.DAO.CompraDao;
import principal.DAO.PassagemDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ClienteDao clienteDao = new ClienteDao();
        PassagemDao passagemDao = new PassagemDao();
        CompraDao compraDao = new CompraDao();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Lista de Clientes");
            System.out.println("2. Criar Cliente");
            System.out.println("3. Atualizar Dados do Cliente");
            System.out.println("4. Dados do Cliente");
            System.out.println("5. Excluir Cliente");
            System.out.println("6. Lista de Passagens");
            System.out.println("7. Criar Passagem");
            System.out.println("8. Atualizar Passagem");
            System.out.println("9. Dados da Passagem");
            System.out.println("10. Excluir Passagem");
            System.out.println("11. Datas reservadas");
            System.out.println("12. Criar Compra");
            System.out.println("13. Atualizar Compra");
            System.out.println("14. Dados da Compra");
            System.out.println("15. Excluir Compra");
            System.out.println("16. Sair");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (escolha) {
                case 1:
                    listarClientes(clienteDao);
                    break;
                case 2:
                    criarCliente(scanner, clienteDao);
                    break;
                case 3:
                    atualizarCliente(scanner, clienteDao);
                    break;
                case 4:
                    buscarClientePorId(scanner, clienteDao);
                    break;
                case 5:
                    excluirCliente(scanner, clienteDao);
                    break;
                case 6:
                    listarPassagens(passagemDao);
                    break;
                case 7:
                    criarPassagem(scanner, passagemDao);
                    break;
                case 8:
                    atualizarPassagem(scanner, passagemDao);
                    break;
                case 9:
                    buscarPassagemPorId(scanner, passagemDao);
                    break;
                case 10:
                    excluirPassagem(scanner, passagemDao);
                    break;
                case 11:
                    listarCompras(compraDao);
                    break;
                case 12:
                    criarCompra(scanner, compraDao, clienteDao, passagemDao);
                    break;
                case 13:
                    atualizarCompra(scanner, compraDao, clienteDao, passagemDao);
                    break;
                case 14:
                    buscarCompraPorId(scanner, compraDao, clienteDao, passagemDao);
                    break;
                case 15:
                    excluirCompra(scanner, compraDao);
                    break;
                case 16:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Métodos para operações com Clientes (listar, criar, atualizar, buscar, excluir)
    
    private static void listarClientes(ClienteDao clienteDao) {
        List<Cliente> clientes = clienteDao.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println("Id: " + cliente.getId_Cliente() + " - " + cliente.getNome());
            }
        }
    }

    private static void criarCliente(Scanner scanner, ClienteDao clienteDao) {
        Cliente novoCliente = new Cliente();
        System.out.print("ID do Cliente: ");
        novoCliente.setId_Cliente(scanner.nextLine());
        System.out.print("Nome do Cliente: ");
        novoCliente.setNome(scanner.nextLine());
        System.out.print("Email do Cliente: ");
        novoCliente.setEmail(scanner.nextLine());
        System.out.print("Senha do Cliente: ");
        novoCliente.setSenha(scanner.nextLine());

        clienteDao.criarCliente(novoCliente);
        System.out.println("Cliente criado com sucesso!");
    }

    private static void atualizarCliente(Scanner scanner, ClienteDao clienteDao) {
        System.out.print("Digite o ID do Cliente a ser atualizado: ");
        String idCliente = scanner.nextLine();
        Cliente clienteExistente = clienteDao.buscarClientePorId(idCliente);

        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.print("Novo Nome do Cliente: ");
            clienteExistente.setNome(scanner.nextLine());
            System.out.print("Novo Email do Cliente: ");
            clienteExistente.setEmail(scanner.nextLine());
            System.out.print("Nova Senha do Cliente: ");
            clienteExistente.setSenha(scanner.nextLine());

            clienteDao.atualizarCliente(clienteExistente);
            System.out.println("Cliente atualizado com sucesso!");
        }
    }

    private static void buscarClientePorId(Scanner scanner, ClienteDao clienteDao) {
        System.out.print("Digite o ID do Cliente: ");
        String idCliente = scanner.nextLine();
        Cliente cliente = clienteDao.buscarClientePorId(idCliente);

        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            System.out.println("Cliente encontrado:");
            System.out.println("ID: " + cliente.getId_Cliente());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Senha: " + cliente.getSenha());
        }
    }

    private static void excluirCliente(Scanner scanner, ClienteDao clienteDao) {
        System.out.print("Digite o ID do Cliente a ser excluído: ");
        String idCliente = scanner.nextLine();
        Cliente clienteExistente = clienteDao.buscarClientePorId(idCliente);

        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado.");
        } else {
            clienteDao.excluirClientePorId(idCliente);
            System.out.println("Cliente excluído com sucesso!");
        }
    }

    // Métodos para operações com Passagens (listar, criar, atualizar, buscar, excluir)
    
    private static void listarPassagens(PassagemDao passagemDao) {
        List<Passagem> passagens = passagemDao.listarPassagens();
        if (passagens.isEmpty()) {
            System.out.println("Não há passagens cadastradas.");
        } else {
            System.out.println("Lista de Passagens:");
            for (Passagem passagem : passagens) {
                System.out.println("Id: " + passagem.getId_passagem() + " - " + passagem.getSaindo());
            }
        }
    }

    private static void criarPassagem(Scanner scanner, PassagemDao passagemDao) {
        Passagem novaPassagem = new Passagem();
        System.out.print("ID da Passagem: ");
        novaPassagem.setId_passagem(scanner.nextLine());
        System.out.print("Local de Saída: ");
        novaPassagem.setSaindo(scanner.nextLine());
        System.out.print("Local de Destino: ");
        novaPassagem.setIndo(scanner.nextLine());
        System.out.print("Valor da Passagem: ");
        novaPassagem.setValor(scanner.nextLine());

        passagemDao.criarPassagem(novaPassagem);
        System.out.println("Passagem criada com sucesso!");
    }

    private static void atualizarPassagem(Scanner scanner, PassagemDao passagemDao) {
        System.out.print("Digite o ID da Passagem a ser atualizada: ");
        String idPassagem = scanner.nextLine();
        Passagem passagemExistente = passagemDao.buscarPassagemPorId(idPassagem);

        if (passagemExistente == null) {
            System.out.println("Passagem não encontrada.");
        } else {
            System.out.print("Novo Local de Saída: ");
            passagemExistente.setSaindo(scanner.nextLine());
            System.out.print("Novo Local de Destino: ");
            passagemExistente.setIndo(scanner.nextLine());
            System.out.print("Novo Valor da Passagem: ");
            passagemExistente.setValor(scanner.nextLine());

            passagemDao.atualizarPassagem(passagemExistente);
            System.out.println("Passagem atualizada com sucesso!");
        }
    }

    private static void buscarPassagemPorId(Scanner scanner, PassagemDao passagemDao) {
        System.out.print("Digite o ID da Passagem: ");
        String idPassagem = scanner.nextLine();
        Passagem passagem = passagemDao.buscarPassagemPorId(idPassagem);

        if (passagem == null) {
            System.out.println("Passagem não encontrada.");
        } else {
            System.out.println("Passagem encontrada:");
            System.out.println("ID: " + passagem.getId_passagem());
            System.out.println("Local de Saída: " + passagem.getSaindo());
            System.out.println("Local de Destino: " + passagem.getIndo());
            System.out.println("Valor: " + passagem.getValor());
        }
    }

    private static void excluirPassagem(Scanner scanner, PassagemDao passagemDao) {
        System.out.print("Digite o ID da Passagem a ser excluída: ");
        String idPassagem = scanner.nextLine();
        Passagem passagemExistente = passagemDao.buscarPassagemPorId(idPassagem);

        if (passagemExistente == null) {
            System.out.println("Passagem não encontrada.");
        } else {
            passagemDao.excluirPassagemPorId(idPassagem);
            System.out.println("Passagem excluída com sucesso!");
        }
    }

    // Métodos para operações com Compras (listar, criar, atualizar, buscar, excluir)
    
    private static void listarCompras(CompraDao compraDao) {
        List<Compra> compras = compraDao.listarCompras();
        if (compras.isEmpty()) {
            System.out.println("Não há compras cadastradas.");
        } else {
            System.out.println("Lista de Compras:");
            for (Compra compra : compras) {
                System.out.println("Id: " + compra.getId_compra() + " - Data: " + compra.getData());
            }
        }
    }

    private static void criarCompra(Scanner scanner, CompraDao compraDao, ClienteDao clienteDao, PassagemDao passagemDao) {
        Compra novaCompra = new Compra();
        System.out.print("ID da Compra: ");
        novaCompra.setId_compra(scanner.nextLine());
        System.out.print("Data da Compra: ");
        novaCompra.setData(scanner.nextLine());

        // Solicitar o ID do Cliente
        System.out.print("ID do Cliente: ");
        String idCliente = scanner.nextLine();
        Cliente cliente = clienteDao.buscarClientePorId(idCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        novaCompra.setCliente(cliente);

        // Solicitar o ID da Passagem
        System.out.print("ID da Passagem: ");
        String idPassagem = scanner.nextLine();
        Passagem passagem = passagemDao.buscarPassagemPorId(idPassagem);
        if (passagem == null) {
            System.out.println("Passagem não encontrada.");
            return;
        }
        novaCompra.setPassagem(passagem);

        compraDao.criarCompra(novaCompra);
        System.out.println("Compra criada com sucesso!");
    }

    private static void atualizarCompra(Scanner scanner, CompraDao compraDao, ClienteDao clienteDao, PassagemDao passagemDao) {
        System.out.print("Digite o ID da Compra a ser atualizada: ");
        String idCompra = scanner.nextLine();
        Compra compraExistente = compraDao.buscarCompraPorId(idCompra);

        if (compraExistente == null) {
            System.out.println("Compra não encontrada.");
        } else {
            System.out.print("Nova Data da Compra: ");
            compraExistente.setData(scanner.nextLine());

            // Solicitar o ID do Cliente
            System.out.print("ID do Cliente: ");
            String idCliente = scanner.nextLine();
            Cliente cliente = clienteDao.buscarClientePorId(idCliente);
            if (cliente == null) {
                System.out.println("Cliente não encontrado.");
                return;
            }
            compraExistente.setCliente(cliente);

            // Solicitar o ID da Passagem
            System.out.print("ID da Passagem: ");
            String idPassagem = scanner.nextLine();
            Passagem passagem = passagemDao.buscarPassagemPorId(idPassagem);
            if (passagem == null) {
                System.out.println("Passagem não encontrada.");
                return;
            }
            compraExistente.setPassagem(passagem);

            compraDao.atualizarCompra(compraExistente);
            System.out.println("Compra atualizada com sucesso!");
        }
    }

    private static void buscarCompraPorId(Scanner scanner, CompraDao compraDao, ClienteDao clienteDao, PassagemDao passagemDao) {
        System.out.print("Digite o ID da Compra: ");
        String idCompra = scanner.nextLine();
        Compra compra = compraDao.buscarCompraPorId(idCompra);

        if (compra == null) {
            System.out.println("Compra não encontrada.");
        } else {
            System.out.println("Compra encontrada:");
            System.out.println("ID: " + compra.getId_compra());
            System.out.println("Data: " + compra.getData());
            System.out.println("Cliente: " + compra.getCliente().getNome());
            System.out.println("Passagem: " + compra.getPassagem().getSaindo() + " - " + compra.getPassagem().getIndo());
            System.out.println("Valor da Passagem: " + compra.getPassagem().getValor());
        }
    }

    private static void excluirCompra(Scanner scanner, CompraDao compraDao) {
        System.out.print("Digite o ID da Compra a ser excluída: ");
        String idCompra = scanner.nextLine();
        Compra compraExistente = compraDao.buscarCompraPorId(idCompra);

        if (compraExistente == null) {
            System.out.println("Compra não encontrada.");
        } else {
            compraDao.excluirCompraPorId(idCompra);
            System.out.println("Compra excluída com sucesso!");
        }
    }
}