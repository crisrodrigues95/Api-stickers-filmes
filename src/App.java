import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws Exception {
        

        // Fazer uma conexão HTTp

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        


        //Selecionar somente os dados que interessam (titulo, postar, nota)
        var parser = new JsonParse();

        List <Map<String, String>> listadeFilmes = parser.parse(body);
        


        //exibir e manipular os dados 

        
        for (Map<String, String> filme : listadeFilmes){

            String urlimage = filme.get("image");

            InputStream inputStream = new URL(urlimage).openStream();

            String imageName = ((filme.get("title")).replace(":", "-")+".png");

            var generator = new StickerGenerator();
            double stars = Double.parseDouble((filme.get("imDbRating")));
            generator.creating(inputStream, imageName, stars);

            System.out.println("#"+filme.get("rank")+":");
            System.out.println(UI.negrito("Título: ")+filme.get("title"));
            System.out.println(UI.color(stars)+("Classificação: ")+ stars+UI.ANSI_RESET) ;
            for (int i = 1; i<= Math.round(stars); i++){
                System.out.print("⭐");

            }
            System.out.println("\n");
            
            

        }


        
    }
}
