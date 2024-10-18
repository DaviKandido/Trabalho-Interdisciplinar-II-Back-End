package service;

import com.google.gson.Gson;

import dao.AnimalDAO;
import dao.TagsAnimalDAO;
import model.Animal;
import model.TagsAnimal;

import java.io.IOException;
import spark.Request;
import spark.Response;

public class TagsAnimalService {
    
    private TagsAnimalDAO tagsAnimalDAO;

    public TagsAnimalService(){
        try{
            this.tagsAnimalDAO = new TagsAnimalDAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();

        TagsAnimal registro = gson.fromJson(request.body(), TagsAnimal.class);
       
        int idTag = this.tagsAnimalDAO.getMaxId() + 1;  // Corrigido o getMaxId
        int idAnimal = this.tagsAnimalDAO.getMaxIdAnimal();  // Corrigido o getMaxId
        
        registro.setId_tagAnimal(idTag);
        registro.setId_animal(idAnimal);

        System.out.println(registro);

        tagsAnimalDAO.inserirTagAnimal(registro);

        response.status(201); // 201 Created
        return idTag;
    }

    public Object get(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        
        TagsAnimal[] tagsAnimal = tagsAnimalDAO.get(id);

        if (tagsAnimal != null){
            Gson gson = new Gson();
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            // Retorna o objeto Animal como JSON
            return gson.toJson(tagsAnimal);
        } else{
            response.status(404); // 404 Not Found
            return "Animal " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

        TagsAnimal[] tagsAnimal = (TagsAnimal[]) tagsAnimalDAO.get(id);

        if (tagsAnimal != null){
            
            Gson gson = new Gson();

            TagsAnimal registroAtualizado = gson.fromJson(request.body(), TagsAnimal.class);

            // Atualizando os campos do objeto animal
            tagsAnimal[0].setConteudo_tag(registroAtualizado.getConteudo_tag());
            tagsAnimal[1].setConteudo_tag(registroAtualizado.getConteudo_tag());
            tagsAnimal[2].setConteudo_tag(registroAtualizado.getConteudo_tag());


            tagsAnimalDAO.atualizarTagAnimal(tagsAnimal[0]);  // Usando o objeto `animal` atualizado
            tagsAnimalDAO.atualizarTagAnimal(tagsAnimal[1]);
            tagsAnimalDAO.atualizarTagAnimal(tagsAnimal[2]);

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "Animal não encontrado.";
        }
    }

    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        
        TagsAnimal[] tagsAnimal = tagsAnimalDAO.get(id);
        
        if (tagsAnimal != null){
            tagsAnimalDAO.excluirTagAnimal(id);
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

