package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUD;
import be.krivi.ucll.ip.domain.common.Database;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public interface ProtectedNetworkDB extends Database, CRUD<ProtectedNetwork, Integer>{

    public Collection<ProtectedNetwork> getAllFromCity( String city ) throws DatabaseException;
}
