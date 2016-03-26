package be.krivi.ucll.ip.domain.common;

import be.krivi.ucll.ip.domain.exception.DatabaseException;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public interface CRUD<E extends Entity, K extends Object>{

    E getById( K id ) throws DatabaseException;

    Collection<E> getAll() throws DatabaseException;

    E add( E entity ) throws DatabaseException;

    void delete( E entity ) throws DatabaseException;

    E update( E entity ) throws DatabaseException;
}
