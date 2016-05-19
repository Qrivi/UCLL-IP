package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.OpenNetwork;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Krivi on 21/02/16.
 */
public class OpenNetworkMapDB extends CRUDMapDB<OpenNetwork> implements OpenNetworkDB{

    public OpenNetworkMapDB(){
        super();
    }

    @Override
    public Collection<OpenNetwork> getAllFromCity( String city ) throws DatabaseException{
        return map.values().stream().filter( ( n ) -> n.getLocation().getCity().equals( city ) ).collect( Collectors.toList() );
    }
}
