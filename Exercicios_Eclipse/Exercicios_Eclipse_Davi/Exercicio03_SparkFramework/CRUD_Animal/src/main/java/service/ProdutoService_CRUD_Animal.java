package service;

import com.google.gson.Gson;
import dao.AnimalDAO;
import model.Animal;
import java.io.IOException;
import spark.Request;
import spark.Response;

public class ProdutoService_CRUD_Animal {
    
	private AnimalDAO animalDAO;

    public ProdutoService_CRUD_Animal(){
        try{
            this.animalDAO = new AnimalDAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();

        Animal registro = gson.fromJson(request.body(), Animal.class);
       

        int id = this.animalDAO.getMaxId() + 1;
        
        registro.setId(id);
        System.out.println(registro);

        animalDAO.inserirAnimal(registro);

        response.status(201); // 201 Created
        return id;
    }

    public Object get(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

        Animal animal = (Animal) animalDAO.get(id);

        if (animal != null){
            response.header("Content-Type", "application/xml");
            response.header("Content-Encoding", "UTF-8" );

            return "<Animal>\n" +
                    "\t<id>" + animal.getId() + "</id>\n" +
                    "\t<url>" + animal.getUrl() + "</url>\n" +
                    "\t<nome>" + animal.getNome() + "</nome>\n" +
                    "\t<sexo>" + animal.getSexo() + "</sexo>\n" +
                    "\t<idade>" + animal.getIdade() + "</idade>\n" +
                    "\t<raca>" + animal.getRaca() + "</raca>\n" +
                    "\t<vacinas>" + animal.getVacinas() + "</vacinas>\n"
                    + "\t<cadastrado>" + (animal.getCadastrado() ? "TRUE" : "FALSE") + "</cadastrado>\n"
                    + "\t<historia>" + animal.getHistoria() + "</historia>\n"
                    + "\t<tags>" + animal.getTags() + "</tags>\n"
                    + "\t<porte>" + animal.getPorte() + "</porte>\n"
                    + "\t<especie>" + animal.getEspecie() + "</especie>\n" 
                    + "</Animal>\n";
        } else{
            response.status(404); // 404 Not Found
            return "Produto " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

        Animal animal = (Animal) animalDAO.get(id);

        if (animal != null){
            animal.setUrl(request.queryParams("url_Animal"));
            animal.setNome(request.queryParams("nome_Animal"));
            animal.setSexo((request.queryParams("sexo_Animal")).charAt(0));
            animal.setIdade(request.queryParams("idade_Animal"));
            animal.setRaca(request.queryParams("raca_Animal"));
            animal.setVacinas(request.queryParams("vacinas_Animal"));
            animal.setCidade(request.queryParams("cidade_Animal"));
            animal.setCadastrado(true);
            animal.setHistoria(request.queryParams("historia_Animal"));
            animal.setTags(request.queryParams("tags_Animal"));
            animal.setPorte((request.queryParams("porte_Animal")).charAt(0));
            animal.setEspecie(request.queryParams("especie_Animal"));

            animalDAO.atualizarAnimal(animal);  

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "Produto não encontrado.";
        }
    }


    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.params("id"));

        Animal animal = (Animal) animalDAO.get(id);

        if (animal != null){

            animalDAO.excluirAnimal(id);

            response.status(200); //sucess
            return id;
        }else{
            response.status(404); // 404 Not Found
            return "Produto não encontrado.";
        }
    }


    public Object getAll(Request request, Response response){
        StringBuffer returnValue = new StringBuffer("<animais type=\"array\">");

        for(Animal animal: animalDAO.getAnimais()){

             returnValue.append("<Animal>\n" +
            "\t<id>" + animal.getId() + "</id>\n" +
            "\t<url>" + animal.getUrl() + "</url>\n" +
            "\t<nome>" + animal.getNome() + "</nome>\n" +
            "\t<sexo>" + animal.getSexo() + "</sexo>\n" +
            "\t<idade>" + animal.getIdade() + "</idade>\n" +
            "\t<raca>" + animal.getRaca() + "</raca>\n" +
            "\t<vacinas>" + animal.getVacinas() + "</vacinas>\n"
            + "\t<cadastrado>" + (animal.getCadastrado() ? "TRUE" : "FALSE") + ", '" + "</cadastrado>"
            + "\t<historia>" + animal.getHistoria() + "</historia>\n"
            + "\t<tags>" + animal.getTags() + "</tags>\n"
            + "\t<porte>" + animal.getPorte() + "</porte>\n"
            + "\t<especie>" + animal.getEspecie() + "</especie>" 
            + "</Animal>");
        }
        returnValue.append("</animais>");
        response.header("Content-type", "application/xml");
        response.header("Content-Encoding", "UTF-8");
        return returnValue.toString();

    }
}
