
package dao;

import model.*;

import java.sql.*;

public class EnviadoDAO extends DAOAzure {
    

    public EnviadoDAO() {
        this.Conectar();
     }
  
     public void finalize() {
        this.close();
     }

    private int maxId = 0;


    public int getMaxId() {
        try{
            String sql = "SELECT MAX(id_enviado) as maxId FROM enviado";
            // Para evitar SQL Injection
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();  // Usa executeQuery para comandos SELECT
                if (rs.next()){
                    maxId = rs.getInt("maxId");
                }
                rs.close(); // Fecha o ResultSet
                stmt.close(); //Fecha o Statement
            }
        }catch(SQLException u){
            u.printStackTrace();
        }
        return maxId;
    }

    // Inserir Animal Enviado para tabela enviado
    public boolean inserir(Enviado enviado) {
        boolean status = false;
        try{
            this.maxId = (enviado.getId_enviado() > this.maxId) ? enviado.getId_enviado() : this.maxId;
            String sql = "INSERT INTO enviado (id_enviado, imagem, sexo, especie, raca, idade, porte, temperamento, necessidades_especiais, estado_de_saude, caracteristicas_gerais, localizacao, id_pessoa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, enviado.getId_enviado());
                stmt.setString(2, enviado.getImagem());
                stmt.setString(3, String.valueOf(enviado.getSexo()));
                stmt.setString(4, enviado.getEspecie());
                stmt.setString(5, enviado.getRaca());
                stmt.setString(6, enviado.getIdade());
                stmt.setString(7, String.valueOf(enviado.getPorte()));
                stmt.setString(8, enviado.getTemperamento());
                stmt.setString(9, enviado.getNecessidades_especiais());
                stmt.setString(10, enviado.getEstado_de_saude());
                stmt.setString(11, enviado.getCaracteristicas_gerais());
                stmt.setString(12, enviado.getLocalizacao());
                stmt.setInt(13, enviado.getId_pessoa());
            
                stmt.executeUpdate();
                stmt.close();
                status = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public Enviado get(int id) {
        Enviado enviado = null;
        String sql = "SELECT id_enviado, imagem, sexo, especie, raca, idade, porte, temperamento, necessidades_especiais, estado_de_saude, caracteristicas_gerais, localizacao, id_pessoa FROM enviado WHERE id_pessoa = ?";
    
        try (PreparedStatement stmt = this.conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            stmt.setInt(1, id);  // Corrigido o índice da variável setInt de 2 para 1
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                enviado = new Enviado(
                    rs.getInt("id_enviado"),
                    rs.getString("imagem"),
                    rs.getString("sexo").charAt(0),
                    rs.getString("especie"),
                    rs.getString("raca"),
                    rs.getString("idade"),
                    rs.getString("porte").charAt(0),
                    rs.getString("temperamento"),
                    rs.getString("necessidades_especiais"),
                    rs.getString("estado_de_saude"),
                    rs.getString("caracteristicas_gerais"),
                    rs.getString("localizacao"),
                    rs.getInt("id_pessoa")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enviado;
    }
    

    public Enviado get_FromIdEnviado(int id) {
        Enviado enviado = null;
        String sql = "SELECT id_enviado, imagem, sexo, especie, raca, idade, porte, temperamento, necessidades_especiais, estado_de_saude, caracteristicas_gerais, localizacao, id_pessoa FROM enviado WHERE id_enviado = ?";

        try (PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                enviado = new Enviado(
                    rs.getInt("id_enviado"),
                    rs.getString("imagem"),
                    rs.getString("sexo").charAt(0),
                    rs.getString("especie"),
                    rs.getString("raca"),
                    rs.getString("idade"),
                    rs.getString("porte").charAt(0),
                    rs.getString("temperamento"),
                    rs.getString("necessidades_especiais"),
                    rs.getString("estado_de_saude"),
                    rs.getString("caracteristicas_gerais"),
                    rs.getString("localizacao"),
                    rs.getInt("id_pessoa")
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return enviado;
    }


    public Enviado[] getAll() {
        Enviado[] enviados = null;
        try{
            String sql = "SELECT id_enviado, imagem, sexo, especie, raca, idade, porte, temperamento, necessidades_especiais, estado_de_saude, caracteristicas_gerais, localizacao, id_pessoa FROM enviado";
            
            // Modificando o PreparedStatement para usar um ResultSet rolável
            PreparedStatement stmt = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                rs.last();
                enviados = new Enviado[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                enviados[i] = new Enviado(
                    rs.getInt("id_enviado"),
                    rs.getString("imagem"),
                    rs.getString("sexo").charAt(0),
                    rs.getString("especie"),
                    rs.getString("raca"),
                    rs.getString("idade"),
                    rs.getString("porte").charAt(0),
                    rs.getString("temperamento"),
                    rs.getString("necessidades_especiais"),
                    rs.getString("estado_de_saude"),
                    rs.getString("caracteristicas_gerais"),
                    rs.getString("localizacao"),
                    rs.getInt("id_pessoa")
                );
            }
    
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return enviados;
    }

    
    public boolean atualizar(Enviado enviado) {
        boolean status = false;
        try{
           String sql = "UPDATE enviado SET imagem=?, sexo=?, especie=?, raca=?, idade=?, porte=?, temperamento=?, necessidades_especiais=?, estado_de_saude=?, caracteristicas_gerais=?, localizacao=? WHERE id_pessoa = ?";

           try (PreparedStatement stmt = conexao.prepareStatement(sql)) {;
                stmt.setString(1, enviado.getImagem());
                stmt.setString(2, String.valueOf(enviado.getSexo()));
                stmt.setString(3, enviado.getEspecie());
                stmt.setString(4, enviado.getRaca());
                stmt.setString(5, enviado.getIdade());
                stmt.setString(6, String.valueOf(enviado.getPorte()));
                stmt.setString(7, enviado.getTemperamento());
                stmt.setString(8, enviado.getNecessidades_especiais());
                stmt.setString(9, enviado.getEstado_de_saude());
                stmt.setString(10, enviado.getCaracteristicas_gerais());
                stmt.setInt(11, enviado.getId_pessoa());

                stmt.executeUpdate();
                stmt.close();
           }

            status = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public boolean excluir(int id) {
        boolean status = false;
        String sql = "DELETE FROM enviado WHERE id_enviado = ?";

        try (PreparedStatement stmt = this.conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            status = affectedRows > 0; //se conseguir deletar retorna true
        } catch (SQLException e) {
            System.err.println("Erro ao deletar Enviado com ID: " + id);
            e.printStackTrace();
        }
        return status;
    }

}
