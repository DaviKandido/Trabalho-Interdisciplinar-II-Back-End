package app;

import service.ProdutoService_CRUD_Animal;
import static spark.Spark.*;



public class Aplicacao_CRUD_Animal {

    private static ProdutoService_CRUD_Animal produtoService_CRUD_Animal = new ProdutoService_CRUD_Animal();

        public static void main(String[] args) {
        staticFileLocation("/public");//setando a pasta padrão do arquivo
        
        port(6789);
        
        post("/animal", (request, response) -> produtoService_CRUD_Animal.add(request, response));

        get("/animal/:id", (request, response) -> produtoService_CRUD_Animal.get(request, response));

        get("/animal/update/:id", (request, response) -> produtoService_CRUD_Animal.update(request, response));

        get("/animal/delete/:id", (request, response) -> produtoService_CRUD_Animal.remove(request, response));
    
        get("/animal", (request, response) -> produtoService_CRUD_Animal.getAll(request, response));
    }
}
