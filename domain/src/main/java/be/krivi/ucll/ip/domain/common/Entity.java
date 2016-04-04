package be.krivi.ucll.ip.domain.common;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Krivi on 21/02/16.
 */
@MappedSuperclass
public class Entity{

    @Id
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "generatorName" )
    @TableGenerator( name = "generatorName", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Override
    public boolean equals( Object obj ){
        if( obj == null || id == null )
            return false;
        if( obj instanceof Entity ){
            return ( (Entity)obj ).getId() != null && ( (Entity)obj ).getId().equals( this.id );
        }
        return false;
    }

    @Override
    public int hashCode(){
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
