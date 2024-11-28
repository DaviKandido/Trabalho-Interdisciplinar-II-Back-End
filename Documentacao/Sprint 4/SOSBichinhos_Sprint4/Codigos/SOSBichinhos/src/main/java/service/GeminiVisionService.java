package service;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GeminiVisionService {

    private static final String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro-latest:generateContent?key="; // Endpoint da API da Gemini
    private static final String clientId = "1019454710926-r1hjkhgqjf33ku1ei77chkqjmsq7qs02.apps.googleusercontent.com"; //YOUR_CLIENT_ID 
    private static final String clientSecret = "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"; //YOUR_CLIENT_SECRET
    private static final String accessToken = "AIzaSyCcexpkqT-72ynmMfCDtb4x3Pp8jBAZbzU"; //ACCESS_TOKEN

    public static String analyzeImage(String imageUrl) throws Exception {
        // URL para análise de imagem
        String analyzeUrl = endpoint + accessToken;

        // Configurando a requisição
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(analyzeUrl);

        // Adiciona o header da autenticação e o Content-Type
        request.setHeader("Ocp-Apim-Subscription-Key", accessToken);
        request.setHeader("Content-Type", "application/json");

        // Baixar a imagem da URL
        URL url = new URL(imageUrl);
        URLConnection connection = url.openConnection();
        
        // Detectar o tipo MIME da imagem
        String mimeType = connection.getContentType(); // Ex: image/jpeg ou image/png
        
        // Ler os bytes da imagem
        InputStream inputStream = connection.getInputStream();
        byte[] imageBytes = inputStream.readAllBytes();
        inputStream.close();

        // Codificar os bytes em Base64
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // System.out.println("\n" + base64Image + "\n"); //Teste de Base64
        
        // Corpo da solicitação JSON
        String body = "{"
            + "\"contents\": ["
            + "{"
            + "\"role\": \"user\","
            + "\"parts\": ["
            + "{"
            + "\"text\": \"Analise a imagem para identificar as seguintes características do animal:\\n"
            + "- Espécie: estritamente uma das seguintes opções – 'Cachorro', 'Gato', 'Coelho', 'Roedor', ou 'Outro', caso não seja um animal conhecido, escreva 'Desconhecido'.\\n"
            + "- Raça: se identificável.\\n"
            + "- Idade aproximada: em uma faixa como 'Filhote', 'Adulto', 'Idoso'.\\n"
            + "- Porte: 'Pequeno', 'Médio', 'Grande'.\\n"
            + "- Temperamento: com base nas características gerais da espécie e raça, caso possível.\\n"
            + "- Necessidades Especiais: indique condições comuns à raça, se aplicável.\\n"
            + "- Estado de Saúde: observações gerais com base na imagem.\\n"
            + "- Características Gerais: atributos visíveis como pelagem, cor, e outros detalhes observáveis.\\n"
            + "Caso algum atributo não possa ser identificado na imagem escreva: 'Desconhecido'.\\n"
            + "Retorne as informações em um JSON estruturado conforme o exemplo:\\n"
            + "{\\n  \\\"especie\\\": \\\"Cachorro\\\",\\n  \\\"raca\\\": \\\"Pug\\\",\\n  \\\"idade\\\": \\\"Filhote (aproximadamente 2-4 meses)\\\",\\n  \\\"porte\\\": \\\"Pequeno\\\",\\n  \\\"temperamento\\\": \\\"Dócil, brincalhão e afetuoso\\\",\\n  \\\"necessidades_especiais\\\": \\\"Pugs podem ter problemas respiratórios e tendência à obesidade\\\",\\n  \\\"estado_de_saude\\\": \\\"Aparenta estar saudável na imagem\\\",\\n  \\\"caracteristicas_gerais\\\": \\\"Pelagem curta bege claro, focinho curto e enrugado\\\"\\n}\""
            + "},"
            + "{"
            + "\"inlineData\": {"
            + "\"mimeType\": \"" + mimeType + "\","
            + "\"data\": \"" + base64Image + "\""
            + "}"
            + "}"
            + "]"
            + "}"
            + "],"
            + "\"generationConfig\": {"
            + "\"temperature\": 0.7,"
            + "\"topP\": 0.9,"
            + "\"topK\": 40,"
            + "\"candidateCount\": 1,"
            + "\"maxOutputTokens\": 1000,"
            + "\"responseMimeType\": \"text/plain\""
            + "}"
            + "}";

    

        StringEntity requestEntity = new StringEntity(body);
        request.setEntity(requestEntity);

        // Faz a requisição
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        // Verifica o código de status da resposta
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            String result = EntityUtils.toString(entity);
            return result;
        } else {
            // Trata erros
            String errorMessage = EntityUtils.toString(entity);
            throw new Exception("Erro na API Gemini Vision: " + statusCode + " - " + errorMessage);
        }
    }
}
