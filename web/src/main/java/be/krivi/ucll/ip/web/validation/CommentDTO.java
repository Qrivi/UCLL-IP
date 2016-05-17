package be.krivi.ucll.ip.web.validation;

import be.krivi.ucll.ip.domain.core.Comment;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Krivi on 01/04/16.
 */
public class CommentDTO{

    @NotBlank( message = "{NotBlank.CommentDTO.message}" )
    @Size( min = 2, max = 140, message = "{Size.CommentDTO.message}" )
    private String message;

    public CommentDTO(){
    }

    public String getMessage(){
        return message;
    }

    public void setMessage( String message ){
        this.message = message;
    }
}
