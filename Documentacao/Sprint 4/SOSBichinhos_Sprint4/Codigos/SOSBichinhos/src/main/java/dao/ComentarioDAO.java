package dao;

import model.*;
import java.sql.*;

public class ComentarioDAO extends DAOAzure {
    public ComentarioDAO() {
        this.Conectar();
    }

    public void finalize() {
        this.close();
    }

    private int maxId = 0;

    ///////////////////////////////////[GET_MAX_ID]///////////////////////////////////

    public int getMaxId() {
        String sql = "SELECT MAX(id_comentario) as maxId FROM comentario";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("maxId");
            }
            rs.close();
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return maxId;
    }

    ///////////////////////////////////[INSERT]///////////////////////////////////

    public boolean inserirComentario(Comentario comentario) {
        boolean status = false;
        String sql = "INSERT INTO comentario (id_comentario, conteudo, id_animal, id_pessoa) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, comentario.getId());
            ps.setString(2, comentario.getConteudo());
            ps.setInt(3, comentario.getIdAnimal());
            ps.setInt(4, comentario.getIdPessoa());
            ps.executeUpdate();
            status = true;
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    }

    ///////////////////////////////////[UPDATE]///////////////////////////////////

    public boolean atualizarComentario(Comentario comentario) {
        boolean status = false;
        String sql = "UPDATE comentario SET conteudo = ?, id_animal = ?, id_pessoa = ? WHERE id_comentario = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, comentario.getConteudo());
            ps.setInt(2, comentario.getIdAnimal());
            ps.setInt(3, comentario.getIdPessoa());
            ps.setInt(4, comentario.getId());
            ps.executeUpdate();
            status = true;
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    }

    ///////////////////////////////////[DELETE]///////////////////////////////////

    public boolean excluirComentario(int id) {
        boolean status = false;
        String sql = "DELETE FROM comentario WHERE id_comentario = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    ///////////////////////////////////[GET]///////////////////////////////////

    public Comentario get(int id) {
        Comentario comentario = null;
        String sql = "SELECT * FROM comentario WHERE id_comentario = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comentario = new Comentario(
                    rs.getInt("id_comentario"),
                    rs.getString("conteudo"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa")
                );
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return comentario;
    }

    ///////////////////////////////////[GET_ALL]///////////////////////////////////

    public Comentario[] getComentario() {
        Comentario[] comentarios = null;
        String sql = "SELECT * FROM comentario";
        try (PreparedStatement ps = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.last();
                comentarios = new Comentario[rs.getRow()];
                rs.beforeFirst();
            }
            for (int i = 0; rs.next(); i++) {
                comentarios[i] = new Comentario(
                    rs.getInt("id_comentario"),
                    rs.getString("conteudo"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa")
                );
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return comentarios;
    }

    ///////////////////////////////////[GET_ANIMAL_COMMENT]///////////////////////////////////

    public Comentario[] getAnimal(int id) {
        Comentario[] comentarios = null;
        String sql = "SELECT * FROM comentario WHERE id_animal = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.last();
                comentarios = new Comentario[rs.getRow()];
                rs.beforeFirst();
            }
            for (int i = 0; rs.next(); i++) {
                comentarios[i] = new Comentario(
                    rs.getInt("id_comentario"),
                    rs.getString("conteudo"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa")
                );
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar comentários: " + e.getMessage());
        }
        return comentarios;
    }
}
