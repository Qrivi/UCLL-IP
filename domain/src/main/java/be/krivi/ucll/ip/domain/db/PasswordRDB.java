package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.exception.DatabaseException;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public class PasswordRDB implements PasswordDB{

    @Override
    public Password getById( Integer id ) throws DatabaseException{
        return null;
    }

    @Override
    public Collection<Password> getAll() throws DatabaseException{
        return null;
    }

    @Override
    public Password add( Password password ) throws DatabaseException{
        return null;
    }

    @Override
    public void delete( Password password ) throws DatabaseException{
    }

    @Override
    public Password update( Password password ) throws DatabaseException{
        return null;
    }
}
