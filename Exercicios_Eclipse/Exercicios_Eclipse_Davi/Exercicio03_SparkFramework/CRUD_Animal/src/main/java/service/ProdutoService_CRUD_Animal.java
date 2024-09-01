package service;


import dao.CRUD_Animal_DAO;
import model.Animal;
import java.io.IOException;
import java.util.Iterator;
import model.Animal;
import spark.Request;
import spark.Response;

public class ProdutoService_CRUD_Animal {
    
	private CRUD_Animal_DAO animalDAO;

    public ProdutoService_CRUD_Animal(){
        try{
            animalDAO = new CRUD_Animal_DAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        String url = request.queryParams("url-animal");
        String  nome = request.queryParams("nome-animal");
        char sexo = (request.queryParams("sexo-animal")).charAt(0);
        String  idade = request.queryParams("idade-animal");
        String  raca = request.queryParams("raca-animal");
        String  vacinas = request.queryParams("vacinas-animal");
        String  cidade = request.queryParams("cidades-animal");
        boolean cadastrado = Boolean.parseBoolean(request.queryParams("cadastrado-animal"));
        String historia = request.queryParams("historia-animal");
        String  tags = request.queryParams("tags-animal");
        char porte = (request.queryParams("porte-animal")).charAt(0);
        String especie = request.queryParams("especie-animal");

        int id = animalDAO.getMaxId() + 1;

        Animal animal = new Animal(id, url, nome, sexo, idade, raca, vacinas, cidade, cadastrado, historia, tags,	porte, especie );

        animalDAO.inserirAnimal(animal);

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
            return "Produto" + id + "não encontrado";
        }
    }

    public Object update(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

        Animal animal = (Animal) animalDAO.get(id);

        if (animal != null){
            animal.setUrl(request.queryParams("url-animal"));
            animal.setNome(request.queryParams("nome-animal"));
            animal.setSexo((request.queryParams("sexo-animal")).charAt(0));
            animal.setIdade(request.queryParams("idade-animal"));
            animal.setRaca(request.queryParams("raca-animal"));
            animal.setVacinas(request.queryParams("vacinas-animal"));
            animal.setCidade(request.queryParams("cidade-animal"));
            animal.setCadastrado(true);
            animal.setHistoria(request.queryParams("historia-animal"));
            animal.setTags(request.queryParams("tags-animal"));
            animal.setPorte((request.queryParams("porte-animal")).charAt(0));
            animal.setEspecie(request.queryParams("especie-animal"));

            animalDAO.atualizarAnimal(animal);  

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "Produto não encontrado.";
        }
    }


    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

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
