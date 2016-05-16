package be.krivi.ucll.ip.api.validation;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * Created by Krivi on 01/04/16.
 */
public class CommentTO{

    @NotBlank( message = "{NotBlank.CommentTO.message}" )
    @Size( min = 2, max = 140, message = "{Size.CommentTO.message}" )
    private String message;

    public CommentTO(){
    }

    public String getMessage(){
        return message;
    }

    public void setMessage( String message ){
        this.message = message;
    }
}
