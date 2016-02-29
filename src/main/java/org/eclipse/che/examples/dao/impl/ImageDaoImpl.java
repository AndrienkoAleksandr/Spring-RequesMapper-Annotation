package org.eclipse.che.examples.dao.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.che.examples.dao.ImageDao;
import org.eclipse.che.examples.exceptions.ServerException;
import org.eclipse.che.examples.model.Image;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ImageDaoImpl implements ImageDao, ApplicationContextAware {

    private static final String STORAGE = "storage/files";
    private ApplicationContext  applicationContext;
    private Path   storage;
    private String rootPath;


    @Override
    public List<Image> getImages() throws org.eclipse.che.examples.exceptions.ServerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Image getImageByName(String name) throws org.eclipse.che.examples.exceptions.ServerException {
        byte[] data = null;
        try {
            Path path = Paths.get(getStoragePath(), name);
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new ServerException("Unable to save icon", e);
        }
        return new Image(name, data);
    }

    @Override
    public void saveImage(Image image) throws ServerException {
        try {
            Path path = Paths.get(getStoragePath(), image.getName());
            Files.write(path, image.getData());
        } catch (IOException e) {
            throw new ServerException("Unable to save icon", e);
        }
    }

    @Override
    public void delete(Image image) throws org.eclipse.che.examples.exceptions.ServerException {
        // TODO Auto-generated method stub
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    private String getStoragePath() throws ServerException {
        if (rootPath != null) {
            return rootPath;
        } 
        try {
            rootPath = applicationContext.getResource("").getURL().getPath();
            storage = Paths.get(rootPath, STORAGE);
            if (!Files.exists(storage)) {
                Files.createDirectories(storage);
            }
            return rootPath;
        } catch (Exception e) {
            throw new ServerException("Unable create local storage for files", e);
        }
    }
}
