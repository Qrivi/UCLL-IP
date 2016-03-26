package be.krivi.ucll.ip.domain.network;

import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.exception.DomainException;

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
