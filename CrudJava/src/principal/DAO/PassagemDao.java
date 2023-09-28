
package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import principal.Conexao;
import principal.Passagem;

public class PassagemDao {
    private Connection conexao;

    public PassagemDao() throws Exception {
        abrirConexao();
    }

    private void abrirConexao() throws Exception {
        conexao = Conexao.createConnectionToMySQL(); // Certifique-se de que a classe Conexao forneça o método 'createConnectionToMySQL' adequado
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

    public List<Passagem> listarPassagens() {
        List<Passagem> passagens = new ArrayList<>();
        String sql = "SELECT * FROM passagem";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Passagem passagem = new Passagem();
                passagem.setId_passagem(resultado.getString("id_passagem"));
                passagem.setSaindo(resultado.getString("saindo"));
                passagem.setIndo(resultado.getString("indo"));
                passagem.setValor(resultado.getString("valor"));
                passagens.add(passagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return passagens;
    }

    public void criarPassagem(Passagem passagem) {
        String sql = "INSERT INTO passagem (id_passagem, saindo, indo, valor) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, passagem.getId_passagem());
            stmt.setString(2, passagem.getSaindo());
            stmt.setString(3, passagem.getIndo());
            stmt.setString(4, passagem.getValor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarPassagem(Passagem passagem) {
        String sql = "UPDATE passagem SET saindo = ?, indo = ?, valor = ? WHERE id_passagem = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, passagem.getSaindo());
            stmt.setString(2, passagem.getIndo());
            stmt.setString(3, passagem.getValor());
            stmt.setString(4, passagem.getId_passagem());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Passagem buscarPassagemPorId(String id) {
        String sql = "SELECT * FROM passagem WHERE id_passagem = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, id);
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

    public void excluirPassagemPorId(String id) {
        String sql = "DELETE FROM passagem WHERE id_passagem = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
