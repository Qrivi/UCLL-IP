package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUDJPADatabase;
import be.krivi.ucll.ip.domain.core.Location;

/**
 * Created by Krivi on 21/02/16.
 */
public class LocationRDB extends CRUDJPADatabase<Location> implements LocationDB{

    public LocationRDB( String name ){
        super( name, Location.class );
    }
}
