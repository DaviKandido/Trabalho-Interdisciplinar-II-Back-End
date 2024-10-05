//[bibliotecas]
package app;

import service.ComentarioService_CRUD_Comentario;
import static spark.Spark.*;

//[cabecalho]
/**
 * @author: matheusEduardoCamposSoares - 837435
 * @data: 23.09.2024 - aplicacaoComentario
**/


public class AplicacaoComentario {

    private static ComentarioService_CRUD_Comentario comentarioService_CRUD_Comentario = new ComentarioService_CRUD_Comentario();

    public static void main(String[] args) {
        
        staticFileLocation("/public");//setando a pasta padrão do arquivo
        port(8081);



        post("/comentario", (request, response) -> comentarioService_CRUD_Comentario.add(request, response));

        get("/comentario/:id", (request, response) -> comentarioService_CRUD_Comentario.get(request, response));

        get("/comentario/update/:id", (request, response) -> comentarioService_CRUD_Comentario.update(request, response));

        post("/comentario/delete/:id", (request, response) -> comentarioService_CRUD_Comentario.remove(request, response));
    
        get("/comentario", (request, response) -> comentarioService_CRUD_Comentario.getAll(request, response));
    }
}
//Azure blockStoreg - para imagem