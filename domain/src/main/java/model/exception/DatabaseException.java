package model.exception;

/**
 * Created by Krivi on 12/02/16.
 */
public class DatabaseException extends Exception{

    public DatabaseException(){
        super();
    }

    public DatabaseException( String message ){
        super( message );
    }

    public DatabaseException( String message, Throwable exception ){
        super( message, exception );
    }

}
