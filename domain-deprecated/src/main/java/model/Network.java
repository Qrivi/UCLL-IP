package model;

import model.exception.DomainException;
import model.wrapper.Comment;
import model.wrapper.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krivi on 8/02/16.
 */
public class Network{

    private String ssid;
    private Location location;

    // lists will later change to maps, once their content can be mapped to actual users
    private List<Comment> comments;

    public Network( String ssid, Location location ) throws DomainException{
        setSsid( ssid );
        setLocation( location );

        comments = new ArrayList<>(  );
    }

    public NetworkType getType(){
        return null;
    }

    public String getSsid(){
        return ssid;
    }

    public void setSsid( String ssid ) throws DomainException{
        if( ssid.equals( "" ) )
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
