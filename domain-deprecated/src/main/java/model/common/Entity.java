package model.common;

import java.util.Objects;

/**
 * Created by Krivi on 21/02/16.
 */
public class Entity{

    private Integer id;

    @Override
    public boolean equals( Object obj ){
        if( obj == null || id == null )
            return false;
        if( obj instanceof Entity ){
            if( ( (Entity)obj ).getId() == null )
                return false;
            return ( (Entity)obj ).getId()
                    .equals( this.id );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode( this.id );
        return hash;
    }

    public Integer getId(){
        return id;
    }

    public void setId( Integer id ){
        this.id = id;
    }
}
