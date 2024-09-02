package dao;

import model.*;

import java.sql.*;

public class CRUD_Animal_DAO {
    
    private int maxId = 0;

    //Retorna o id do ultimo animal inserido no banco de dados
    public int getMaxId(){
        return maxId;
    }


    private Connection conexao;
    
    public CRUD_Animal_DAO(){
        conexao = null;
    }
    

    //Inicia a conecção com o postgresSQL
    public boolean Conectar(){
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "CRUD_Animal";
        int porta = 6789;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "patinhas";
        String password = "patinhas";
        boolean status = false;

        try{
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conexão efetuada com o postgres!");
        } catch(ClassNotFoundException e){
            System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado --" + e.getMessage());
        } catch(SQLException e){
            System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    //Fecha a conecção com o postgresSQL
    public boolean close(){
        boolean status = false;

        try{
            conexao.close();
            status = true;
        }catch(SQLException e){
            System.err.println("Erro ao fechar a conexão -- " + e.getMessage());
        }
        return status;
    }

    //Inseri um animal no banco de dados
    public boolean inserirAnimal(Animal animal){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO animal (id, url, nome, sexo, idade, raca, vacinas, cadastrado, historia, tags, porte, especie) " +
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
                           animal.getEspecie() + "')";

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
                         "especie = '" + animal.getEspecie() + "' " +
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
        Animal[] animais = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM animal WHERE id = " + id);
    
            if (rs.next()) {
                rs.last();
                animais = new Animal[rs.getRow()];
                rs.beforeFirst();
            }
    
            animais[0] = new Animal(
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

    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return animais[0];
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
