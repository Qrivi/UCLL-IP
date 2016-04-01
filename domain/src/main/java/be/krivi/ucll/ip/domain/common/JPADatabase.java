package be.krivi.ucll.ip.domain.common;

import be.krivi.ucll.ip.domain.exception.DatabaseException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * Created by Krivi on 30/03/16.
 */
public class JPADatabase implements Database{

    private final String name;
    protected static EntityManagerFactory entityManagerFactory;

    public JPADatabase( String name ){
        this.name = name;
    }

    protected EntityManager createManager(){
        if( entityManagerFactory == null || !entityManagerFactory.isOpen() )
            entityManagerFactory = Persistence.createEntityManagerFactory( name );
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void openConnexion(){
        try{
            if( entityManagerFactory == null || !entityManagerFactory.isOpen() )
                entityManagerFactory = Persistence.createEntityManagerFactory( name );
        }catch( PersistenceException pe ){
            throw new DatabaseException( "PersistenceException while trying to open database connexion: " + pe.getMessage() + "\n\n" + pe.getStackTrace() );
        }catch( Exception e ){
            throw new DatabaseException( e );
        }
    }

    @Override
    public void closeConnexion(){
        try{
            entityManagerFactory.close();
        }catch( PersistenceException pe ){
            throw new DatabaseException( "PersistenceException while trying to close database connexion: " + pe.getMessage() + "\n\n" + pe.getStackTrace() );
        }catch( Exception e ){
            throw new DatabaseException( e );
        }
    }
}
