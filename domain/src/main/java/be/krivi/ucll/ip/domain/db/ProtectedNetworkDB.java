package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUD;
import be.krivi.ucll.ip.domain.common.Database;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

/**
 * Created by Krivi on 21/02/16.
 */
public interface ProtectedNetworkDB extends Database, CRUD<ProtectedNetwork, Integer> {
}
