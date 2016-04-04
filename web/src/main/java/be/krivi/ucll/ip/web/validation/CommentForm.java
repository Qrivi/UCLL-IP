package be.krivi.ucll.ip.web.validation;

import be.krivi.ucll.ip.domain.core.Comment;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Krivi on 01/04/16.
 */
public class CommentForm{

    private String ssid;

    @NotEmpty(message = "{NotEmpty.CommentForm.message}" )
    @Size( min = 2, max = 140, message = "{Size.CommentForm.message}" )
    private String message;

    private Set<Comment> comments;

    public CommentForm(){
    }

    public String getSsid(){
        return ssid;
    }

    public void setSsid( String ssid ){
        this.ssid = ssid;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage( String message ){
        this.message = message;
    }

    public Set<Comment> getComments(){
        return comments;
    }

    public void setComments( Set<Comment> comments ){
        this.comments = comments;
    }
}
