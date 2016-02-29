package org.eclipse.che.examples.model;

public class Image {
    
    private final String name;
    private final byte[] data;
    
    public Image(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
    
    public String getName() {
        return name;
    }
    
    public byte[] getData() {
        return data;
    }
}