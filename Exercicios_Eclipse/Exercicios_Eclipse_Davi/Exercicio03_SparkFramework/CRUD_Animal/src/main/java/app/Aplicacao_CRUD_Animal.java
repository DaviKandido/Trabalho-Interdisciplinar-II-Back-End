package app;

import service.ProdutoService_CRUD_Animal;
import spark.Spark;



public class Aplicacao_CRUD_Animal {

    private static ProdutoService_CRUD_Animal produtoService_CRUD_Animal = new ProdutoService_CRUD_Animal();

    public static void main(String[] args) {
        spark.port(6789);
        
        spark.post("/animal", (request, response) -> produtoService_CRUD_Animal.add(request, response));

        spark.get("/animal/:id", (request, response) -> produtoService_CRUD_Animal.get(request, response));

        spark.get("/animal/update/:id", (request, response) -> produtoService_CRUD_Animal.update(request, response));

        spark.get("/animal/delete/:id", (request, response) -> produtoService_CRUD_Animal.remove(request, response));
    
        spark.get("/animal", (request, response) -> produtoService_CRUD_Animal.getAll(request, response));
    }
}
