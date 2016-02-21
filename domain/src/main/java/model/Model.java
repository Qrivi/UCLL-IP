package model;

import model.db.CommentDB;
import model.db.DatabaseFactory;
import model.exception.DatabaseException;
import model.wrapper.Comment;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public class Model{

    private CommentDB commentDB;

    public Model( String type ){
        commentDB = DatabaseFactory.createCommentDB( type );
    }

    public Comment getById( Integer id ) throws DatabaseException{
        return commentDB.getById( id );
    }

    public Collection<Comment> getAll() throws DatabaseException{
        return commentDB.getAll();
    }

    public void add( Comment comment ) throws DatabaseException{
        commentDB.add( comment );
    }

    public void delete( Comment comment ) throws DatabaseException{
        commentDB.delete( comment );
    }

    public void update( Comment comment ) throws DatabaseException{
        commentDB.update( comment );
    }
}
