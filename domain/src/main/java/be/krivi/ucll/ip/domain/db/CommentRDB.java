package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.exception.DatabaseException;

import java.util.Collection;

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
    public Comment add( Comment comment ) throws DatabaseException{
        return null;
    }

    @Override
    public void delete( Comment comment ) throws DatabaseException{
    }

    @Override
    public Comment update( Comment comment ) throws DatabaseException{
        return null;
    }
}
