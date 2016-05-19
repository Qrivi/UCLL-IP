package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUDJPADatabase;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public class ProtectedNetworkRDB extends CRUDJPADatabase<ProtectedNetwork> implements ProtectedNetworkDB{

    public ProtectedNetworkRDB( String name ){
        super( name, ProtectedNetwork.class );
    }

    @Override
    public Collection<ProtectedNetwork> getAllFromCity( String city ) throws DatabaseException{
        EntityManager manager = createManager();
        try{
            return manager.createQuery( "SELECT n FROM ProtectedNetwork n WHERE n.location.city = :city", ProtectedNetwork.class )
                    .setParameter( "city", city ).getResultList();
        }catch( NoResultException ex ){
            return new ArrayList<>();
        }catch( Exception e ){
            throw new DatabaseException( e );
        }finally{
            manager.close();
        }
    }
}
