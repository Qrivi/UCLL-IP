package be.krivi.ucll.ip.domain.service;

import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.db.CommentDB;
import be.krivi.ucll.ip.domain.db.NetworkDB;
import be.krivi.ucll.ip.domain.db.DatabaseFactory;
import be.krivi.ucll.ip.domain.db.LocationDB;
import be.krivi.ucll.ip.domain.db.PasswordDB;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.Network;

import java.util.Collection;

/**
 * Created by Krivi on 21/02/16.
 */
public class NetworkService {

    private CommentDB commentDB;
    private LocationDB locationDB;
    private NetworkDB networkDB;
    private PasswordDB passwordDB;

    public NetworkService(String type ){
        commentDB = DatabaseFactory.createCommentDB( type );
        locationDB = DatabaseFactory.createLocationDB( type );
        networkDB = DatabaseFactory.createNetworkDB( type );
        passwordDB = DatabaseFactory.createPasswordDB( type );
    }

    //****************************************************************
    // region Comment
    //****************************************************************

    public Comment getCommentById( Integer id ) throws DatabaseException {
        return commentDB.getById( id );
    }

    public Collection<Comment> getAllComments() throws DatabaseException{
        return commentDB.getAll();
    }

    public void addComment( Comment comment ) throws DatabaseException{
        commentDB.add( comment );
    }

    public void deleteComment( Comment comment ) throws DatabaseException{
        commentDB.delete( comment );
    }

    public void updateComment( Comment comment ) throws DatabaseException{
        commentDB.update( comment );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region Location
    //****************************************************************

    public Location getLocationById(Integer id ) throws DatabaseException {
        return locationDB.getById( id );
    }

    public Collection<Location> getAllLocations() throws DatabaseException{
        return locationDB.getAll();
    }

    public void addLocation( Location location ) throws DatabaseException{
        locationDB.add( location );
    }

    public void deleteLocation( Location location ) throws DatabaseException{
        locationDB.delete( location );
    }

    public void updateLocation( Location location ) throws DatabaseException{
        locationDB.update( location );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region Network
    //****************************************************************

    public Network getNetworkById(Integer id ) throws DatabaseException {
        return networkDB.getById( id );
    }

    public Collection<Network> getAllNetworks() throws DatabaseException{
        return networkDB.getAll();
    }

    public void addNetwork( Network network ) throws DatabaseException{
        networkDB.add( network );
    }

    public void deleteNetwork( Network network ) throws DatabaseException{
        networkDB.delete( network );
    }

    public void updateNetwork( Network network ) throws DatabaseException{
        networkDB.update( network );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region Password
    //****************************************************************

    public Password getPasswordById(Integer id ) throws DatabaseException {
        return passwordDB.getById( id );
    }

    public Collection<Password> getAllPasswords() throws DatabaseException{
        return passwordDB.getAll();
    }

    public void addLocation( Password location ) throws DatabaseException{
        passwordDB.add( location );
    }

    public void deleteLocation( Password password ) throws DatabaseException{
        passwordDB.delete( password );
    }

    public void updateLocation( Password password ) throws DatabaseException{
        passwordDB.update( password );
    }

    //****************************************************************
    // endregion
    //****************************************************************
}
