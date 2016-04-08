package be.krivi.ucll.ip.domain.db.map;

import be.krivi.ucll.ip.domain.common.CRUD;
import be.krivi.ucll.ip.domain.common.Entity;
import be.krivi.ucll.ip.domain.exception.DatabaseException;

import java.util.Collection;

public abstract class CRUDMapDB<E extends Entity> extends MapDB<E> implements CRUD<E, Integer>{

    public CRUDMapDB(){
        super();
    }

    @Override
    public E getById( Integer id ) throws DatabaseException{
        return map.get( id );
    }

    @Override
    public Collection<E> getAll() throws DatabaseException{
        return map.values();
    }

    @Override
    public E add( E entity ) throws DatabaseException{
        if( entity == null )
            throw new DatabaseException( "Cannot add empty entity data" );
        int id = generateId();
        entity.setId( id );
        map.put( id, entity );
        return entity;
    }

    @Override
    public void delete( E entity ) throws DatabaseException{
        if( entity == null )
            throw new DatabaseException( "Cannot remove empty entity data" );
        if( !map.containsKey( entity.getId() ) )
            throw new DatabaseException( "Entity to delete does not exist" );
        map.remove( entity.getId() );
    }

    @Override
    public E update( E entity ) throws DatabaseException{
        if( entity == null )
            throw new DatabaseException( "Cannot update empty entity data" );
        if( !map.containsKey( entity.getId() ) )
            throw new DatabaseException( "Entity to update does not exist" );
        map.put( entity.getId(), entity );
        return entity;
    }
}
