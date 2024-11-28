package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class FormularioDAO extends DAOAzure {

    public FormularioDAO() {
        this.Conectar();
    }

    @Override
    public void finalize() {
        this.close();
    }

    private int maxId_form = 0;
     
    //Retorna o id do ultimo formulario inserido no banco de dados
    public int getMaxId() {
        try {  
            String sql = "SELECT MAX(id_formulario) as maxId FROM formulario";
            // para evitar SQL Injection
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();  // Usa executeQuery para comandos SELECT
                if (rs.next()) { // Verifica se o resultado contém ao menos uma linha
                    maxId_form = rs.getInt("maxId"); // Obtem o valor
                }
                rs.close(); // Fecha o ResultSet
                stmt.close(); //Fecha o Statement
            }
        } catch (SQLException u) {
            u.printStackTrace();
        }
        
        return maxId_form; // Retorna o valor de maxId
    }

    // Inserir Formulario para adotar animal
    public boolean insert(Formulario formulario) {
        boolean status = false;
        try {  
            this.maxId_form = (formulario.getIdFormulario() > this.maxId_form) ? formulario.getIdFormulario() : this.maxId_form;
            String sql = "INSERT INTO formulario (id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, telefone, ap_liberado) VALUES(?,?,?,?,?,?,?,?,?)";
        
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, formulario.getIdFormulario());
                stmt.setString(2, formulario.getAnimalSozinho());
                stmt.setBoolean(3, formulario.isFamiliaCiente());  
                stmt.setBoolean(4, formulario.isPermissao());  
                stmt.setBoolean(5, formulario.isTeveAnimal());  
                stmt.setInt(6, formulario.getIdAnimal());  
                stmt.setInt(7, formulario.getIdPessoa());  
                stmt.setString(8, formulario.getTelefone());  
                stmt.setBoolean(9, formulario.isApLiberado());  
                
                stmt.executeUpdate();  
            }
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    // Obter Formulario pelo ID
    public Formulario get(int id) {
        Formulario formulario = null;
        String sql = "SELECT id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, telefone, ap_liberado FROM formulario WHERE id_formulario = ?";
    
        try {
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id); 
            ResultSet rs = st.executeQuery(); 
    
            if (rs.next()) {
                formulario = new Formulario(
                    rs.getInt("id_formulario"),
                    rs.getString("animal_sozinho"),
                    rs.getBoolean("familia_ciente"),
                    rs.getBoolean("permissao"),
                    rs.getBoolean("teve_animal"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa"),
                    rs.getString("telefone"),
                    rs.getBoolean("ap_liberado")
                );
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formulario; // Retorna o formulario ou null se nao encontrado
    }    

    //Listar todos os formularios submetidos
    public List<Formulario> getAll() {
        List<Formulario> formularios = new ArrayList<Formulario>();
        
        try {
            String sql = "SELECT id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, telefone, ap_liberado FROM formulario";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
                  
            while(rs.next()) {
                Formulario f = new Formulario(
                    rs.getInt("id_formulario"),
                    rs.getString("animal_sozinho"),
                    rs.getBoolean("familia_ciente"),
                    rs.getBoolean("permissao"),
                    rs.getBoolean("teve_animal"),
                    rs.getInt("id_animal"),
                    rs.getInt("id_pessoa"),
                    rs.getString("telefone"),
                    rs.getBoolean("ap_liberado")
                );

                formularios.add(f);
            }
            
            rs.close(); // Fechar ResultSet
            stmt.close(); // Fechar Statement
        } catch (SQLException e) {
            System.err.println("Erro ao buscar formularios: " + e.getMessage());
            e.printStackTrace();
        }
        return formularios;
    }

    //Atualizar informacoes do formulario
    public boolean update(Formulario formulario) {
        boolean status = false;
        
        try {  
            String sql = "UPDATE formulario SET (id_formulario, animal_sozinho, familia_ciente, permissao, teve_animal, id_animal, id_pessoa, telefone, ap_liberado) VALUES(?,?,?,?,?,?,?,?,?)";
            
            try(PreparedStatement st = conexao.prepareStatement(sql)){
                st.setInt(1, formulario.getIdFormulario());
                st.setString(2, formulario.getAnimalSozinho());
                st.setBoolean(3, formulario.isFamiliaCiente());  
                st.setBoolean(4, formulario.isPermissao());  
                st.setBoolean(5, formulario.isTeveAnimal());  
                st.setInt(6, formulario.getIdAnimal());  
                st.setInt(7, formulario.getIdPessoa());  
                st.setString(8, formulario.getTelefone());  
                st.setBoolean(9, formulario.isApLiberado());  
                
                st.executeUpdate(sql);
                st.close();
                status = true;
            }
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    //Deletar Formulario
    public boolean delete(int id) {
        boolean status = false;
        
        String sql = "DELETE FROM formulario WHERE id_formulario = ?";
        
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, id);
            int affectedRows = pst.executeUpdate();
            status = affectedRows > 0; //se conseguir deletar retorna true
        } catch (SQLException e) {  
            System.err.println("Erro ao deletar formulário com ID: " + id);
            e.printStackTrace(); 
        }
        return status;
    }    
}
