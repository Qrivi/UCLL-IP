package be.krivi.ucll.ip.domain.network;

import be.krivi.ucll.ip.domain.common.Entity;
import be.krivi.ucll.ip.domain.exception.DomainException;
import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.core.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krivi on 8/02/16.
 */
public abstract class Network extends Entity{

    private String ssid;
    private Location location;

    // lists will later change to maps, once their content can be mapped to actual users
    private List<Comment> comments;

    public Network( String ssid, Location location ) throws DomainException{
        setSsid( ssid );
        setLocation( location );

        comments = new ArrayList<>(  );
    }

    public abstract NetworkType getType();

    public String getSsid(){
        return ssid;
    }

    public void setSsid( String ssid ) throws DomainException{
        if( ssid.isEmpty() )
            throw new DomainException( "Network requires valid SSID" );
        this.ssid = ssid;
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

    public List<Comment> getComments(){
        return comments;
    }
}
