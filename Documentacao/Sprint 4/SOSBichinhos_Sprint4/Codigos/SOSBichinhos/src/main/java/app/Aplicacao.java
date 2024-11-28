package app;

import service.AnimalService;
import service.EnderecoService;
import service.EnviadoService;
import service.FormularioService;
import service.PessoaService;
import service.TagsAnimalService;
import service.ComentarioService;

import static spark.Spark.*;


public class Aplicacao {

    private static AnimalService animalService = new AnimalService(); // Service animal
    private static TagsAnimalService tagsAnimalService = new TagsAnimalService();
    private static EnviadoService enviadoService = new EnviadoService();


    private static ComentarioService comentarioService = new ComentarioService(); // Service comentario
    private static FormularioService formularioService = new FormularioService(); // Service formulario
    private static PessoaService aplicacaousuario = new PessoaService(); // Service pessoa'
    private static EnderecoService aplicacaoEndereco = new EnderecoService(); // Service Endereco


    public static void main(String[] args) {
        
        staticFileLocation("/public"); //setando a pasta padrão do arquivo
        port(8080);

        // //Redireciona para o arquivo HTML quando acessar a raiz
        // get("/", (request, response) -> {
        //     response.redirect("/modules/TelaCadastroAnimalONG/teladecadastrodeanimalONG.html");
        //     return null;
        // });

        // -------------------------Aplicações CRUD Animal  ------------------------------------------ //


        post("/animal", (request, response) -> animalService.add(request, response));

        get("/animal/:id", (request, response) -> animalService.get(request, response));
        
        put("/animal/update/:id", (request, response) -> animalService.update(request, response));

        put("/animal/adotado/:id", (request, response) -> animalService.adotou(request, response));

        put("/animal/voltou/:id", (request, response) -> animalService.voltou(request, response));
        
        delete("/animal/delete/:id", (request, response) -> animalService.remove(request, response));
        
        get("/animal", (request, response) -> animalService.getAll(request, response));


        // -------------------------Aplicações CRUD Tags Animal  ------------------------------------------ //
        

        post("/tagsAnimal", (request, response) -> tagsAnimalService.add(request, response));

        get("/tagsAnimal/:id", (request, response) -> tagsAnimalService.get(request, response));

        put("/tagsAnimal/update/:id", (request, response) -> tagsAnimalService.update(request, response));

        delete("/tagsAnimal/delete/:id", (request, response) -> tagsAnimalService.remove(request, response));
    
        //get("/tagsAnimal", (request, response) -> tagsAnimalService.getAll(request, response));


        // -------------------------Aplicações CRUD Enviado - animal ------------------------------------------ //


        post("/enviado", (request, response) -> enviadoService.add(request, response));

        get("/enviado/:id", (request, response) -> enviadoService.get(request, response));
        
        put("/enviado/update/:id", (request, response) -> enviadoService.update(request, response));
        
        delete("/enviado/delete/:id", (request, response) -> enviadoService.remove(request, response));
        
        get("/enviado", (request, response) -> enviadoService.getAll(request, response));
        
        post("/enviado_gemini", (request, response) -> enviadoService.analise(request, response));


        // -------------------------Aplicações CRUD Comentario  ------------------------------------------ //


        post("/comentario", (request, response) -> comentarioService.add(request, response));

        get("/comentario/:id", (request, response) -> comentarioService.get(request, response));

        put("/comentario/update/:id", (request, response) -> comentarioService.update(request, response));

        delete("/comentario/delete/:id", (request, response) -> comentarioService.remove(request, response));
    
        get("/comentario", (request, response) -> comentarioService.getAll(request, response));

        get("/comentario/animal/:id", (request, response) -> comentarioService.getAnimal(request, response));

        // -------------------------Aplicações CRUD Formulário  ------------------------------------------ //

                
        post("/formulario", (request, response) -> formularioService.insert(request, response));

        get("/formulario/:id", (request, response) -> formularioService.get(request, response));
        
        get("/formulario", (request, response) -> formularioService.getAll(request, response));
        
        put("/formulario/update/:id", (request, response) -> formularioService.update(request, response));
           
        delete("/formulario/delete/:id", (request, response) -> formularioService.delete(request, response));
   

        // -------------------------Aplicações CRUD Pessoa  ------------------------------------------ //


        post("/usuario", (request, response) -> aplicacaousuario.add(request, response));

        get("/usuario/:id", (request, response) -> aplicacaousuario.get(request, response));

        put("/usuario/update/:id", (request, response) -> aplicacaousuario.update(request, response));

        delete("/usuario/delete/:id", (request, response) -> aplicacaousuario.remove(request, response));
    
        get("/usuario", (request, response) -> aplicacaousuario.getAll(request, response));
       

        // -------------------------Aplicações CRUD Endereço  ------------------------------------------ //


        post("/endereco", (request, response) -> aplicacaoEndereco.add(request, response)); 

        get("/endereco/:id", (request, response) -> aplicacaoEndereco.get(request, response)); 

        put("/endereco/update/:id", (request, response) -> aplicacaoEndereco.update(request, response)); 

        delete("/endereco/delete/:id", (request, response) -> aplicacaoEndereco.remove(request, response));

        get("/endereco", (request, response) -> aplicacaoEndereco.getAll(request, response));
        
    }
}


//Azure blockStoreg - para imagem
//Gemini Vision - para imagem
//Azure Vision - para imagem
