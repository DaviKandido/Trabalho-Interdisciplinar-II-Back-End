import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Exercício 4: Computação em Nuvem e IA como Serviço
 * 
 * @author Davi Cândido de Almeida
 * @version 2.0, 18/10/2024
 */

public class Main {
    private static final String subscriptionKey = "2c7026acd79a44fa8e653075af0ce7da"; //MY_SUBSCRIPTION_KEY
    private static final String endpoint = "https://analisedetextososbichinhos.cognitiveservices.azure.com"; //MY_API_ENDPOINT
    
    public static void main(String[] args) {
        String textToAnalyze = "Estou trabalhando em um projeto maravilhoso chamado SOSBichinhos ele ajudará muitas ONGs e pessoas a encontrar bichinhos abandonados e assim contribuir para um futuro melhor.\n"
        		+ "\n"
        		+ "Desenvolver esse projeto tem sido bem desafiador, nem sempre temos todo o conhecimento necessário para alcançar o resultado desejado e por isso temos muitas vezes que recorrer a ajudas de colegas de trabalho para que assim a carga possa ser mais leve.\n"
        		+ "\n"
        		+ "Acredito que o desenvolvimento de meu projeto me ajudara a me desenvolver academicamente e profissionalmente, e assim adquirir novos conhecimentos e habilidade pelo caminho, busca estar cada vez mais preparado para cada desafio que vier pela frente\n"
        		+ "\n"
        		+ "Não importa quanto difícil for vou sempre lutar pelo que acredito e defendo, irei alcançar meu sonhos e objetos, no final sei que que sempre valerá a pena!";

        HttpClient client = HttpClient.newHttpClient();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Cria o Gson com formatação bonita

        Map<String, Object> document = new HashMap<>();
        document.put("language", "pt");
        document.put("id", "1");
        document.put("text", textToAnalyze);

        Map<String, Object> documents = new HashMap<>();
        documents.put("documents", new Object[]{document});

        String json = gson.toJson(documents);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint + "/text/analytics/v3.0/sentiment"))
            .header("Content-Type", "application/json")
            .header("Ocp-Apim-Subscription-Key", subscriptionKey)
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        try {
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(response -> {
                    // Formata e imprime a resposta JSON de forma organizada
                    Object jsonResponse = gson.fromJson(response, Object.class); // Converte a resposta para um objeto
                    String prettyJson = gson.toJson(jsonResponse); // Converte o objeto de volta para JSON formatado
                    System.out.println(prettyJson); // Imprime a resposta formatada
                })
                .join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
