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

    @NotEmpty(message = "{NotNull.Password.password}" )
    @Column( name = "password" )
    private String password;

    @NotNull(message = "{NotNull.Password.timestamp}")
    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "timestamp" )
    private Date timestamp;

    @Min( value = 0, message = "{Min.Passwords.upvotes}")
    @Column( name = "upvotes" )
    private int upvotes;

    @Min( value = 0, message = "{Min.Passwords.downvotes}")
    @Column( name = "downvotes" )
    private int downvotes;

    /* The user that created the password needs to be able to remove it
    -> wait for Spring, which will handle auth
    private int user_id; ?
    private int network_id
    */

    public Password(){}

    public Password( String password, Date timestamp, int upvotes, int downvotes ){
        this.password = password;
        this.timestamp = timestamp;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public Password( String password ){
        this( password, new Date(), 0, 0 );
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

    public int getScore(){
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

    public Date getTimestamp(){
        return timestamp;
    }

    @Override
    public int compareTo( Password p ){
        Integer p1score = this.getScore();
        Integer p2score = p.getScore();

        return p1score.compareTo( p2score );
    }
}
