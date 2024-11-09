
// Source code is decompiled from a .class file using FernFlower decompiler.
package dao;

import model.*;

import java.sql.*;

public class PessoaDAO extends DAOAzure {
    

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

    
    public boolean inserirpessoa(Pessoa pessoa) {
        boolean status = false;
        try {
            this.maxId_pessoa = (pessoa.getId() > this.maxId_pessoa) ? pessoa.getId() : this.maxId_pessoa;
            String senhaCriptografada = CriptografiaAES.criptografar(pessoa.getSenha());
    
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO pessoa (id_pessoa, nome, email, senha, moradia, imagem, idade, sexo) " +
                         "VALUES (" +
                         pessoa.getId() + ", '" +
                         pessoa.getNome() + "', '" +
                         pessoa.getEmail() + "', '" +
                         senhaCriptografada + "', '" +
                         pessoa.getMoradia() + "', '" +
                         pessoa.getImagem() + "', " +
                         pessoa.getIdade() + ", '" +
                         pessoa.getSexo() + "')";
    
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    //Atualiza o pessoa pertencente ao id do seu objeto
    public boolean atualizarpessoa(Pessoa pessoa) {
        boolean status = false;
        try {
            // Atualiza o ID máximo, caso a pessoa tenha um ID maior que o registrado
            this.maxId_pessoa = (pessoa.getId() > this.maxId_pessoa) ? pessoa.getId() : this.maxId_pessoa;
            
            // Criptografa a senha
            String senhaCriptografada = CriptografiaAES.criptografar(pessoa.getSenha());
    
            // Cria a instrução SQL de atualização
            Statement st = conexao.createStatement();
            String sql = "UPDATE pessoa SET " +
                         "nome = '" + pessoa.getNome() + "', " +
                         "email = '" + pessoa.getEmail() + "', " +
                         "senha = '" + senhaCriptografada + "', " +
                         "moradia = '" + pessoa.getMoradia() + "', " +
                         "imagem = '" + pessoa.getImagem() + "', " +
                         "idade = " + pessoa.getIdade() + ", " +
                         "sexo = '" + pessoa.getSexo() + "' " +
                         "WHERE id_pessoa = " + pessoa.getId();
    
            // Executa a instrução SQL e fecha o statement
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
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

   // Retorna o objeto Pessoa correspondente ao ID informado
public Pessoa get(int id) {
    Pessoa pessoa = null;
    try {
        Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "SELECT * FROM pessoa WHERE id_pessoa = " + id;
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            String senhaDescriptografada;
            try {
                senhaDescriptografada = CriptografiaAES.descriptografar(rs.getString("senha"));
            } catch (Exception e) {
                System.err.println("Erro ao descriptografar a senha para ID: " + id);
                senhaDescriptografada = "";  // Ou um valor padrão
            }

            pessoa = new Pessoa(
                rs.getInt("id_pessoa"),
                rs.getString("nome"),
                rs.getString("email"),
                senhaDescriptografada,
                rs.getString("moradia"),
                rs.getString("imagem"),
                rs.getInt("idade"),
                rs.getString("sexo")
            );
        } else {
            System.out.println("Nenhuma pessoa encontrada com o ID: " + id);
        }

        rs.close();  // Fechando o ResultSet
        st.close();  // Fechando o Statement

    } catch (Exception e) {
        e.printStackTrace();  // Exibe o stack trace completo para diagnóstico
    }
    return pessoa;
}

    //Retorna todos os animais presentes no banco de dados
    public Pessoa[] getpessoas() {
        Pessoa[] pessoas = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM pessoa");
    
            if (rs.next()) {  // Verifica se há pelo menos uma linha
                rs.last();  // Move para a última linha
                int rowCount = rs.getRow();  // Número total de linhas
                pessoas = new Pessoa[rowCount];
                rs.beforeFirst();  // Volta para antes da primeira linha
    
                String senhaDescriptografada;
                for (int i = 0; rs.next(); i++) {
                    try {
                        senhaDescriptografada = CriptografiaAES.descriptografar(rs.getString("senha"));
                    } catch (Exception e) {
                        System.err.println("Erro ao descriptografar a senha para ID: " + rs.getInt("id_pessoa"));
                        senhaDescriptografada = "";  // Ou uma mensagem de erro
                    }
    
                    pessoas[i] = new Pessoa(
                        rs.getInt("id_pessoa"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        senhaDescriptografada,
                        rs.getString("moradia"),
                        rs.getString("imagem"),
                        rs.getInt("idade"),
                        rs.getString("sexo")
                    );
                }
            }
    
        } catch (Exception e) {
            e.printStackTrace();  // Exibe o stack trace completo para diagnóstico
        }
        return pessoas;
    }
    
    
}
