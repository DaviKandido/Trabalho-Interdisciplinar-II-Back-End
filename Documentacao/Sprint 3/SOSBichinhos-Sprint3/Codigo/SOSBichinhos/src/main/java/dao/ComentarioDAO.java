// Source code is decompiled from a .class file using FernFlower decompiler.
//[bibliotecas/importacoes]
package dao;

import model.*;

import java.sql.*;
//import java.util.*;

//[cabecalho]
/**
 *@author: matheusEduardoCamposSoares
 *@data: 20.10.2024 - @version - 4.0 
**/

//[classes]
/**
 * A classe ComentarioDAO é responsável pelo gerenciamento das operações de acesso ao banco de dados
 * relacionadas à entidade Comentario, como inserção, exclusão, atualização e consulta de comentários.
 * Ela estende a classe DAOAzure, herdando métodos de conexão e fechamento da base de dados.
 */
public class ComentarioDAO extends DAOAzure 
{
    /**
     * Construtor da classe ComentarioDAO.
     * O construtor chama o método Conectar() para estabelecer a conexão com o banco de dados
     * assim que uma instância da classe ComentarioDAO é criada.
     */
    public ComentarioDAO() 
    {
        this.Conectar(); // Estabelece a conexão com o banco de dados
    }

    /**
     * Método finalize().
     * Este método é chamado pelo coletor de lixo (Garbage Collector) quando a instância do ComentarioDAO
     * está prestes a ser destruída, garantindo que a conexão com o banco de dados seja fechada.
     * Ele chama o método close() para liberar os recursos de conexão.
     */
    public void finalize() 
    {
        this.close(); // Fecha a conexão com o banco de dados
    }

    private int maxId = 0;//variavel criada para facilitar o acesso ao ultimo Id no BD

    ///////////////////////////////////[GET_MAX_ID]///////////////////////////////////

    /**
     * Retorna o ID do utlimo comentario inserido no banco de dados
     * @return o maior valor de ID presente na tabela comentario
     */
    public int getMaxId() 
    {
        try 
        {
            // Cria um statement para executar uma query no banco de dados
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             
            // Comando SQL que obtém o maior ID da tabela de comentários
            String sql = "SELECT MAX(id_comentario) as maxId FROM comentario"; // Alias 'maxId' para facilitar o acesso
             
            // Executa a consulta SQL e armazena o resultado
            ResultSet rs = st.executeQuery(sql); // Usa executeQuery para comandos SELECT
             
            // Verifica se o resultado contém ao menos uma linha
            if (rs.next()) 
            {
                // Obtem o valor do maior ID usando o alias 'maxId'
                maxId = rs.getInt("maxId"); 
            }
     
            // Fecha o ResultSet e o Statement após o uso
            rs.close();
            st.close();
        } 
        catch (SQLException u) 
        {
            // Caso ocorra uma exceção SQL, imprime o erro
            u.printStackTrace();
        }
         
        // Retorna o valor de maxId (o maior ID da tabela de comentários)
        return maxId;
    }
    
    ///////////////////////////////////[INSERT]///////////////////////////////////
     
    /**
     * Insere um novo comentário no banco de dados.
     *
     * O comentário é inserido na tabela 'comentario' com base nos atributos fornecidos
     * pelo objeto Comentario, incluindo o ID do comentário, o conteúdo, o ID do animal 
     * e o ID da pessoa relacionados ao comentário.
     *
     * @param comentario o objeto Comentario contendo as informações a serem inseridas no banco de dados.
     * @return true se o comentário foi inserido com sucesso, false se houve algum erro.
     */
    public boolean inserirComentario(Comentario comentario) 
    {
        boolean status = false;
        try 
        {
            this.maxId = (comentario.getId() > this.maxId) ? comentario.getId() : this.maxId;
            // Cria um statement para executar a inserção no banco de dados
            Statement st = conexao.createStatement();
            
            // Monta a consulta SQL para inserir um novo comentário
            String sql = "INSERT INTO comentario (id_comentario, conteudo, id_animal, id_pessoa) " +
                        "VALUES (" +
                        comentario.getId() + ", '" +
                        comentario.getConteudo() + "', '" +
                        comentario.getIdAnimal() + "', '" +
                        comentario.getIdPessoa() + "')";
            
            // Executa a consulta de inserção
            st.executeUpdate(sql);
            
            // Fecha o Statement após a execução
            st.close();
            
            // Define o status como true para indicar sucesso
            status = true;
        } 
        catch (SQLException u) 
        {
            // Caso ocorra uma exceção SQL, imprime o erro
            u.printStackTrace();
        }
        
        // Retorna o status da operação (true se inseriu, false se ocorreu um erro)
        return status;
    }

    ///////////////////////////////////[UPDATE]///////////////////////////////////

    /**
     * Atualiza os dados de um comentário existente no banco de dados.
     *
     * O método atualiza os campos do comentário na tabela 'comentario' com base nas 
     * informações fornecidas pelo objeto Comentario, como o ID, conteúdo, ID do animal, 
     * e ID da pessoa relacionados ao comentário.
     *
     * @param comentario o objeto Comentario que contém as informações atualizadas.
     * @return true se o comentário foi atualizado com sucesso, false se houve algum erro.
     */
    public boolean atualizarComentario(Comentario comentario) 
    {
        boolean status = false;
        try 
        {
            this.maxId = (comentario.getId() > this.maxId) ? comentario.getId() : this.maxId;
            // Cria um statement para executar a atualização no banco de dados
            Statement st = conexao.createStatement();
            
            // Monta a consulta SQL para atualizar os campos do comentário
            String sql = "UPDATE comentario SET " +
                        "id_comentario = " + comentario.getId() + ", " +
                        "conteudo = '" + comentario.getConteudo() + "', " +
                        "id_animal = '" + comentario.getIdAnimal() + "', " +
                        "id_pessoa = '" + comentario.getIdPessoa() + "' " +
                        "WHERE id_comentario = " + comentario.getId();
            
            // Executa a consulta de atualização
            st.executeUpdate(sql);
            
            // Fecha o Statement após a execução
            st.close();
            
            // Define o status como true para indicar sucesso
            status = true;
        } 
        catch (SQLException u) 
        {
            // Caso ocorra uma exceção SQL, imprime o erro
            u.printStackTrace();
        }
        
        // Retorna o status da operação (true se atualizou, false se ocorreu um erro)
        return status;
    }

    ///////////////////////////////////[DELETE]///////////////////////////////////
    
    /**
     * Exclui um comentário do banco de dados com base no ID fornecido.
     *
     * O método remove um registro da tabela 'comentario' cujo ID corresponde ao valor fornecido.
     *
     * @param id o ID do comentário que será excluído.
     * @return true se o comentário foi excluído com sucesso, false se houve algum erro.
     * @throws RuntimeException se ocorrer uma SQLException durante o processo.
     */
    public boolean excluirComentario(int id)
    {
        boolean status = false;
        try 
        {
            // Cria um statement para executar a exclusão no banco de dados
            Statement st = conexao.createStatement();
            
            // Executa o comando DELETE para remover o comentário com o ID fornecido
            st.executeUpdate("DELETE FROM comentario WHERE id_comentario = " + id);
            
            // Fecha o Statement após a execução
            st.close();
            
            // Define o status como true para indicar sucesso
            status = true;
        } 
        catch(SQLException u)
        {
            // Lança uma exceção RuntimeException se ocorrer uma SQLException
            throw new RuntimeException(u);
        }
        
        // Retorna o status da operação (true se excluiu, false se ocorreu um erro)
        return status;
    }

    ///////////////////////////////////[GET]///////////////////////////////////
    
    /**
     * Recupera um comentário do banco de dados com base no ID fornecido.
     *
     * O método busca na tabela 'comentario' o registro correspondente ao ID fornecido
     * e retorna um objeto Comentario contendo os dados do comentário, se encontrado.
     *
     * @param id o ID do comentário a ser recuperado.
     * @return um objeto Comentario se o comentário for encontrado, ou null se não houver correspondência.
     */
    public Comentario get(int id) {
        Comentario comentario = null;
        try {
            // Cria um statement para executar a consulta de forma insensível a mudanças e apenas leitura
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            // SQL para buscar o comentário com base no ID fornecido
            String sql = "SELECT * FROM comentario WHERE id_comentario = " + id;
            ResultSet rs = st.executeQuery(sql);

            // Se houver um resultado, cria um objeto Comentario com os dados do banco
            if (rs.next()) {
                comentario = new Comentario(
                    rs.getInt("id_comentario"),
                    rs.getString("conteudo"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa")
                );
            }
            
            // Fecha o Statement após a execução
            st.close();

        } catch (Exception e) {
            // Em caso de erro, imprime a mensagem de exceção
            System.err.println(e.getMessage());
        }
        
        // Retorna o objeto Comentario, ou null se não for encontrado
        return comentario;
    }

    ///////////////////////////////////[GET_ALL]///////////////////////////////////
    
    /**
     * Recupera todos os comentários armazenados no banco de dados.
     *
     * O método busca todos os registros da tabela 'comentario' e retorna um array de objetos Comentario,
     * onde cada objeto representa um comentário recuperado do banco de dados.
     *
     * @return um array de objetos Comentario contendo todos os comentários encontrados no banco,
     *         ou null se nenhum comentário for encontrado ou ocorrer algum erro.
     */
    public Comentario[] getComentario() {
        Comentario[] comentarios = null;
        try {
            // Cria um statement para executar a consulta de forma insensível a mudanças e apenas leitura
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            // Executa a consulta para obter todos os comentários
            ResultSet rs = st.executeQuery("SELECT * FROM comentario");

            // Verifica se existem comentários no resultado da consulta
            if (rs.next()) {
                rs.last(); // Move para a última linha para contar o número de registros
                comentarios = new Comentario[rs.getRow()]; // Inicializa o array com o número de registros
                rs.beforeFirst(); // Retorna para antes da primeira linha
            }

            // Itera sobre o ResultSet e preenche o array de Comentarios
            for (int i = 0; rs.next(); i++) {
                comentarios[i] = new Comentario(
                    rs.getInt("id_comentario"), 
                    rs.getString("conteudo"), 
                    rs.getInt("id_animal"), 
                    rs.getInt("id_pessoa")
                );
            }

            // Fecha o Statement após a execução
            st.close();

        } catch (Exception e) {
            // Em caso de erro, imprime a mensagem de exceção
            System.err.println(e.getMessage());
        }

        // Retorna o array de comentarios ou null se não houver resultados ou ocorrer um erro
        return comentarios;
    }

    ///////////////////////////////////[GET_ANIMAL_COMMENT]///////////////////////////////////

    /**
     * Recupera todos os comentários associados a um determinado animal com base no ID do animal.
     *
     * O método busca todos os registros da tabela 'comentario' cujo campo 'id_animal' corresponde ao ID fornecido
     * e retorna um array de objetos Comentario, onde cada objeto representa um comentário relacionado ao animal.
     *
     * @param id o ID do animal cujos comentários serão recuperados.
     * @return um array de objetos Comentario contendo os comentários encontrados, ou null se nenhum comentário for encontrado.
     */
    public Comentario[] getAnimal(int id) {
        Comentario[] comentarios = null;
        
        // SQL para buscar os comentários de um animal específico
        String sql = "SELECT * FROM comentario WHERE id_animal = ?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql, 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            // Define o valor do ID do animal no PreparedStatement (previne SQL Injection)
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // Verifica se há resultados no ResultSet
            if (rs.next()) {
                rs.last(); // Vai para o último registro para contar o número de linhas
                comentarios = new Comentario[rs.getRow()]; // Inicializa o array com o número de linhas
                rs.beforeFirst(); // Retorna ao início do ResultSet
            }

            // Itera sobre os resultados e preenche o array de Comentarios
            for (int i = 0; rs.next(); i++) {
                comentarios[i] = new Comentario(
                    rs.getInt("id_comentario"),
                    rs.getString("conteudo"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa")
                );
            }

        } catch (Exception e) {
            // Em caso de erro, imprime a mensagem de exceção
            System.err.println("Erro ao buscar comentários: " + e.getMessage());
        }

        // Retorna o array de comentarios ou null se nenhum comentário for encontrado
        return comentarios;
    }
}
