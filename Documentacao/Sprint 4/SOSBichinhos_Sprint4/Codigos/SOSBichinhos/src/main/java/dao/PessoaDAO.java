package dao;

import model.Pessoa;
import java.sql.*;

public class PessoaDAO extends DAOAzure {

    public PessoaDAO() {
        this.Conectar();
    }

    public void finalize() {
        this.close();
    }

    private int maxId_pessoa = 0;

    // Retorna o id da última pessoa inserida no banco de dados
    public int getMaxId_pessoa() {
        String sql = "SELECT MAX(id_pessoa) as maxId_pessoa FROM pessoa";
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                maxId_pessoa = rs.getInt("maxId_pessoa");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxId_pessoa;
    }

    // Insere uma pessoa no banco de dados
    public boolean inserirpessoa(Pessoa pessoa) {
        boolean status = false;
        String sql = "INSERT INTO pessoa (id_pessoa, nome, email, senha, moradia, imagem, idade, sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, pessoa.getId());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getEmail());
            ps.setString(4, CriptografiaAES.criptografar(pessoa.getSenha()));
            ps.setString(5, pessoa.getMoradia());
            ps.setString(6, pessoa.getImagem());
            ps.setInt(7, pessoa.getIdade());
            ps.setString(8, pessoa.getSexo());

            ps.executeUpdate();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Atualiza uma pessoa no banco de dados
    public boolean atualizarpessoa(Pessoa pessoa) {
        boolean status = false;
        String sql = "UPDATE pessoa SET nome = ?, email = ?, senha = ?, moradia = ?, imagem = ?, idade = ?, sexo = ? WHERE id_pessoa = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setString(3, CriptografiaAES.criptografar(pessoa.getSenha()));
            ps.setString(4, pessoa.getMoradia());
            ps.setString(5, pessoa.getImagem());
            ps.setInt(6, pessoa.getIdade());
            ps.setString(7, pessoa.getSexo());
            ps.setInt(8, pessoa.getId());

            ps.executeUpdate();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Exclui uma pessoa com base no ID informado
    public boolean excluirpessoa(int id) {
        boolean status = false;
        String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.executeUpdate();
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    // Retorna o objeto Pessoa correspondente ao ID informado
    public Pessoa get(int id) {
        Pessoa pessoa = null;
        String sql = "SELECT * FROM pessoa WHERE id_pessoa = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String senhaDescriptografada;
                    try {
                        senhaDescriptografada = CriptografiaAES.descriptografar(rs.getString("senha"));
                    } catch (Exception e) {
                        System.err.println("Erro ao descriptografar a senha para ID: " + id);
                        senhaDescriptografada = "";
                    }

                    pessoa = new Pessoa(
                        rs.getInt("id_pessoa"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        senhaDescriptografada,
                        rs.getString("moradia"),
                        rs.getString("imagem"),
                        rs.getInt("idade"),
                        rs.getString("sexo")
                    );
                } else {
                    System.out.println("Nenhuma pessoa encontrada com o ID: " + id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    // Retorna todas as pessoas presentes no banco de dados
    public Pessoa[] getpessoas() {
        Pessoa[] pessoas = null;
        String sql = "SELECT * FROM pessoa";

        try (PreparedStatement ps = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                rs.last();
                int rowCount = rs.getRow();
                pessoas = new Pessoa[rowCount];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    String senhaDescriptografada;
                    try {
                        senhaDescriptografada = CriptografiaAES.descriptografar(rs.getString("senha"));
                    } catch (Exception e) {
                        System.err.println("Erro ao descriptografar a senha para ID: " + rs.getInt("id_pessoa"));
                        senhaDescriptografada = "";
                    }

                    pessoas[i] = new Pessoa(
                        rs.getInt("id_pessoa"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        senhaDescriptografada,
                        rs.getString("moradia"),
                        rs.getString("imagem"),
                        rs.getInt("idade"),
                        rs.getString("sexo")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pessoas;
    }
}
