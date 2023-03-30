package services.dataExtract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entities.Content;
import services.JsonParse;

public class NasaDataExtract implements DataExtract {

    public List<Content> contentsExtractor (String json){

        //exibir e manipular os dados 

        var parser = new JsonParse();
        List <Map<String, String>> atributsList = parser.parse(json);

        List<Content> contents = new ArrayList<>();

        //popluar lista de conteudos

        for(Map<String, String> atributs : atributsList){
            String title = atributs.get("title");
            String urlImage = atributs.get("url");
            var content = new Content(title, urlImage);
            contents.add(content);
        }



        return contents;

    }
    
}
