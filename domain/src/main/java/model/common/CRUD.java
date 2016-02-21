package model.common;

import model.exception.DatabaseException;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public interface CRUD<E extends Entity, K extends Object>{

    E getById( K id ) throws DatabaseException;

    Collection<E> getAll() throws DatabaseException;

    void add( E entity ) throws DatabaseException;

    void delete( E entity ) throws DatabaseException;

    void update( E entity ) throws DatabaseException;
}
