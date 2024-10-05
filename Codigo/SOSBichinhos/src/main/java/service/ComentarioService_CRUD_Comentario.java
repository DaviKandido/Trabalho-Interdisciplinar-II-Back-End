//[bibliotecas]
package service;

import com.google.gson.Gson;

import dao.ComentarioDAO;
import model.Comentario;
import java.io.IOException;
import spark.Request;
import spark.Response;

//[cabecalho]
/**
 * @author: matheusEduardoCamposSoares -837435
 * @data: 23.09.2024 - serviceComentario
**/

public class ComentarioService_CRUD_Comentario {
    
    private ComentarioDAO ComentarioDAO;

    public ComentarioService_CRUD_Comentario(){
        try{
            this.ComentarioDAO = new ComentarioDAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();

        Comentario registro = gson.fromJson(request.body(), Comentario.class);

        //debug
        //System.out.println(registro.getId());
        //System.out.println("FOOOOOOIIII");

        int id = this.ComentarioDAO.getMaxId() + 1;  // Corrigido o getMaxId
        
        registro.setId(id);
        System.out.println(registro);

        ComentarioDAO.inserirComentario(registro);

        response.status(201); // 201 Created
        return id;
    }

   public Object get(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        Comentario comentario = ComentarioDAO.get(id);

        if (comentario != null){
            Gson gson = new Gson();
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            // Retorna o objeto Comentario como JSON
            return gson.toJson(comentario);
        } else{
            response.status(404); // 404 Not Found
            return "Comentario " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

        Comentario comentario = (Comentario) ComentarioDAO.get(id);

        if (comentario != null){
            
            Gson gson = new Gson();

            Comentario registroAtualizado = gson.fromJson(request.body(), Comentario.class);

            // Atualizando os campos do objeto comentario
            comentario.setConteudo(registroAtualizado.getConteudo());
            comentario.setIdAnimal(registroAtualizado.getIdAnimal());
            comentario.setIdPessoa(registroAtualizado.getIdPessoa());

            ComentarioDAO.atualizarComentario(comentario);  // Usando o objeto `comentario` atualizado

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "Comentario não encontrado.";
        }
    }

    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        
        Comentario comentario = ComentarioDAO.get(id);
        
        if (comentario != null){
            ComentarioDAO.excluirComentario(id);
            response.status(200); // Sucesso
            return id;
        }else{
            response.status(404); // 404 Not Found
            return "Comentario não encontrado.";
        }
    }

    public Object getAll(Request request, Response response){
        Gson gson = new Gson();

        response.header("Content-type", "application/json");
        response.header("Content-Encoding", "UTF-8");

        // Retorna a lista de comentarios como JSON
        return gson.toJson(ComentarioDAO.getComentarios());
    }
}