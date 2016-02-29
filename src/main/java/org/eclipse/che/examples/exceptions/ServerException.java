package org.eclipse.che.examples.exceptions;

public class ServerException extends Exception {
    
    public ServerException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
      public ServerException(String message) {
        super(message);
    }
}
