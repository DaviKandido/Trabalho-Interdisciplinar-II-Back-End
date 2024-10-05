package app;

import static spark.Spark.*;
import service.FormularioService;


public class AplicacaoFormulario {
	
	private static FormularioService formularioService = new FormularioService();
	
    public static void main(String[] args) {
        port(8080);
        
        staticFiles.location("/public");
        
        post("/formulario", (request, response) -> formularioService.insert(request, response));

        get("/formulario/:id", (request, response) -> formularioService.get(request, response));
        
        get("/formulario", (request, response) -> formularioService.getAll(request, response));
        
        post("/formulario/update/:id", (request, response) -> formularioService.update(request, response));
           
        delete("/formulario/:id", (request, response) -> formularioService.delete(request, response));
   
    }
}