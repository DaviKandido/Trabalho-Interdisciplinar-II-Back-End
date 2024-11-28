package service;

import com.google.gson.Gson;

import dao.AnimalDAO;
import model.Animal;
import java.io.IOException;


import spark.Request;
import spark.Response;

// import javax.servlet.http.Part;
// import java.util.Collection;
// import javax.servlet.http.Part;


public class AnimalService {
    
    private AnimalDAO animalDAO;

    public AnimalService(){
        try{
            this.animalDAO = new AnimalDAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

   // public Object addImagem(Request request, Response response) {
    // String pastaDestino = "../../resources/public/imgs/imagsInputs/"; // Defina o caminho da pasta onde a imagem será salva

    //     System.out.println("\ncheguei AQUIII!!!\n");

    //     try {   
    //         // Obtém as partes do request
    //         Collection<Part> parts = request.raw().getParts();
    //         Part filePart = null;
            
    //         for (Part part : parts) {
    //             if (part.getName().equals("imagem")) { // Verifica se o nome da parte é "imagem"
    //                 filePart = part;
    //                 break; // Sai do loop após encontrar a parte da imagem
    //             }
    //         }

    //         if (filePart != null) {
    //             String fileName = filePart.getSubmittedFileName();
    //             filePart.write(pastaDestino + fileName); // Salva a imagem na pasta definida

    //             response.status(201); // 201 Created
    //             return fileName; // Retorna o nome do arquivo salvo
    //         } else {
    //             response.status(400); // Bad Request
    //             return "Arquivo de imagem não encontrado";
    //         }

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         response.status(500); // Internal Server Error
    //         return "Erro ao salvar a imagem: " + e.getMessage();
    //     }
    // }

    
    public Object add(Request request, Response response) {
        Gson gson = new Gson();

        Animal registro = gson.fromJson(request.body(), Animal.class);
       
        int id = this.animalDAO.getMaxId() + 1;  // Corrigido o getMaxId
        
        registro.setId(id);

        animalDAO.inserirAnimal(registro);

        response.status(201); // 201 Created
        return id;
    }


    public Object get(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        Animal animal = animalDAO.get(id);

        if (animal != null){
            Gson gson = new Gson();
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            // Retorna o objeto Animal como JSON
            return gson.toJson(animal);
        } else{
            response.status(404); // 404 Not Found
            return "Animal " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response){

        int id = Integer.parseInt(request.params(":id"));

        System.out.println("\n Id do animal atualizado: " + id);

        Animal animal = (Animal) animalDAO.get(id);

        if (animal != null){
            
            Gson gson = new Gson();

            Animal registroAtualizado = gson.fromJson(request.body(), Animal.class);

            // Atualizando os campos do objeto animal
            animal.setImagem(registroAtualizado.getImagem());
            animal.setNome(registroAtualizado.getNome());
            animal.setSexo(registroAtualizado.getSexo());
            animal.setIdade(registroAtualizado.getIdade());
            animal.setRaca(registroAtualizado.getRaca());
            animal.setVacinas(registroAtualizado.getVacinas());
            animal.setCastrado(registroAtualizado.getCastrado());
            animal.setHistoria(registroAtualizado.getHistoria());
            animal.setPorte(registroAtualizado.getPorte());
            animal.setEspecie(registroAtualizado.getEspecie());

            animal.setId(id); //Coloca o id do animal a ser atualizado


            animalDAO.atualizarAnimal(animal);  // Usando o objeto `animal` atualizado

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "Animal não encontrado.";
        }
    }

    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

        System.out.println("\n Id do animal removido: " + id);
        
        Animal animal = animalDAO.get(id);
        
        if (animal != null){
            animalDAO.excluirAnimal(id);
            response.status(200); // Sucesso
            return id;
        }else{
            response.status(404); // 404 Not Found
            return "Animal não encontrado.";
        }
    }

    public Object getAll(Request request, Response response){
        Gson gson = new Gson();

        response.header("Content-type", "application/json");
        response.header("Content-Encoding", "UTF-8");

        // Retorna a lista de animais como JSON
        return gson.toJson(animalDAO.getAnimais());
    }
}
