
// Source code is decompiled from a .class file using FernFlower decompiler.
package dao;

import model.*;

import java.sql.*;

public class PessoaDAO extends DAO {
    

    public PessoaDAO() {
        this.Conectar();
     }
  
     public void finalize() {
        this.close();
     }

    private int maxId_pessoa = 0;

    //Retorna o id do ultima pessoa inserido no banco de dados
    public int getMaxId_pessoa() {
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id_pessoa) as maxId_pessoa FROM pessoa"; // Alias para facilitar a leitura do valor
    
            ResultSet rs = st.executeQuery(sql); // Usa executeQuery para comandos SELECT
            if (rs.next()) { // Verifica se o resultado contém ao menos uma linha
                maxId_pessoa = rs.getInt("maxId_pessoa"); // Obtem o valor usando o alias
            }

            rs.close(); // Fecha o ResultSet
            st.close(); // Fecha o Statement
        } catch (SQLException u) {
            u.printStackTrace();
        }
        
        return maxId_pessoa; // Retorna o valor de maxId_pessoa
    }

    

    
     
    //Inseri um pessoa no banco de dados
    public boolean inserirpessoa(Pessoa pessoa){
        boolean status = false;
        try {
            this.maxId_pessoa = (pessoa.getId() > this.maxId_pessoa) ? pessoa.getId() : this.maxId_pessoa;
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO pessoa (id_pessoa, nome, email, senha, moradia, imagem, idade, sexo) " +
             "VALUES (" +
             pessoa.getId() + ", '" +
             pessoa.getNome() + "', '" +
             pessoa.getEmail() + "', '" +
             pessoa.getSenha() + "', '" +
             pessoa.getMoradia() + "', '" +
             pessoa.getImagem() + "', " +
             pessoa.getIdade() + ", '" +
             pessoa.getSexo() + "')";


            //Executa o update com a variavel String query               
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    }   

    //Atualiza o pessoa pertencente ao id do seu objeto
    public boolean atualizarpessoa(Pessoa pessoa) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE pessoa SET " +
             "nome = '" + pessoa.getNome() + "', " +
             "email = '" + pessoa.getEmail() + "', " +
             "senha = '" + pessoa.getSenha() + "', " +
             "moradia = '" + pessoa.getMoradia() + "', " +
             "imagem = '" + pessoa.getImagem() + "', " +
             "idade = " + pessoa.getIdade() + ", " +
             "sexo = '" + pessoa.getSexo() + "' " +
             "WHERE id_pessoa = " + pessoa.getId();


            //Executa o update com a variavel String query               
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
    
    //Exclui o pessoa pertencente ao id informado
    public boolean excluirpessoa(int id){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM pessoa WHERE id_pessoa = " + id);
            st.close();
            status = true;
        } catch(SQLException u){
            throw new RuntimeException(u);
        }
        return status;
    }

    //Retorna o pessoa pertencente ao id informado
    public Pessoa get(int id) {
        Pessoa pessoa = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM pessoa WHERE id_pessoa = " + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                pessoa = new Pessoa(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("moradia"),
                    rs.getString("imagem"),
                    rs.getInt("idade"),
                    rs.getString("sexo")
                );
            }
            
            st.close();
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pessoa;
    }

    //Retorna todos os animais presentes no banco de dados
    public Pessoa[] getpessoas() {
        Pessoa[] pessoas = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM pessoa");
    
            if (rs.next()) {
                rs.last();
                pessoas = new Pessoa[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                pessoas[i] = new Pessoa(
                    rs.getInt("id_pessoa"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("moradia"),
                    rs.getString("imagem"),
                    rs.getInt("idade"),
                    rs.getString("sexo")
                );
            }
            
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return pessoas;
    }

    
}