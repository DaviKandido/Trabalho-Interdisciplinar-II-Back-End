package app;
import service.*;

public class TesteApi {

    private static AzureVisionService azureVisionService = new AzureVisionService(); // Service analisa animal

    private static GeminiVisionService geminiVisionService = new GeminiVisionService(); // Service analisa animal

    public static void main(String[] args) {

        String url = "https://i.ibb.co/sVRXCSp/Imag-Inicial2.jpg";

        try{

             System.out.println("\n-------------- Analise feita pela Azure ---------------\n");

             System.out.println(azureVisionService.analyzeImage(url));

        }catch(Exception e){
            e.printStackTrace();
        }


        
        try{
            
            System.out.println("\n-------------- Analise feita pela Gemini ---------------\n");

            System.out.println(geminiVisionService.analyzeImage(url));

       }catch(Exception e){
           e.printStackTrace();
       }
        
    }
}
