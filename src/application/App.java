package application;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import entities.Content;
import services.ClientHttp;
import services.StickerGenerator;
import services.dataExtract.DataExtract;
import services.dataExtract.NasaDataExtract;


public class App {

    public static void main(String[] args) throws Exception {
        

        
        


        
       

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        var http = new ClientHttp();
        String json = http.dataSearcher(url);

        // Exibir e manipular dados

       DataExtract extractor = new NasaDataExtract();
       List<Content> contents = extractor.contentsExtractor(json);
        


        

        
        for (Content content : contents){

            String urlimage = content.getUrlImage();
            String title = content.getTitle();

            InputStream inputStream = new URL(urlimage).openStream();

            String imageName = (title.replace(":", "-")+".png");

            var generator = new StickerGenerator();
            
            generator.creating(inputStream, imageName, title);

            System.out.println(title);
            System.out.println("\n");
            
            

        }


        
    }
}
