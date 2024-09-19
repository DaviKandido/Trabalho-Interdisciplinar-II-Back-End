package app;

import service.AnimalService_CRUD_Animal;
import static spark.Spark.*;


public class AplicacaoAnimal {

    private static AnimalService_CRUD_Animal animalService_CRUD_Animal = new AnimalService_CRUD_Animal();

    public static void main(String[] args) {
        
        staticFileLocation("/public");//setando a pasta padrão do arquivo
        port(8081);

        // //Redireciona para o arquivo HTML quando acessar a raiz
        // get("/", (request, response) -> {
        //     response.redirect("/modules/TelaCadastroAnimalONG/teladecadastrodeanimaONG.html");
        //     return null;
        // });


        post("/animal", (request, response) -> animalService_CRUD_Animal.add(request, response));

        get("/animal/:id", (request, response) -> animalService_CRUD_Animal.get(request, response));

        get("/animal/update/:id", (request, response) -> animalService_CRUD_Animal.update(request, response));

        post("/animal/delete/:id", (request, response) -> animalService_CRUD_Animal.remove(request, response));
    
        get("/animal", (request, response) -> animalService_CRUD_Animal.getAll(request, response));
        
        // get("/animal", (request, response) -> {
        //     try {
        //         response.type("application/json");
        //         return produtoService_CRUD_Animal.getAll(request, response);
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //         response.status(500);
        //         return "Erro ao processar a solicitação";
        //     }
        // });   
        
    }
}


//Azure blockStoreg - para imagem