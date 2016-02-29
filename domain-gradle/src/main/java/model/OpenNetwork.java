package model;

import model.exception.DomainException;
import model.wrapper.Location;

/**
 * Created by Krivi on 9/02/16.
 */
public class OpenNetwork extends Network{

    public OpenNetwork( String ssid, Location location ) throws DomainException{
        super( ssid, location );
    }

    @Override
    public NetworkType getType(){
        return NetworkType.OPEN;
    }
}
