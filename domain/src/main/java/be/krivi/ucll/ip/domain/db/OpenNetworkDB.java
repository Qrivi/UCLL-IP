package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUD;
import be.krivi.ucll.ip.domain.common.Database;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.OpenNetwork;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public interface OpenNetworkDB extends Database, CRUD<OpenNetwork, Integer>{

    Collection<OpenNetwork> getAllFromCity( String city ) throws DatabaseException;
}
