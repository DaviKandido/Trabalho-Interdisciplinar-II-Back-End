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
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id_endereco) as maxId_endereco FROM endereco"; // Alias para facilitar a leitura do valor

            ResultSet rs = st.executeQuery(sql); // Usa executeQuery para comandos SELECT
            if (rs.next()) { // Verifica se o resultado contém ao menos uma linha
                maxId_endereco = rs.getInt("maxId_endereco"); // Obtem o valor usando o alias
            }

            rs.close(); // Fecha o ResultSet
            st.close(); // Fecha o Statement
        } catch (SQLException u) {
            u.printStackTrace();
        }

        return maxId_endereco; // Retorna o valor de maxId_endereco
    }

    // Insere um endereço no banco de dados
    public boolean inserirEndereco(Endereco endereco) {
        boolean status = false;
        String sql = "INSERT INTO endereco (id_endereco, bairro, rua, numero, cidade, estado, id_pessoa) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, endereco.getId_endereco());
            pstmt.setString(2, endereco.getBairro());
            pstmt.setString(3, endereco.getRua());
            pstmt.setString(4, endereco.getNumero()); 
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getEstado());
            pstmt.setInt(7, endereco.getId_pessoa());
    
            pstmt.executeUpdate();
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    

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
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM endereco WHERE id_endereco = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // Retorna o endereço pertencente ao id informado
    public Endereco get(int id) {
        Endereco endereco = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM endereco WHERE id_endereco = " + id;
            ResultSet rs = st.executeQuery(sql);

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

            rs.close();
            st.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return endereco;
    }

    // Retorna todos os endereços presentes no banco de dados
    public Endereco[] getEnderecos() {
        Endereco[] enderecos = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM endereco");

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

            rs.close();
            st.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return enderecos;
    }
}
