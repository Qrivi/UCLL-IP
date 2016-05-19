package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Krivi on 21/02/16.
 */
public class ProtectedNetworkMapDB extends CRUDMapDB<ProtectedNetwork> implements ProtectedNetworkDB{

    public ProtectedNetworkMapDB(){
        super();
    }

    @Override
    public Collection<ProtectedNetwork> getAllFromCity( String city ) throws DatabaseException{
        return map.values().stream().filter( ( n ) -> n.getLocation().getCity().equals( city ) ).collect( Collectors.toList() );
    }

}
