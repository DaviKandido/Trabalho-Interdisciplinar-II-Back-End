
package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.EnviadoDAO;
import model.Enviado;

import spark.Request;
import spark.Response;


public class EnviadoService {

    private EnviadoDAO enviadoDAO = new EnviadoDAO();

    public EnviadoService(){
        try{
            this.enviadoDAO = new EnviadoDAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }


    public Object analise(Request request, Response response){
        
        Gson gson = new Gson();
        JsonObject requestBody = gson.fromJson(request.body(), JsonObject.class);
        String url = requestBody.get("url").getAsString();

        if (url != null){
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            String analise = "";

            try {
                analise = GeminiVisionService.analyzeImage(url);
            } catch (Exception e) {
                return "Erro ao analisar a imagem" + e.getMessage();
            }

            System.out.println(analise);

            // Retorna o objeto enviado como JSON
            return analise;
        } else{
            response.status(404); // 404 Not Found
            return "Erro ao analisar a imagem";
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();

        Enviado registro = gson.fromJson(request.body(), Enviado.class);
       
        int id = this.enviadoDAO.getMaxId() + 1;  // Corrigido o getMaxId
        
        registro.setId_enviado(id);

        enviadoDAO.inserir(registro);

        response.status(201); // 201 Created
        return id;
    }

    public Object get(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        Enviado enviado = enviadoDAO.get_FromIdEnviado(id);

        System.out.println("\n Animal Enviado: " + enviado );

        if (enviado != null){
            Gson gson = new Gson();
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            // Retorna o objeto Animal como JSON
            return gson.toJson(enviado);
        } else{
            response.status(404); // 404 Not Found
            return "enviado " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response){

        int id = Integer.parseInt(request.params(":id"));

        System.out.println("\n Id do Enviado atualizado: " + id);

        Enviado enviado = (Enviado) enviadoDAO.get(id);

        if (enviado != null){
            
            Gson gson = new Gson();

            Enviado registroAtualizado = gson.fromJson(request.body(), Enviado.class);

            // Atualizando os campos do objeto Enviado
            enviado.setImagem(registroAtualizado.getImagem());
            enviado.setSexo(registroAtualizado.getSexo());
            enviado.setEspecie(registroAtualizado.getEspecie());
            enviado.setRaca(registroAtualizado.getRaca());
            enviado.setIdade(registroAtualizado.getIdade());
            enviado.setPorte(registroAtualizado.getPorte());
            enviado.setTemperamento(registroAtualizado.getTemperamento());
            enviado.setNecessidades_especiais(registroAtualizado.getNecessidades_especiais());
            enviado.setEstado_de_saude(registroAtualizado.getEstado_de_saude());
            enviado.setCaracteristicas_gerais(registroAtualizado.getCaracteristicas_gerais());
            enviado.setLocalizacao(registroAtualizado.getLocalizacao());


            enviado.setId_pessoa(registroAtualizado.getId_pessoa()); //Coloca o id da pessoa a ser atualizado

            enviado.setId_enviado(id); //Coloca o id do envio a ser atualizado

            enviadoDAO.atualizar(enviado);  // Usando o objeto `Enviado` atualizado

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "Enviado não encontrado.";
        }
    }
        

    public Object remove(Request request, Response response){

        int id = Integer.parseInt(request.params(":id"));

        System.out.println("\n Id do Enviado removido: " + id);
        
        Enviado enviado = enviadoDAO.get_FromIdEnviado(id);
        
        if (enviado != null){
            enviadoDAO.excluir(id);
            response.status(200); // Sucesso
            return id;
        }else{
            response.status(404); // 404 Not Found
            return "Enviado não encontrado.";
        }
    }
    
    public Object getAll(Request requst, Response response){
        Gson gson = new Gson();

        response.header("Content-type", "application/json");
        response.header("Content-Encoding", "UTF-8");

        return gson.toJson(enviadoDAO.getAll());
    }
}
