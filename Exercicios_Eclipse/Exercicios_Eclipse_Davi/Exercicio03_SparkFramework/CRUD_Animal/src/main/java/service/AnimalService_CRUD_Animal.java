package service;

import com.google.gson.Gson;

import dao.AnimalDAO;
import model.Animal;
import java.io.IOException;
import spark.Request;
import spark.Response;

public class AnimalService_CRUD_Animal {
    
    private AnimalDAO animalDAO;

    public AnimalService_CRUD_Animal(){
        try{
            this.animalDAO = new AnimalDAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();

        Animal registro = gson.fromJson(request.body(), Animal.class);
       
        int id = this.animalDAO.getMaxId() + 1;  // Corrigido o getMaxId
        
        registro.setId(id);
        System.out.println(registro);

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

        Animal animal = (Animal) animalDAO.get(id);

        if (animal != null){
            
            Gson gson = new Gson();

            Animal registroAtualizado = gson.fromJson(request.body(), Animal.class);

            // Atualizando os campos do objeto animal
            animal.setUrl(registroAtualizado.getUrl());
            animal.setNome(registroAtualizado.getNome());
            animal.setSexo(registroAtualizado.getSexo());
            animal.setIdade(registroAtualizado.getIdade());
            animal.setRaca(registroAtualizado.getRaca());
            animal.setVacinas(registroAtualizado.getVacinas());
            animal.setCidade(registroAtualizado.getCidade());
            animal.setCadastrado(registroAtualizado.getCadastrado());
            animal.setHistoria(registroAtualizado.getHistoria());
            animal.setTags(registroAtualizado.getTags());
            animal.setPorte(registroAtualizado.getPorte());
            animal.setEspecie(registroAtualizado.getEspecie());

            animalDAO.atualizarAnimal(animal);  // Usando o objeto `animal` atualizado

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "Animal não encontrado.";
        }
    }

    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        
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
