package dao;

import model.*;

import java.sql.*;
import java.util.*;

public class TagsAnimalDAO extends DAOAzure {
    
    public TagsAnimalDAO() {
        this.Conectar();
     }
  
     public void finalize() {
        this.close();
     }

    private int maxId = 0;

    //Retorna o id da ultima TagAnimal inserida no banco de dados
    public int getMaxId() {
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id_taganimal) as maxId FROM tagsanimal"; // Alias para facilitar a leitura do valor
    
            ResultSet rs = st.executeQuery(sql); // Usa executeQuery para comandos SELECT
            if (rs.next()) { // Verifica se o resultado contém ao menos uma linha
                maxId = rs.getInt("maxId"); // Obtem o valor usando o alias
            }

            rs.close(); // Fecha o ResultSet
            st.close(); // Fecha o Statement
        } catch (SQLException u) {
            u.printStackTrace();
        }
        
        return maxId; // Retorna o valor de maxId
    }



    //Inseri as Tags do animal no banco de dado
    public boolean inserirTagAnimal(TagsAnimal tagsAnimal) {
        boolean status = false;
    
        System.out.println(tagsAnimal);
    
        String sql = "INSERT INTO tagsanimal (id_tagAnimal, conteudo_tag, id_animal) VALUES (?, ?, ?)";
    
        try {
            // Atualiza maxId se necessário
            this.maxId = (tagsAnimal.getId_tagAnimal() > this.maxId) ? tagsAnimal.getId_tagAnimal() : this.maxId;
    
            int cont = 0;
            // Usando PreparedStatement
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                while (tagsAnimal.getConteudo_tag().size() > 0) {
                    pstmt.setInt(1, tagsAnimal.getId_tagAnimal() + cont++); // id_tagAnimal
                    pstmt.setString(2, tagsAnimal.getConteudo_tag().remove(0)); // Remove e obtém a primeira tag
                    pstmt.setInt(3, tagsAnimal.getId_animal()); // id_animal
    
                    // Executa a inserção
                    pstmt.executeUpdate();
                }
            }
    
            status = true;
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    }
    

    //Atualiza as Tags do animal no banco de dado 
    public boolean atualizarTagAnimal(TagsAnimal tagsAnimal) {
        boolean status = false;
    
        System.out.println("Atualizando tagsAnimal: " + tagsAnimal);
    
        String sql = "UPDATE tagsanimal SET conteudo_tag = ? WHERE id_tagAnimal = ? AND id_animal = ?";
    
        try {
            int cont = 0;

            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                while (tagsAnimal.getConteudo_tag().size() > 0) {
                    pstmt.setString(1, tagsAnimal.getConteudo_tag().remove(0)); // Atualiza o conteúdo da tag
                    pstmt.setInt(2, tagsAnimal.getId_tagAnimal() + cont++); // id_tagAnimal (ajusta o ID da tag)
                    pstmt.setInt(3, tagsAnimal.getId_animal()); // id_animal
    
                    // Executa a atualização
                    pstmt.executeUpdate();
                }
            }
    
            status = true;
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    }

    //Exclui as Tags pertencente ao id informado
    public boolean excluirTagAnimal(int id_animal){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM tagsanimal WHERE id_animal = " + id_animal);
            st.close();
            status = true;
        } catch(SQLException u){
            throw new RuntimeException(u);
        }
        return status;
    }


    //Retorna as tags do animal pertencente ao id informado
    public TagsAnimal get(int id_animal) {
        TagsAnimal tagsAnimal = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM tagsanimal WHERE id_animal = " + id_animal;
            ResultSet rs = st.executeQuery(sql);

            ArrayList<String> conteudo_tag = new ArrayList<>();

            // Verifica se há resultados
            if (rs.next()) {
                int idTagAnimal = rs.getInt("id_taganimal");

                do {
                    conteudo_tag.add(rs.getString("conteudo_tag")); 
                } while (rs.next()); 

                // Cria o objeto TagsAnimal com a primeira id_tagAnimal capturada e as tags
                tagsAnimal = new TagsAnimal(
                    idTagAnimal,
                    conteudo_tag,
                    id_animal
                );
            }

            st.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tagsAnimal;
    }


    //Retorna todos as Tags de Animais presentes no banco de dados
    // public TagsAnimal[] getTagsAnimal() {
    //     TagsAnimal[] tagsAnimal = null;
    //     try {
    //         Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    //         ResultSet rs = st.executeQuery("SELECT * FROM tagsanimal");
    
    //         if (rs.next()) {
    //             rs.last();
    //             tagsAnimal = new TagsAnimal[rs.getRow()];
    //             rs.beforeFirst();
    //         }
    //         for (int i = 0; rs.next(); i++) {
    //             tagsAnimal[i] = new TagsAnimal(
    //                 rs.getInt("id_tagAnimal"), 
    //                 rs.getString("conteudo_tag"), 
    //                 rs.getInt("id_animal")  
    //             );
    //         }
    
    //     } catch (Exception e) {
    //         System.err.println(e.getMessage());
    //     }
    //     return tagsAnimal;
    // }


}