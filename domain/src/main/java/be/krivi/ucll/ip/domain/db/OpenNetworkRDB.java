package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUDJPADatabase;
import be.krivi.ucll.ip.domain.network.OpenNetwork;

/**
 * Created by Krivi on 21/02/16.
 */
public class OpenNetworkRDB extends CRUDJPADatabase<OpenNetwork> implements OpenNetworkDB{

    public OpenNetworkRDB( String name ){
        super( name, OpenNetwork.class );
    }
}
