package service;

import com.google.gson.Gson;
import dao.EnderecoDAO; // Altere para EnderecoDAO
import model.Endereco; // Altere para Endereco
import dao.PessoaDAO;
import spark.Request;
import spark.Response;

public class EnderecoService {
    
    private EnderecoDAO enderecoDAO; // Altere para EnderecoDAO
    private PessoaDAO pessoaDAO;
    public EnderecoService() {
        try {
            this.enderecoDAO = new EnderecoDAO(); // Altere para EnderecoDAO
        } catch (Exception e) {
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }

        try {
            this.pessoaDAO = new PessoaDAO(); // Altere para EnderecoDAO
        } catch (Exception e) {
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();
        Endereco registro = gson.fromJson(request.body(), Endereco.class); // Altere para Endereco

        // Gera o próximo id para o Endereco
        int id = this.enderecoDAO.getMaxId_endereco() + 1; // Altere para getMaxIdEndereco()
        int id_pessoa = this.pessoaDAO.getMaxId_pessoa();
        
        registro.setId_pessoa(id_pessoa);
        registro.setId_endereco(id); // Altere para setId_endereco
        
        System.out.println(registro);

        enderecoDAO.inserirEndereco(registro); // Altere para inserirEndereco

        response.status(201); // 201 Created
        return id;
    }

    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Endereco endereco = enderecoDAO.get(id); // Altere para Endereco

        if (endereco != null) {
            Gson gson = new Gson();
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            // Retorna o objeto Endereco como JSON
            return gson.toJson(endereco);
        } else {
            response.status(404); // 404 Not Found
            return "Endereco " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Endereco endereco = enderecoDAO.get(id); // Altere para Endereco

        if (endereco != null) {
            Gson gson = new Gson();
            Endereco registroAtualizado = gson.fromJson(request.body(), Endereco.class); // Altere para Endereco

            // Atualizando os campos do objeto endereco
            endereco.setBairro(registroAtualizado.getBairro()); // Altere para setBairro
            endereco.setRua(registroAtualizado.getRua()); // Altere para setRua
            endereco.setNumero(registroAtualizado.getNumero()); // Altere para setNumero
            endereco.setCidade(registroAtualizado.getCidade()); // Altere para setCidade
            endereco.setEstado(registroAtualizado.getEstado()); // Altere para setEstado
            endereco.setId_pessoa(registroAtualizado.getId_pessoa()); // Altere para setId_pessoa

            enderecoDAO.atualizarEndereco(endereco); // Altere para atualizarEndereco

            return id;
        } else {
            response.status(404); // 404 Not Found
            return "Endereco não encontrado.";
        }
    }

    public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Endereco endereco = enderecoDAO.get(id); // Altere para Endereco

        if (endereco != null) {
            enderecoDAO.excluirEndereco(id); // Altere para excluirEndereco
            response.status(200); // Sucesso
            return id;
        } else {
            response.status(404); // 404 Not Found
            return "Endereco não encontrado.";
        }
    }

    public Object getAll(Request request, Response response) {
        Gson gson = new Gson();

        response.header("Content-type", "application/json");
        response.header("Content-Encoding", "UTF-8");

        // Retorna a lista de endereços como JSON
        return gson.toJson(enderecoDAO.getEnderecos()); // Altere para getEnderecos
    }
}
