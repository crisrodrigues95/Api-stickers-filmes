package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.management.RuntimeErrorException;

public class ClientHttp {

    public String dataSearcher(String url){

        // Fazer uma conexão HTTp
        
        try {
            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString()) ;
            String body = response.body();
            return body;
        } catch (IOException | InterruptedException e) {
            
            throw new RuntimeErrorException(null);
        }
        
        
    }
    
}
