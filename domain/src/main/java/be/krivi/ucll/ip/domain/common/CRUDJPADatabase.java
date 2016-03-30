package be.krivi.ucll.ip.domain.common;

import be.krivi.ucll.ip.domain.exception.DatabaseException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Krivi on 30/03/16.
 */
public class CRUDJPADatabase<ENTITY extends Entity> extends JPADatabase implements CRUD<ENTITY, Integer>{

    private final Class<ENTITY> typeClass;

    public CRUDJPADatabase( String name, Class<ENTITY> typeClass ){
        super( name );
        this.typeClass = typeClass;
    }

    @Override
    public ENTITY add( ENTITY obj ){
        EntityManager manager = createManager();
        try{
            manager.getTransaction().begin();
            manager.persist( obj );
            manager.flush();
            manager.getTransaction().commit();
            return obj;
        }catch( Exception e ){
            throw new DatabaseException( "Adding object to database failed: " + obj, e );
        }finally{
            manager.close();
        }
    }

    @Override
    public ENTITY update( ENTITY obj ){
        EntityManager manager = createManager();
        try{
            manager.getTransaction().begin();
            ENTITY o = manager.merge( obj );
            manager.getTransaction().commit();
            return o;
        }catch( Exception e ){
            throw new DatabaseException( "Updating object in database failed: " + obj, e );
        }finally{
            manager.close();
        }
    }

    @Override
    public void delete( ENTITY obj ){
        EntityManager manager = createManager();
        try{
            manager.getTransaction().begin();
            manager.remove( manager.contains( obj ) ? obj : manager.merge( obj ) );
            manager.getTransaction().commit();
        }catch( Exception e ){
            throw new DatabaseException( "Removing object from database failed: " + obj, e );
        }finally{
            manager.close();
        }
    }

    @Override
    public ENTITY getById( Integer id ){
        EntityManager manager = createManager();
        try{
            return manager.find( typeClass, id );
        }catch( Exception e ){
            throw new DatabaseException( "Fetching object from database failed: " + id, e );
        }finally{
            manager.close();
        }
    }

    @Override
    public Collection<ENTITY> getAll() throws DatabaseException{
        EntityManager manager = createManager();
        try{
            return manager.createQuery( "SELECT a FROM " + typeClass.getName() + " a", typeClass ).getResultList();
        }catch( NoResultException ex ){
            return new ArrayList<>();
        }catch( Exception e ){
            throw new DatabaseException( e );
        }finally{
            manager.close();
        }
    }
}