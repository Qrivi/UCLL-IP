package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;
import be.krivi.ucll.ip.domain.network.Network;

/**
 * Created by Krivi on 21/02/16.
 */
public class NetworkMapDB extends CRUDMapDB<Network> implements NetworkDB{

    public NetworkMapDB(){
        super();
    }

}
