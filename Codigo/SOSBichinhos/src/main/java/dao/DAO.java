package dao;

import java.sql.*;


public class DAO {

    protected Connection conexao = null;

    public DAO() {
    }

    
    //Inicia a conecção com o postgresSQL
    public boolean Conectar(){
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "SOSBichinhos";
        int porta = 5432;
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
}