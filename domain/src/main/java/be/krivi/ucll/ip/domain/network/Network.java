package be.krivi.ucll.ip.domain.network;

import be.krivi.ucll.ip.domain.common.Entity;
import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.core.Location;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Krivi on 8/02/16.
 */
@javax.persistence.Entity
@Table( name = "network" )
@DiscriminatorColumn( name = "type" )
@Inheritance( strategy = InheritanceType.JOINED )
public abstract class Network extends Entity{

    @NotNull( message = "{NotNull.Network.ssid}" )
    @Column( name = "ssid" )
    private String ssid;

    @NotNull(message = "{NotNull.Password.timestamp}")
    @Temporal( TemporalType.TIMESTAMP )
    private Date timestamp;

    @Valid
    @NotNull( message = "{NotNull.Network.location}" )
    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true )
    private Location location;

    @Valid
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<Comment> comments;

    public Network(){
    }

    public Network( String ssid, Location location ){
        setSsid( ssid );
        setLocation( location );

        comments = new HashSet<>();
    }

    public abstract NetworkType getType();

    public String getSsid(){
        return ssid;
    }

    public void setSsid( String ssid ){
        this.ssid = ssid;
    }

    public Date getTimestamp(){
        return timestamp;
    }

    public void setTimestamp( Date timestamp ){
        this.timestamp = timestamp;
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation( Location location ){
        this.location = location;
    }

    public void addComment( Comment comment ){
        comments.add( comment );
    }

    public void removeComment( Comment comment ){
        comments.remove( comment );
    }

    public Set<Comment> getComments(){
        return comments;
    }
}
