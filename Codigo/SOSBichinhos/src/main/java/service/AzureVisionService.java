package service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AzureVisionService {

    private static final String subscriptionKey = "f7bbe748520e451e9e08c3c7b08ad272"; //MY_API_Key
    private static final String endpoint = "https://analisaanimal.cognitiveservices.azure.com/"; //My_Endpoint

    public static String analyzeImage(String imageUrl) throws Exception {
        // URL para análise de imagem
        String analyzeUrl = endpoint + "/vision/v3.1/analyze?visualFeatures=Categories,Description,Color,Tags,Objects&language=pt";

        // Configurando a requisição
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(analyzeUrl);

        // Adiciona o header da API Key
        request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);
        request.setHeader("Content-Type", "application/json");

        // Corpo da requisição
        String body = "{ \"url\": \"" + imageUrl + "\" }";
        StringEntity requestEntity = new StringEntity(body);
        request.setEntity(requestEntity);

        // Faz a requisição
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        // Fecha a conexão
        response.close();
        httpClient.close();

        
        return result;
    }
}

