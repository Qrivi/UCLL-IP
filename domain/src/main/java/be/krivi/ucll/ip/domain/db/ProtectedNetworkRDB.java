package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUDJPADatabase;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

/**
 * Created by Krivi on 21/02/16.
 */
public class ProtectedNetworkRDB extends CRUDJPADatabase<ProtectedNetwork> implements ProtectedNetworkDB{

    public ProtectedNetworkRDB( String name ){
        super( name, ProtectedNetwork.class );
    }
}
