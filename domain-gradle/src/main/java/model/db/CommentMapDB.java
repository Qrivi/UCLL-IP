package model.db;

import model.exception.DatabaseException;
import model.wrapper.Comment;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Krivi on 21/02/16.
 */
public class CommentMapDB implements CommentDB{

    private Map<Integer, Comment> map;

    public CommentMapDB(){
        map = new HashMap<>();
    }

    @Override
    public Comment getById( Integer id ) throws DatabaseException{
        if( !map.containsKey( id ) )
            throw new DatabaseException( "Comment does not exist" );
        return map.get( id );
    }

    @Override
    public Collection<Comment> getAll() throws DatabaseException{
        return map.values();
    }

    @Override
    public void add( Comment comment ) throws DatabaseException{
        if( comment == null )
            throw new DatabaseException( "Cannot add empty comment data" );
        map.put( comment.getId(), comment );
    }

    @Override
    public void delete( Comment comment ) throws DatabaseException{
        if( !map.containsKey( comment.getId() ) )
            throw new DatabaseException( "Comment to delete does not exist" );
        map.remove( comment.getId() );
    }

    @Override
    public void update( Comment comment ) throws DatabaseException{
        add( comment );
    }
}
