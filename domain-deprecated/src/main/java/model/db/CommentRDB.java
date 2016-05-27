package model.db;

import model.exception.DatabaseException;
import model.wrapper.Comment;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Krivi on 21/02/16.
 */
public class CommentRDB implements CommentDB{

    @Override
    public Comment getById( Integer id ) throws DatabaseException{
        return null;
    }

    @Override
    public Collection<Comment> getAll() throws DatabaseException{
        return null;
    }

    @Override
    public void add( Comment comment ) throws DatabaseException{
    }

    @Override
    public void delete( Comment comment ) throws DatabaseException{
    }

    @Override
    public void update( Comment comment ) throws DatabaseException{
    }
}
