package app;

import service.PessoaService;
import service.EnderecoService;
import static spark.Spark.*;


public class AplicacaoPessoa {

    private static PessoaService aplicacaousuario = new PessoaService();
    private static EnderecoService aplicacaoEndereco = new EnderecoService();

    public static void main(String[] args) {
        
        
            port(8080);
            
            staticFiles.location("/public");




         post("/Usuario", (request, response) -> aplicacaousuario.add(request, response));

       
        post("/Endereco", (request, response) -> aplicacaoEndereco.add(request, response)); 


        get("/Usuario/:id", (request, response) -> aplicacaousuario.get(request, response));

        get("/Usuario/update/:id", (request, response) -> aplicacaousuario.update(request, response));

        post("/Usuario/delete/:id", (request, response) -> aplicacaousuario.remove(request, response));
    
        get("/Usuario", (request, response) -> aplicacaousuario.getAll(request, response));
       


        get("/Endereco/:id", (request, response) -> aplicacaoEndereco.get(request, response)); 

        put("/Endereco/:id", (request, response) -> aplicacaoEndereco.update(request, response)); 

        delete("/Endereco/:id", (request, response) -> aplicacaoEndereco.remove(request, response));

        get("/Endereco", (request, response) -> aplicacaoEndereco.getAll(request, response));
        
    }
}

