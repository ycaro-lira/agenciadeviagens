 package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import principal.Cliente;
import principal.Conexao;

public class ClienteDao {
    private Connection conexao;

    public ClienteDao() throws Exception {
        abrirConexao();
    }

    private void abrirConexao() throws Exception {
        conexao = Conexao.createConnectionToMySQL(); // Correção: Deve chamar o método createConnectionToMySQL()
    }

    private void fecharConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_Cliente(resultado.getString("id_Cliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setSenha(resultado.getString("senha"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public void criarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (id_Cliente, nome, email, senha) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getId_Cliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, senha = ? WHERE id_Cliente = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setString(4, cliente.getId_Cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarClientePorId(String id) {
        String sql = "SELECT * FROM cliente WHERE id_Cliente = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_Cliente(resultado.getString("id_Cliente"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setSenha(resultado.getString("senha"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void excluirClientePorId(String id) {
        String sql = "DELETE FROM cliente WHERE id_Cliente = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
