package dao;

import java.sql.*;

public class DAOAzure {

    protected Connection conexao = null;

    public DAOAzure() {
    }

    // Inicia a conexão com o PostgreSQL
    public boolean Conectar(){
        String driverName = "org.postgresql.Driver";
        String serverName = "sosbichinhos.postgres.database.azure.com"; // Host do banco no Azure
        String mydatabase = "postgres"; // Nome do banco de dados
        int porta = 5432; // Porta padrão do PostgreSQL
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase; // String de conexão JDBC
        String username = "sosbichinhos"; // Usuário do banco de dados
        String password = "Patinhas02"; // Senha do banco de dados
        boolean status = false;

        try{
            Class.forName(driverName); // Carrega o driver do PostgreSQL
            conexao = DriverManager.getConnection(url, username, password); // Estabelece a conexão
            status = (conexao == null); // Verifica se a conexão foi estabelecida
            System.out.println("Conexão efetuada com o PostgreSQL!");
        } catch(ClassNotFoundException e){
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- Driver não encontrado -- " + e.getMessage());
        } catch(SQLException e){
            System.err.println("Conexão NÃO efetuada com o PostgreSQL -- " + e.getMessage());
        }

        return status;
    }

    // Fecha a conexão com o PostgreSQL
    public boolean close(){
        boolean status = false;

        try{
            if (conexao != null && !conexao.isClosed()) {
                conexao.close(); // Fecha a conexão
                status = true;
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch(SQLException e){
            System.err.println("Erro ao fechar a conexão -- " + e.getMessage());
        }
        return status;
    }   
}
