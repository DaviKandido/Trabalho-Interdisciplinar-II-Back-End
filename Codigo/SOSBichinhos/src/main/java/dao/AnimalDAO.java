
// Source code is decompiled from a .class file using FernFlower decompiler.
package dao;

import model.*;

import java.sql.*;

public class AnimalDAO extends DAO {
    

    public AnimalDAO() {
        this.Conectar();
     }
  
     public void finalize() {
        this.close();
     }

    private int maxId = 0;

    //Retorna o id do ultimo animal inserido no banco de dados
    public int getMaxId() {
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id) as maxId FROM animal"; // Alias para facilitar a leitura do valor
    
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

    

    
     
    //Inseri um animal no banco de dados
    public boolean inserirAnimal(Animal animal){
        boolean status = false;
        try {
            this.maxId = (animal.getId() > this.maxId) ? animal.getId() : this.maxId;
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO animal (id, url, nome, sexo, idade, raca, vacinas, cadastrado, historia, tags, porte, especie, cidade) " +
                           "VALUES (" +
                           animal.getId() + ", '" +
                           animal.getUrl() + "', '" +
                           animal.getNome() + "', '" +
                           animal.getSexo() + "', '" +
                           animal.getIdade() + "', '" +
                           animal.getRaca() + "', '" +
                           animal.getVacinas() + "', " +
                           (animal.getCadastrado() ? "TRUE" : "FALSE") + ", '" +
                           animal.getHistoria() + "', '" +
                           animal.getTags() + "', '" +
                           animal.getPorte() + "', '" +
                           animal.getEspecie() + "', '" +
                           animal.getCidade() + "')";

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
    public boolean atualizarAnimal(Animal animal) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE animal SET " +
                         "url = '" + animal.getUrl() + "', " +
                         "nome = '" + animal.getNome() + "', " +
                         "sexo = '" + animal.getSexo() + "', " +
                         "idade = '" + animal.getIdade() + "', " +
                         "raca = '" + animal.getRaca() + "', " +
                         "vacinas = '" + animal.getVacinas() + "', " +
                         "cadastrado = " + (animal.getCadastrado() ? "TRUE" : "FALSE") + ", " +
                         "historia = '" + animal.getHistoria() + "', " +
                         "tags = '" + animal.getTags() + "', " +
                         "porte = '" + animal.getPorte() + "', " +
                         "especie = '" + animal.getEspecie() + "', " +
                         "cidade = '" + animal.getCidade() + "' " +
                         "WHERE id = " + animal.getId();

            //Executa o update com a variavel String query               
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
    
    //Exclui o animal pertencente ao id informado
    public boolean excluirAnimal(int id){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM animal WHERE id = " + id);
            st.close();
            status = true;
        } catch(SQLException u){
            throw new RuntimeException(u);
        }
        return status;
    }

    //Retorna o animal pertencente ao id informado
    public Animal get(int id) {
        Animal animal = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM animal WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                animal = new Animal(
                        rs.getInt("id"), 
                        rs.getString("url"), 
                        rs.getString("nome"), 
                        rs.getString("sexo").charAt(0), 
                        rs.getString("idade"), 
                        rs.getString("raca"), 
                        rs.getString("vacinas"), 
                        rs.getString("cidade"),
                        rs.getBoolean("cadastrado"), 
                        rs.getString("historia"), 
                        rs.getString("tags"), 
                        rs.getString("porte").charAt(0), 
                        rs.getString("especie")
                    );
            }
            st.close();
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animal;
    }

    //Retorna todos os animais presentes no banco de dados
    public Animal[] getAnimais() {
        Animal[] animais = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM animal");
    
            if (rs.next()) {
                rs.last();
                animais = new Animal[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                animais[i] = new Animal(
                    rs.getInt("id"), 
                    rs.getString("url"), 
                    rs.getString("nome"), 
                    rs.getString("sexo").charAt(0), 
                    rs.getString("idade"), 
                    rs.getString("raca"), 
                    rs.getString("vacinas"),
                    rs.getString("cidade"), 
                    rs.getBoolean("cadastrado"), 
                    rs.getString("historia"), 
                    rs.getString("tags"), 
                    rs.getString("porte").charAt(0), 
                    rs.getString("especie")
                );
            }
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }

    //Retorna apenas animais machos
    public Animal[] getAnimaisMachos() {
        Animal[] animais = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM animal WHERE animal.sexo LIKE 'M'");
    
            if (rs.next()) {
                rs.last();
                animais = new Animal[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                animais[i] = new Animal(
                    rs.getInt("id"), 
                    rs.getString("url"), 
                    rs.getString("nome"), 
                    rs.getString("sexo").charAt(0), 
                    rs.getString("idade"), 
                    rs.getString("raca"), 
                    rs.getString("vacinas"), 
                    rs.getString("cidade"),
                    rs.getBoolean("cadastrado"), 
                    rs.getString("historia"), 
                    rs.getString("tags"), 
                    rs.getString("porte").charAt(0), 
                    rs.getString("especie")
                );
            }
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }

    //Retorna apenas animais Femeas
    public Animal[] getAnimaisFemeas() {
        Animal[] animais = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM animal WHERE animal.sexo LIKE 'F'");
    
            if (rs.next()) {
                rs.last();
                animais = new Animal[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                animais[i] = new Animal(
                    rs.getInt("id"), 
                    rs.getString("url"), 
                    rs.getString("nome"), 
                    rs.getString("sexo").charAt(0), 
                    rs.getString("idade"), 
                    rs.getString("raca"), 
                    rs.getString("vacinas"), 
                    rs.getString("cidade"),
                    rs.getBoolean("cadastrado"), 
                    rs.getString("historia"), 
                    rs.getString("tags"), 
                    rs.getString("porte").charAt(0), 
                    rs.getString("especie")
                );
            }
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }
    
}
