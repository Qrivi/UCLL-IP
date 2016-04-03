package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;

/**
 * Created by Krivi on 21/02/16.
 */
public class LocationMapDB extends CRUDMapDB<Location> implements LocationDB{

    public LocationMapDB(){
        super();
    }

}
