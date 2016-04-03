package be.krivi.ucll.ip.domain.core;

import be.krivi.ucll.ip.domain.common.Entity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Krivi on 21/02/16.
 */
@javax.persistence.Entity
@Table(name = "password")
public class Password extends Entity implements Comparable<Password>{

    @NotEmpty(message = "{NotEmpty.Password.password}" )
    @Column( name = "password" )
    private String password;

    @Min( value = 0, message = "{Min.Passwords.upvotes}")
    @Column( name = "upvotes" )
    private int upvotes;

    @Min( value = 0, message = "{Min.Passwords.downvotes}")
    @Column( name = "downvotes" )
    private int downvotes;

    public Password(){}

    public Password( String password, int upvotes, int downvotes ){
        this.password = password;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public Password( String password ){
        this( password, 0, 0 );
    }

    public void upvote(){
        upvotes++;
    }

    public void downvote(){
        downvotes++;
    }

    public void removeUpvote(){
        upvotes--;
    }

    public void removeDownvote(){
        downvotes--;
    }

    public  int getScore(){
        return upvotes - downvotes;
    }

    public int getUpvotes(){
        return upvotes;
    }

    public int getDownvotes(){
        return downvotes;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public int compareTo( Password p ){
        Integer p1score = this.getScore();
        Integer p2score = p.getScore();

        return p2score.compareTo( p1score );
    }
}
