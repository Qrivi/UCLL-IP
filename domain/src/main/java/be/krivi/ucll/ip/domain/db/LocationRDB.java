package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.exception.DatabaseException;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public class LocationRDB implements LocationDB{

    @Override
    public Location getById( Integer id ) throws DatabaseException{
        return null;
    }

    @Override
    public Collection<Location> getAll() throws DatabaseException{
        return null;
    }

    @Override
    public Location add( Location location ) throws DatabaseException{
        return null;
    }

    @Override
    public void delete( Location location ) throws DatabaseException{
    }

    @Override
    public Location update( Location location ) throws DatabaseException{
        return null;
    }
}
