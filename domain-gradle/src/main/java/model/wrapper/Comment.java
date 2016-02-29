package model.wrapper;

import model.exception.DomainException;
import model.common.Entity;

/**
 * Created by Krivi on 16/02/16.
 */
public class Comment extends Entity{

    private String comment;

    public Comment( String comment ) throws DomainException{
        setComment( comment );
    }

    public String getComment(){
        return comment;
    }

    public void setComment( String comment ) throws DomainException{
        if( comment.length() < 2 )
            throw new DomainException( "Comment is too short" );
        if( comment.length() > 140 )
            throw new DomainException( "Comment is too long" );
        this.comment = comment;
    }
}
