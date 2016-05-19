package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUDJPADatabase;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.OpenNetwork;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Krivi on 21/02/16.
 */
public class OpenNetworkRDB extends CRUDJPADatabase<OpenNetwork> implements OpenNetworkDB{

    public OpenNetworkRDB( String name ){
        super( name, OpenNetwork.class );
    }

    @Override
    public Collection<OpenNetwork> getAllFromCity( String city ) throws DatabaseException{
        EntityManager manager = createManager();
        try{
            return manager.createQuery( "SELECT n FROM OpenNetwork n WHERE n.location.city = :city", OpenNetwork.class )
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
