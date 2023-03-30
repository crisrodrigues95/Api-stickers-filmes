package entities;

public class Content {

    private final String title;
    private final String urlImage;

    

    public Content(String title, String utlImage) {
        this.title = title;
        this.urlImage = utlImage;
    }
    public String getTitle() {
        return title;
    }
    public String getUrlImage() {
        return urlImage;
    }
     
    
}
