package be.krivi.ucll.ip.domain.service;

import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.core.Location;
import be.krivi.ucll.ip.domain.core.Password;
import be.krivi.ucll.ip.domain.db.*;
import be.krivi.ucll.ip.domain.exception.DatabaseException;
import be.krivi.ucll.ip.domain.network.Network;
import be.krivi.ucll.ip.domain.network.NetworkType;
import be.krivi.ucll.ip.domain.network.OpenNetwork;
import be.krivi.ucll.ip.domain.network.ProtectedNetwork;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Krivi on 21/02/16.
 */
public class NetworkService{

    private OpenNetworkDB openNetworkDB;
    private ProtectedNetworkDB protectedNetworkDB;
    private CommentDB commentDB;
    private LocationDB locationDB;
    private PasswordDB passwordDB;

    public NetworkService( Properties properties ){
        openNetworkDB = DatabaseFactory.createOpenNetworkDB( properties );
        protectedNetworkDB = DatabaseFactory.createProtectedNetworkDB( properties );
        commentDB = DatabaseFactory.createCommentDB( properties );
        locationDB = DatabaseFactory.createLocationDB( properties );
        passwordDB = DatabaseFactory.createPasswordDB( properties );

        if( properties.getProperty( "type" ).equals( "MAP" ) )
            populateMap();
    }

    private void populateMap(){
        try{
            addProtectedNetwork(
                    new ProtectedNetwork(
                            "McDonalds Free WiFi",
                            new Date(),
                            new Location(
                                    "McDonald's Leuven",
                                    50.879844,
                                    4.700518,
                                    "Kortestraat 7",
                                    "",
                                    "Leuven",
                                    3000,
                                    "Belgium" ),
                            "FriesAreDope"
                    )
            );
            addProtectedNetwork(
                    new ProtectedNetwork(
                            "Leuven HotSpot",
                            new Date(),
                            new Location(
                                    "",
                                    50.879175,
                                    4.701888,
                                    "Grote Markt",
                                    "at Bondgenotenlaan",
                                    "Leuven",
                                    3000,
                                    "Belgium" ),
                            "LeuvenConnects"
                    )
            );
            addOpenNetwork(
                    new OpenNetwork(
                            "OnanConnect",
                            new Date(),
                            new Location(
                                    "Koffie Onan",
                                    50.878573,
                                    4.699040,
                                    "Parijsstraat 28",
                                    "",
                                    "Leuven",
                                    3000,
                                    "Belgium" )
                    )
            );
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    public void openConnection(){
        openNetworkDB.openConnexion();
        protectedNetworkDB.openConnexion();
        commentDB.openConnexion();
        locationDB.openConnexion();
        passwordDB.openConnexion();
    }

    public void closeConnection(){
        openNetworkDB.closeConnexion();
        protectedNetworkDB.closeConnexion();
        commentDB.closeConnexion();
        locationDB.closeConnexion();
        passwordDB.closeConnexion();
    }

    //****************************************************************
    // region Network
    //****************************************************************

    public Network getNetworkById( Integer id ) throws DatabaseException{
        Network network = getOpenNetworkById( id );
        if( network == null )
            return getProtectedNetworkById( id );
        return network;
    }

    public Collection<Network> getAllNetworks() throws DatabaseException{
        return Stream.concat(
                getAllOpenNetworks().stream(),
                getAllProtectedNetworks().stream()
        ).collect( Collectors.toList() );
    }

    public void addNetwork( Network network ) throws DatabaseException{
        if( network.getType() == NetworkType.OPEN )
            openNetworkDB.add( (OpenNetwork)network );
        else if( network.getType() == NetworkType.PROTECTED )
            protectedNetworkDB.add( (ProtectedNetwork)network );
        else
            throw new DatabaseException( "No database for this type of network" );
    }

    public void deleteNetwork( Network network ) throws DatabaseException{
        if( network.getType() == NetworkType.OPEN )
            openNetworkDB.delete( (OpenNetwork)network );
        else if( network.getType() == NetworkType.PROTECTED )
            protectedNetworkDB.delete( (ProtectedNetwork)network );
        else
            throw new DatabaseException( "No database for this type of network" );
    }

    public void updateNetwork( Network network ) throws DatabaseException{
        if( network.getType() == NetworkType.OPEN )
            openNetworkDB.update( (OpenNetwork)network );
        else if( network.getType() == NetworkType.PROTECTED )
            protectedNetworkDB.update( (ProtectedNetwork)network );
        else
            throw new DatabaseException( "No database for this type of network" );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region OpenNetwork
    //****************************************************************

    public OpenNetwork getOpenNetworkById( Integer id ) throws DatabaseException{
        return openNetworkDB.getById( id );
    }

    public Collection<OpenNetwork> getAllOpenNetworks() throws DatabaseException{
        return openNetworkDB.getAll();
    }

    public void addOpenNetwork( OpenNetwork openNetwork ) throws DatabaseException{
        openNetworkDB.add( openNetwork );
    }

    public void deleteOpenNetwork( OpenNetwork openNetwork ) throws DatabaseException{
        openNetworkDB.delete( openNetwork );
    }

    public void updateOpenNetwork( OpenNetwork openNetwork ) throws DatabaseException{
        openNetworkDB.update( openNetwork );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region ProtectedNetwork
    //****************************************************************

    public ProtectedNetwork getProtectedNetworkById( Integer id ) throws DatabaseException{
        return protectedNetworkDB.getById( id );
    }

    public Collection<ProtectedNetwork> getAllProtectedNetworks() throws DatabaseException{
        return protectedNetworkDB.getAll();
    }

    public void addProtectedNetwork( ProtectedNetwork protectedNetwork ) throws DatabaseException{
        protectedNetworkDB.add( protectedNetwork );
    }

    public void deleteProtectedNetwork( ProtectedNetwork protectedNetwork ) throws DatabaseException{
        protectedNetworkDB.delete( protectedNetwork );
    }

    public void updateProtectedNetwork( ProtectedNetwork protectedNetwork ) throws DatabaseException{
        protectedNetworkDB.update( protectedNetwork );
    }

    //****************************************************************
    // endregion
    //****************************************************************

    //****************************************************************
    // region Comment
    //****************************************************************

    public Comment getCommentById( Integer id ) throws DatabaseException{
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

    public Location getLocationById( Integer id ) throws DatabaseException{
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
    // region Password
    //****************************************************************

    public Password getPasswordById( Integer id ) throws DatabaseException{
        return passwordDB.getById( id );
    }

    public Collection<Password> getAllPasswords() throws DatabaseException{
        return passwordDB.getAll();
    }

    public void addPassword( Password password ) throws DatabaseException{
        passwordDB.add( password );
    }

    public void deletePassword( Password password ) throws DatabaseException{
        passwordDB.delete( password );
    }

    public void updatePassword( Password password ) throws DatabaseException{
        passwordDB.update( password );
    }

    //****************************************************************
    // endregion
    //****************************************************************
}
