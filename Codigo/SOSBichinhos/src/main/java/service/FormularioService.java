package service;

import com.google.gson.Gson;
import dao.FormularioDAO;
import model.Formulario;
import spark.Request;
import spark.Response;

public class FormularioService {

    private FormularioDAO formularioDAO;
    private Gson gson;

    public FormularioService() {
        try {
            this.formularioDAO = new FormularioDAO();
            this.gson = new Gson();
        } catch (Exception e) {
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object insert(Request request, Response response) {
        Formulario form = gson.fromJson(request.body(), Formulario.class);
        int id = this.formularioDAO.getMaxId() + 1;
        form.setId(id);
        System.out.println(form);

        formularioDAO.insert(form);

        response.status(201); // 201 Created
        return id;
    }

    public Object get(Request request, Response response) {
        try {
            int id = Integer.parseInt(request.params(":id"));
            Formulario formulario = formularioDAO.get(id);

            if (formulario != null) {
                response.status(200); // success
                response.header("Content-Type", "application/json");
                response.header("Content-Encoding", "UTF-8");
                return gson.toJson(formulario);
            } else {
                response.status(404); // 404 Not found
                return "Formulario " + id + " não encontrado.";
            }
        } catch (NumberFormatException e) {
            response.status(400); // Bad request
            return "ID inválido.";
        } catch (Exception e) {
            response.status(500); // Server error
            return "Erro ao buscar o formulário: " + e.getMessage();
        }
    }

    public Object getAll(Request request, Response response) {
        response.header("Content-Type", "application/json");
        response.header("Content-Encoding", "UTF-8");
        return gson.toJson(formularioDAO.getForms());
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Formulario formulario = (Formulario) formularioDAO.get(id);

        if (formulario != null) {
            Formulario update = gson.fromJson(request.body(), Formulario.class);

            formulario.setNome(update.getNome());
            formulario.setIdade(update.getIdade());
            formulario.setSexo(update.getSexo());
            formulario.setCidade(update.getCidade());
            formulario.setCiente(update.getCiente());
            formulario.setTeveAnimal(update.getTeveAnimal());
            formulario.setPermissao(update.getPermissao());
            formulario.setApLiberado(update.getApLiberado());
            formulario.setAnimalSozinho(update.getAnimalSozinho());
            formulario.setAondeFica(update.getAondeFica());
            formulario.setTelefone(update.getTelefone());
            formulario.setEmail(update.getEmail());
            formulario.setNomeAnimal(update.getNomeAnimal());
            formulario.setUrlImagem(update.getUrlImagem());
            formulario.setMoradia(update.getMoradia());

            formularioDAO.update(formulario);
            response.status(200); // success
            return "Registro (ID " + formulario.getId() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            return "Registro (ID " + id + ") não encontrado!";
        }
    }

    public Object delete(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Formulario formulario = formularioDAO.get(id);
        
        if (formulario != null) {
            formularioDAO.delete(id);
            response.status(204); 
            return ""; 
        } else {
            response.status(404); // 404 Not Found
            return "Registro (" + id + ") não encontrado!";
        }
    }
}
