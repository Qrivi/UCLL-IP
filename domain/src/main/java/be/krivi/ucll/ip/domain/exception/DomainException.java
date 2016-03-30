package be.krivi.ucll.ip.domain.exception;

/**
 * Created by Krivi on 12/02/16.
 */
public class DomainException extends Exception{

    public DomainException(){
        super();
    }

    public DomainException( String message ){
        super( message );
    }

    public DomainException( Throwable throwable ){
        super( throwable );
    }

    public DomainException( String message, Throwable exception ){
        super( message, exception );
    }

}
