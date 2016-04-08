package be.krivi.ucll.ip.domain.core;

import be.krivi.ucll.ip.domain.common.Entity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 * Created by Krivi on 21/02/16.
 */
@javax.persistence.Entity
@Table( name = "password" )
public class Password extends Entity implements Comparable<Password>{

    @NotBlank( message = "{NotBlank.Password.password}" )
    @Column( name = "password" )
    private String password;

    @Min( value = 0, message = "{Min.Passwords.upvotes}" )
    @Column( name = "upvotes" )
    private int upvotes;

    @Min( value = 0, message = "{Min.Passwords.downvotes}" )
    @Column( name = "downvotes" )
    private int downvotes;

    public Password(){
    }

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

    public Integer getScore(){
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
        //TODO why are passwords not sorted in TreeSet in ProtectedNetwork?
        return this.getScore().compareTo( p.getScore() );
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        if( !super.equals( o ) ) return false;

        Password password1 = (Password)o;

        return password.equals( password1.password );
    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
