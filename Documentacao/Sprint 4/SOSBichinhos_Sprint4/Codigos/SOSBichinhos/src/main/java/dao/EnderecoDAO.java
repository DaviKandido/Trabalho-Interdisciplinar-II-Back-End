package dao;

import model.Endereco;

import java.sql.*;

public class EnderecoDAO extends DAOAzure {

    public EnderecoDAO() {
        this.Conectar();
    }

    public void finalize() {
        this.close();
    }

    private int maxId_endereco = 0;

    // Retorna o id do último endereço inserido no banco de dados
    public int getMaxId_endereco() {
        String sql = "SELECT MAX(id_endereco) as maxId_endereco FROM endereco";
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                maxId_endereco = rs.getInt("maxId_endereco");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId_endereco;
    }

    // Insere um endereço no banco de dados
    public boolean inserirEndereco(Endereco endereco) {
        boolean status = false;
        String sql = "INSERT INTO endereco (id_endereco, bairro, rua, numero, cidade, estado, id_pessoa) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, endereco.getId_endereco());
            ps.setString(2, endereco.getBairro());
            ps.setString(3, endereco.getRua());
            ps.setString(4, endereco.getNumero());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());
            ps.setInt(7, endereco.getId_pessoa());

            ps.executeUpdate();
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Atualiza um endereço no banco de dados
    public boolean atualizarEndereco(Endereco endereco) {
        boolean status = false;
        String sql = "UPDATE endereco SET bairro = ?, rua = ?, numero = ?, cidade = ?, estado = ?, id_pessoa = ? WHERE id_endereco = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, endereco.getBairro());
            ps.setString(2, endereco.getRua());
            ps.setString(3, endereco.getNumero());
            ps.setString(4, endereco.getCidade());
            ps.setString(5, endereco.getEstado());
            ps.setInt(6, endereco.getId_pessoa());
            ps.setInt(7, endereco.getId_endereco());

            ps.executeUpdate();
            status = true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar endereço: " + e.getMessage());
            e.printStackTrace();
        }
        return status;
    }

    // Exclui o endereço pertencente ao id informado
    public boolean excluirEndereco(int id) {
        boolean status = false;
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
            status = true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir endereço: " + e.getMessage());
            e.printStackTrace();
        }
        return status;
    }

    // Retorna o endereço pertencente ao id informado
    public Endereco get(int id) {
        Endereco endereco = null;
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    endereco = new Endereco(
                        rs.getInt("id_endereco"),
                        rs.getString("bairro"),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getInt("id_pessoa")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar endereço: " + e.getMessage());
            e.printStackTrace();
        }
        return endereco;
    }

    // Retorna todos os endereços presentes no banco de dados
    public Endereco[] getEnderecos() {
        Endereco[] enderecos = null;
        String sql = "SELECT * FROM endereco";

        try (PreparedStatement ps = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                rs.last();
                enderecos = new Endereco[rs.getRow()];
                rs.beforeFirst();
            }

            for (int i = 0; rs.next(); i++) {
                enderecos[i] = new Endereco(
                    rs.getInt("id_endereco"),
                    rs.getString("bairro"),
                    rs.getString("rua"),
                    rs.getString("numero"),
                    rs.getString("cidade"),
                    rs.getString("estado"),
                    rs.getInt("id_pessoa")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar endereços: " + e.getMessage());
            e.printStackTrace();
        }
        return enderecos;
    }
}
