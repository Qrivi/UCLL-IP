package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;


public class LocationMapDB extends CRUDMapDB<Location> implements LocationDB {

    public LocationMapDB() {
        super();
    }
}
