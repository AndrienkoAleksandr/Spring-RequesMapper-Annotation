package org.eclipse.che.examples.dao;

import java.util.List;

import org.eclipse.che.examples.exceptions.ServerException;
import org.eclipse.che.examples.model.Image;

public interface ImageDao {
    List<Image> getImages() throws ServerException;
    
    Image getImageByName(String name) throws ServerException;
    
    void saveImage(Image image) throws ServerException;
    
    void delete(Image image) throws ServerException;
}
