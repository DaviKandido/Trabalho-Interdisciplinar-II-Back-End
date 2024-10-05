package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.*;


public class FormularioDAO extends DAO {
	
	public FormularioDAO() {
        this.Conectar();
     }

	@Override
	public void finalize() {
		this.close();
	}
	
	 private int maxId = 0;

	 public int getMaxId() {
		 String sql = "SELECT MAX(id) as maxId FROM \"Formulario\"";
	     try {
	    	 Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    
	         ResultSet rs = st.executeQuery(sql); 
	         if (rs.next()) {
	        	 maxId = rs.getInt("maxId");
	         }
	         rs.close(); 
	         st.close(); 
	      } catch (SQLException u) {
	         u.printStackTrace();
	      }
	        
	        return maxId;
	    }

	//inserir um formulario no banco de dados
	public boolean insert(Formulario formulario) {
		boolean status = false;
		try {  
			this.maxId = (formulario.getId() > this.maxId) ? formulario.getId() : this.maxId;
	        Statement st = conexao.createStatement();
	        String sql = "INSERT INTO \"Formulario\" (id, nome, idade, sexo, cidade, ap_liberado, ciente, teve_animal, permissao, animal_sozinho, aonde_fica, telefone, email, nome_animal, imagem_animal, moradia) "
	                   + "VALUES (" + formulario.getId() + ", '" + formulario.getNome() + "', "  
	                   + formulario.getIdade() + ", '" + formulario.getSexo() + "', '" + formulario.getCidade() + "', '"
	                   + formulario.getApLiberado() + "', '" + formulario.getCiente() + "', '"  + formulario.getTeveAnimal() + "', '"
	                   + formulario.getPermissao() + "', '" + formulario.getAnimalSozinho() + "', '"  + formulario.getAondeFica() + "', '"
	                   + formulario.getTelefone() + "', '" + formulario.getEmail() + "', '"  + formulario.getNomeAnimal() + "', '"
	                   + formulario.getUrlImagem() + "', '" + formulario.getMoradia() + "');";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	//obter o formulario pelo if
	public Formulario get(int id) {
	    Formulario formulario = null;
	    
        try { 
        	Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM \"Formulario\" WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);
           
            if (rs.next()) {
            	formulario = new Formulario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("cidade"),
                        rs.getString("ap_liberado"),
                        rs.getString("ciente"),
                        rs.getString("teve_animal"),
                        rs.getString("permissao"),
                        rs.getString("animal_sozinho"),
                        rs.getString("aonde_fica"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("nome_animal"),
                        rs.getString("imagem_animal"),
                        rs.getString("moradia")
                    );
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formulario;
    }
	
	//obter todos os formularios
	public Formulario[] getForms() {
        Formulario[] form = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM \"Formulario\" ");
    
            if (rs.next()) {
                rs.last();
                form = new Formulario[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                form[i] = new Formulario(
                		rs.getInt("id"),
                    	rs.getString("nome"),
                    	rs.getInt("idade"),
                    	rs.getString("sexo").charAt(0),
                    	rs.getString("cidade"),
                    	rs.getString("ap_liberado"),
                    	rs.getString("ciente"),
                    	rs.getString("teve_animal"),
                    	rs.getString("permissao"),
                    	rs.getString("animal_sozinho"),
                    	rs.getString("aonde_fica"),
                    	rs.getString("telefone"),
                    	rs.getString("email"),
                    	rs.getString("nome_animal"),
                    	rs.getString("imagem_animal"),
                    	rs.getString("moradia")
                );
            }
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return form;
    }
	
	public List<Formulario> get(String orderBy) {	
	
		List<Formulario> formularios = new ArrayList<Formulario>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM \"Formulario\"" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Formulario f = new Formulario(
	        			rs.getInt("id"),
	                    rs.getString("nome"),
	                    rs.getInt("idade"),
	                    rs.getString("sexo").charAt(0), 
	                    rs.getString("cidade"),
	                    rs.getString("ap_liberado"),
	                    rs.getString("ciente"),
	                    rs.getString("teve_animal"),
	                    rs.getString("permissao"),
	                    rs.getString("animal_sozinho"),
	                    rs.getString("aonde_fica"),
	                    rs.getString("telefone"),
	                    rs.getString("email"),
	                    rs.getString("nome_animal"),
	                    rs.getString("imagem_animal"),
	                    rs.getString("moradia")
	        	);
	           formularios.add(f);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return formularios;
	}
	
	//atualizar informacoes do formulario
	public boolean update(Formulario formulario) {
	    boolean status = false;
	    try {  
	    	Statement st = conexao.createStatement();
	        String sql = "UPDATE \"Formulario\" SET " +
	                "nome = '" + formulario.getNome() + "', " +
	                "idade = " + formulario.getIdade() + ", " +
	                "sexo = '" + formulario.getSexo() + "', " +
	                "cidade = '" + formulario.getCidade() + "', " +
	                "ap_liberado = '" + formulario.getApLiberado() + "', " + // Corrigido
	                "ciente = '" + formulario.getCiente() + "', " +
	                "teve_animal = '" + formulario.getTeveAnimal() + "', " +
	                "permissao = '" + formulario.getPermissao() + "', " +
	                "animal_sozinho = '" + formulario.getAnimalSozinho() + "', " +
	                "aonde_fica = '" + formulario.getAondeFica() + "', " +
	                "telefone = '" + formulario.getTelefone() + "', " +
	                "email = '" + formulario.getEmail() + "', " +
	                "nome_animal = '" + formulario.getNomeAnimal() + "', " + 
	                "imagem_animal = '" + formulario.getUrlImagem() + "', " +
	                "moradia = '" + formulario.getMoradia() + "' " + 
	                "WHERE id = " + formulario.getId();
	        st.executeUpdate(sql);
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}
	
	//deletar formulario
	public boolean delete(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM \"Formulario\" WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
}