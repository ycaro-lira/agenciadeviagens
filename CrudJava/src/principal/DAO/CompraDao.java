
package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import principal.Cliente;
import principal.Compra;
import principal.Passagem;
import principal.Conexao;

public class CompraDao {
    private Connection conexao;

    public CompraDao() throws Exception {
        abrirConexao();
    }

    private void abrirConexao() throws Exception {
        conexao = Conexao.createConnectionToMySQL(); 
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

    public List<Compra> listarCompras() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra"; 

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Compra compra = new Compra();
                compra.setId_compra(resultado.getString("id_compra"));
                compra.setData(resultado.getString("data"));

                // Recuperar informações de Passagem e Cliente por ID
                Passagem passagem = recuperarPassagemPorId(resultado.getString("id_passagem"));
                Cliente cliente = recuperarClientePorId(resultado.getString("id_cliente"));

                compra.setPassagem(passagem);
                compra.setCliente(cliente);

                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compras;
    }

    public void criarCompra(Compra novaCompra) {
        String sql = "INSERT INTO compra (id_compra, data, id_passagem, id_cliente) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, novaCompra.getId_compra());
            stmt.setString(2, novaCompra.getData());
            stmt.setString(3, novaCompra.getPassagem().getId_passagem());
            stmt.setString(4, novaCompra.getCliente().getId_Cliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarCompra(Compra compra) {
        String sql = "UPDATE compra SET data = ?, id_passagem = ?, id_cliente = ? WHERE id_compra = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, compra.getData());
            stmt.setString(2, compra.getPassagem().getId_passagem());
            stmt.setString(3, compra.getCliente().getId_Cliente());
            stmt.setString(4, compra.getId_compra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Compra buscarCompraPorId(String idCompra) {
        String sql = "SELECT * FROM compra WHERE id_compra = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, idCompra);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                Compra compra = new Compra();
                compra.setId_compra(resultado.getString("id_compra"));
                compra.setData(resultado.getString("data"));

                // Recuperar informações de Passagem e Cliente por ID
                Passagem passagem = recuperarPassagemPorId(resultado.getString("id_passagem"));
                Cliente cliente = recuperarClientePorId(resultado.getString("id_cliente"));

                compra.setPassagem(passagem);
                compra.setCliente(cliente);

                return compra;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void excluirCompraPorId(String idCompra) {
        String sql = "DELETE FROM compra WHERE id_compra = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, idCompra);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implemente o método para recuperar informações de Passagem por ID
    private Passagem recuperarPassagemPorId(String idPassagem) {
        String sql = "SELECT * FROM passagem WHERE id_passagem = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, idPassagem);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                Passagem passagem = new Passagem();
                passagem.setId_passagem(resultado.getString("id_passagem"));
                passagem.setSaindo(resultado.getString("saindo"));
                passagem.setIndo(resultado.getString("indo"));
                passagem.setValor(resultado.getString("valor"));
                return passagem;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Implemente o método para recuperar informações de Cliente por ID
    private Cliente recuperarClientePorId(String idCliente) {
        String sql = "SELECT * FROM cliente WHERE id_Cliente = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, idCliente);
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
}
