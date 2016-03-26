package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.exception.DatabaseException;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public class NetworkRDB implements NetworkDB{

    @Override
    public Network getById( Integer id ) throws DatabaseException{
        return null;
    }

    @Override
    public Collection<Network> getAll() throws DatabaseException{
        return null;
    }

    @Override
    public Network add( Network network ) throws DatabaseException{
        return null;
    }

    @Override
    public void delete( Network network ) throws DatabaseException{
    }

    @Override
    public Network update( Network network ) throws DatabaseException{
        return null;
    }
}
