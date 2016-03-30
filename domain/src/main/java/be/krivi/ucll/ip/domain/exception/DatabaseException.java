package be.krivi.ucll.ip.domain.exception;

/**
 * Created by Krivi on 12/02/16.
 */
public class DatabaseException extends RuntimeException{

    public DatabaseException(){
        super();
    }

    public DatabaseException( String message ){
        super( message );
    }

    public DatabaseException( Throwable throwable ){
        super( throwable );
    }

    public DatabaseException( String message, Throwable exception ){
        super( message, exception );
    }

}
