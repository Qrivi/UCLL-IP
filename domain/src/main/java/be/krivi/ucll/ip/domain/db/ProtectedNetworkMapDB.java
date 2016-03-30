package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

/**
 * Created by Krivi on 21/02/16.
 */
public class ProtectedNetworkMapDB extends CRUDMapDB<ProtectedNetwork> implements ProtectedNetworkDB{

    public ProtectedNetworkMapDB(){
        super();
    }

}
