
// Source code is decompiled from a .class file using FernFlower decompiler.
package dao;

import model.*;

import java.sql.*;

public class AnimalDAO extends DAOAzure {
    

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
            String sql = "SELECT MAX(id_animal) as maxId FROM animal"; // Alias para facilitar a leitura do valor
            try(PreparedStatement st = conexao.prepareStatement(sql)){
                ResultSet rs = st.executeQuery(); // Usa executeQuery para comandos SELECT
                if (rs.next()) { // Verifica se o resultado contém ao menos uma linha
                    maxId = rs.getInt("maxId"); // Obtem o valor usando o alias
                }

                rs.close(); // Fecha o ResultSet
                st.close(); // Fecha o Statement
            }
        } catch (SQLException u) {
            u.printStackTrace();
        }
        
        return maxId; // Retorna o valor de maxId
    }

    
     
    //Inseri um animal no banco de dados
    public boolean inserirAnimal(Animal animal){
        boolean status = false;

        System.out.println(animal);

        try {
            this.maxId = (animal.getId() > this.maxId) ? animal.getId() : this.maxId;
            String sql = "INSERT INTO animal (id_animal, imagem, nome, sexo, idade, raca, vacinas, castrado, historia, porte, especie, adotado) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try(PreparedStatement st = conexao.prepareStatement(sql)){
                st.setInt(1, animal.getId());
                st.setString(2, animal.getImagem());
                st.setString(3, animal.getNome());
                st.setString(4, String.valueOf(animal.getSexo()));
                st.setString(5, animal.getIdade());
                st.setString(6, animal.getRaca());
                st.setString(7, animal.getVacinas());
                st.setBoolean(8, animal.getCastrado());
                st.setString(9, animal.getHistoria());
                st.setString(10, String.valueOf(animal.getPorte()));
                st.setString(11, animal.getEspecie());
                st.setBoolean(12, animal.getAdotado());

                //Executa o update com a variável String sql               
                st.executeUpdate();
                st.close();
                status = true;
            }
            
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    }   

    //Atualiza o animal pertencente ao id do seu objeto
    public boolean atualizarAnimal(Animal animal) {

        System.out.println("Atualizando animal: " + animal);

        boolean status = false;
        try {
            String sql = "UPDATE animal SET imagem=?, nome=?, sexo=?, idade=?, raca=?, vacinas=?, castrado=?, historia=?, porte=?, especie=?, adotado=? WHERE id_animal=?";

            try(PreparedStatement st = conexao.prepareStatement(sql)){
                st.setString(1, animal.getImagem());
                st.setString(2, animal.getNome());
                st.setString(3, String.valueOf(animal.getSexo()));
                st.setString(4, animal.getIdade());
                st.setString(5, animal.getRaca());
                st.setString(6, animal.getVacinas());
                st.setBoolean(7, animal.getCastrado());
                st.setString(8, animal.getHistoria());
                st.setString(9, String.valueOf(animal.getPorte()));
                st.setString(10, animal.getEspecie());
                st.setBoolean(11, animal.getAdotado());
                st.setInt(12, animal.getId());
                
                //Executa o update com a variável String query               
                st.executeUpdate();
                st.close();
                status = true;
            }
            

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
    
    //Exclui o animal pertencente ao id informado
    public boolean excluirAnimal(int id_animal){
        boolean status = false;
        String sql = "DELETE FROM animal WHERE id_animal = ?";
        try(PreparedStatement st = conexao.prepareStatement(sql)){
            st.setInt(1, id_animal);
            st.executeUpdate();

            int affectedRows = st.executeUpdate();
            status = affectedRows > 0; //se conseguir deletar retorna true
            st.close();
        } catch(SQLException u){
            System.err.println("Erro ao deletar animal com ID: " + id_animal);
            throw new RuntimeException(u);
        }
        return status;
    }

    //Retorna o animal pertencente ao id informado
    public Animal get(int id_animal) {
        Animal animal = null;
        String sql = "SELECT id_animal, imagem, nome, sexo, idade, raca, vacinas, castrado, historia, porte, especie, adotado FROM animal WHERE id_animal = ?";
    
        try(PreparedStatement st = this.conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

            st.setInt(1, id_animal);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                animal = new Animal(
                        rs.getInt("id_animal"), 
                        rs.getString("imagem"), 
                        rs.getString("nome"), 
                        rs.getString("sexo").charAt(0), 
                        rs.getString("idade"), 
                        rs.getString("raca"), 
                        rs.getString("vacinas"), 
                        rs.getBoolean("castrado"), 
                        rs.getString("historia"), 
                        rs.getString("porte").charAt(0), 
                        rs.getString("especie"),
                        rs.getBoolean("adotado")
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
        Animal animais[] = null;
        String sql = "SELECT id_animal, imagem, nome, sexo, idade, raca, vacinas, castrado, historia, porte, especie, adotado FROM animal";
    
        // Modificando o PreparedStatement para usar um ResultSet rolável
        try(PreparedStatement st = this.conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                rs.last();
                animais = new Animal[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                animais[i] = new Animal(
                    rs.getInt("id_animal"), 
                    rs.getString("imagem"), 
                    rs.getString("nome"), 
                    rs.getString("sexo").charAt(0), 
                    rs.getString("idade"), 
                    rs.getString("raca"), 
                    rs.getString("vacinas"), 
                    rs.getBoolean("castrado"), 
                    rs.getString("historia"), 
                    rs.getString("porte").charAt(0), 
                    rs.getString("especie"),
                    rs.getBoolean("adotado")
                );
            }
    
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }

    //Retorna apenas animais machos
    public Animal[] getAnimaisMachos() {
        Animal animais[] = null;
        String sql = "SELECT id_animal, imagem, nome, sexo, idade, raca, vacinas, castrado, historia, porte, especie, adotado FROM animal WHERE animal.sexo LIKE 'M'";
    
        // Modificando o PreparedStatement para usar um ResultSet rolável
        try(PreparedStatement st = this.conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                rs.last();
                animais = new Animal[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                animais[i] = new Animal(
                    rs.getInt("id_animal"), 
                    rs.getString("imagem"), 
                    rs.getString("nome"), 
                    rs.getString("sexo").charAt(0), 
                    rs.getString("idade"), 
                    rs.getString("raca"), 
                    rs.getString("vacinas"), 
                    rs.getBoolean("castrado"), 
                    rs.getString("historia"), 
                    rs.getString("porte").charAt(0), 
                    rs.getString("especie"),
                    rs.getBoolean("adotado")
                );
            }
    
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }


    //Retorna apenas animais Femeas
    public Animal[] getAnimaisFemeas() {
        Animal animais[] = null;
        String sql = "SELECT id_animal, imagem, nome, sexo, idade, raca, vacinas, castrado, historia, porte, especie, adotado FROM animal WHERE animal.sexo LIKE 'F'";
    
        // Modificando o PreparedStatement para usar um ResultSet rolável
        try(PreparedStatement st = this.conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                rs.last();
                animais = new Animal[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                animais[i] = new Animal(
                    rs.getInt("id_animal"), 
                    rs.getString("imagem"), 
                    rs.getString("nome"), 
                    rs.getString("sexo").charAt(0), 
                    rs.getString("idade"), 
                    rs.getString("raca"), 
                    rs.getString("vacinas"), 
                    rs.getBoolean("castrado"), 
                    rs.getString("historia"), 
                    rs.getString("porte").charAt(0), 
                    rs.getString("especie"),
                    rs.getBoolean("adotado")
                );
            }
    
            rs.close();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais;
    }


    //Torna o animal adotado
    public void adotado(int id_animal) {
        String sql = "UPDATE animal SET adotado = 'TRUE' WHERE id_animal = ?";
        try(PreparedStatement st = this.conexao.prepareStatement(sql)){
            st.setInt(1, id_animal);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


        //Coloca o animal de volta para adoção
        public void voltou(int id_animal) {
            String sql = "UPDATE animal SET adotado = 'FALSE' WHERE id_animal = ?";
            try(PreparedStatement st = this.conexao.prepareStatement(sql)){
                st.setInt(1, id_animal);
                st.executeUpdate();
                st.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }


    
}
