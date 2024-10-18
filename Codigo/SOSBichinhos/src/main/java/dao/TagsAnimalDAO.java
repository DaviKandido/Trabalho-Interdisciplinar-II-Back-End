package dao;

import model.*;

import java.sql.*;

public class TagsAnimalDAO extends DAO {
    
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

    public int getMaxIdAnimal() {
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id_animal) as maxId FROM animal"; // Alias para facilitar a leitura do valor
    
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
    public boolean inserirTagAnimal(TagsAnimal tagsAnimal){
        boolean status = false;

        try {
            this.maxId = (tagsAnimal.getId_tagAnimal() > this.maxId) ? tagsAnimal.getId_tagAnimal() : this.maxId;
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO tagsanimal (id_tagAnimal, conteudo_tag, id_animal) " +
                           "VALUES (" +
                           tagsAnimal.getId_tagAnimal() + ", '" +
                           tagsAnimal.getConteudo_tag() + "', '" +
                           tagsAnimal.getId_animal() + "')";

            //Executa o update com a variavel String query               
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    } 


    //Atualiza as Tags do animal pertencente ao id do animal
    public boolean atualizarTagAnimal(TagsAnimal tagsAnimal) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE tagsanimal SET " +
                         "conteudo_tag = '" + tagsAnimal.getConteudo_tag() + "', " +
                         "id_taganimal = '" + tagsAnimal.getId_tagAnimal() + "', " +
                         "WHERE id_animal = " + tagsAnimal.getId_animal();

            //Executa o update com a variavel String query               
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
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
        public TagsAnimal[] get(int id_animal) {
            TagsAnimal[] tagsAnimal = null;
            try {
                Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sql = "SELECT * FROM tagsanimal WHERE id_animal = " + id_animal;
                ResultSet rs = st.executeQuery(sql);
      
                if (rs.next()) {
                    rs.last();
                    tagsAnimal = new TagsAnimal[rs.getRow()];
                    rs.beforeFirst();
                }

                for (int i = 0; rs.next(); i++) {
                    tagsAnimal[i] = new TagsAnimal(
                        rs.getInt("id_tagAnimal"), 
                        rs.getString("conteudo_tag"), 
                        rs.getInt("id_animal")  
                    );
                }
        
                st.close();
        
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return tagsAnimal;
        }

    //Retorna todos as Tags de Animais presentes no banco de dados
    public TagsAnimal[] getTagsAnimal() {
        TagsAnimal[] tagsAnimal = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM tagsanimal");
    
            if (rs.next()) {
                rs.last();
                tagsAnimal = new TagsAnimal[rs.getRow()];
                rs.beforeFirst();
            }
            for (int i = 0; rs.next(); i++) {
                tagsAnimal[i] = new TagsAnimal(
                    rs.getInt("id_tagAnimal"), 
                    rs.getString("conteudo_tag"), 
                    rs.getInt("id_animal")  
                );
            }
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return tagsAnimal;
    }


}