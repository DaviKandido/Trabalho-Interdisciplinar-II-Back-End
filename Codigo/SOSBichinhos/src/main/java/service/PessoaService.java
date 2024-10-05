package service;

import com.google.gson.Gson;
import dao.PessoaDAO;
import model.Pessoa;
import spark.Request;
import spark.Response;

public class PessoaService {
    
    private PessoaDAO pessoaDAO;

    public PessoaService() {
        try {
            this.pessoaDAO = new PessoaDAO();
        } catch (Exception e) {
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();
        Pessoa registro = gson.fromJson(request.body(), Pessoa.class);

        int id = this.pessoaDAO.getMaxId_pessoa() + 1; // Corrigido o getMaxId
        registro.setId(id);
        System.out.println(registro);

        pessoaDAO.inserirpessoa(registro);

        response.status(201); // 201 Created
        return id;
    }

    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Pessoa pessoa = pessoaDAO.get(id);

        if (pessoa != null) {
            Gson gson = new Gson();
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            // Retorna o objeto Pessoa como JSON
            return gson.toJson(pessoa);
        } else {
            response.status(404); // 404 Not Found
            return "Pessoa " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Pessoa pessoa = (Pessoa) pessoaDAO.get(id);

        if (pessoa != null) {
            Gson gson = new Gson();
            Pessoa registroAtualizado = gson.fromJson(request.body(), Pessoa.class);

            // Atualizando os campos do objeto pessoa
            pessoa.setNome(registroAtualizado.getNome());
            pessoa.setEmail(registroAtualizado.getEmail());
            pessoa.setSenha(registroAtualizado.getSenha());
            pessoa.setMoradia(registroAtualizado.getMoradia());
            pessoa.setImagem(registroAtualizado.getImagem());
            pessoa.setIdade(registroAtualizado.getIdade());
            pessoa.setSexo(registroAtualizado.getSexo());

            pessoaDAO.atualizarpessoa(pessoa); // Usando o objeto `pessoa` atualizado

            return id;
        } else {
            response.status(404); // 404 Not Found
            return "Pessoa não encontrada.";
        }
    }

    public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        Pessoa pessoa = pessoaDAO.get(id);

        if (pessoa != null) {
            pessoaDAO.excluirpessoa(id);
            response.status(200); // Sucesso
            return id;
        } else {
            response.status(404); // 404 Not Found
            return "Pessoa não encontrada.";
        }
    }

    public Object getAll(Request request, Response response) {
        Gson gson = new Gson();

        response.header("Content-type", "application/json");
        response.header("Content-Encoding", "UTF-8");

        // Retorna a lista de pessoas como JSON
        return gson.toJson(pessoaDAO.getpessoas());
    }
}
