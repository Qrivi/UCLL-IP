package be.krivi.ucll.ip.domain.network;

import be.krivi.ucll.ip.domain.common.Entity;
import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.core.Location;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Krivi on 8/02/16.
 */
@javax.persistence.Entity
@Table( name = "network" )
@DiscriminatorColumn( name = "type" )
@Inheritance( strategy = InheritanceType.JOINED )
public abstract class Network extends Entity implements Comparable<Network>{

    @NotNull( message = "{NotNull.Network.ssid}" )
    @Column( name = "ssid" )
    private String ssid;

    @NotNull( message = "{NotNull.Network.timestamp}" )
    @Temporal( TemporalType.TIMESTAMP )
    private Date timestamp;

    @Valid
    @NotNull( message = "{NotNull.Network.location}" )
    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true )
    private Location location;

    @Valid
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true )
    private Set<Comment> comments;

    public Network(){
    }

    public Network( String ssid, Date timestamp, Location location ){
        this.ssid = ssid;
        this.timestamp = timestamp;
        this.location = location;

        comments = new TreeSet<>();
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

    @Override
    public int compareTo( Network network ){
        return timestamp.compareTo( network.timestamp );
    }
}
