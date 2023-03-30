package services.dataExtract;

import java.util.List;

import entities.Content;

public interface DataExtract {

    public List<Content> contentsExtractor (String json);

    
}
