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

    //Inseri um tagAnimal no banco de dado
    public boolean inserirTagAnimal(TagsAnimal tagsAnimal){
        boolean status = false;

        try {
            this.maxId = (animal.getId() > this.maxId) ? animal.getId() : this.maxId;
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


        //Atualiza o animal pertencente ao id do seu objeto
    public boolean atualizarTagAnimal(TagsAnimal tagsAnimal) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE animal SET " +
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



}