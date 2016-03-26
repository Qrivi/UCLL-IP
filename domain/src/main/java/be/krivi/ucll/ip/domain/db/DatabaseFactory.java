package be.krivi.ucll.ip.domain.db;

/**
 * Created by Krivi on 21/02/16.
 */
public class DatabaseFactory{

    public static CommentDB createCommentDB( String type ){

        if( type.equals( "MAP" ) )
            return new CommentMapDB();
        if( type.equals( "DB" ) )
            return new CommentRDB();
        return null;
    }

    public static LocationDB createLocationDB( String type ){

        if( type.equals( "MAP" ) )
            return new LocationMapDB();
        if( type.equals( "DB" ) )
            return new LocationRDB();
        return null;
    }

    public static NetworkDB createNetworkDB( String type ){

        if( type.equals( "MAP" ) )
            return new NetworkMapDB();
        if( type.equals( "DB" ) )
            return new NetworkRDB();
        return null;
    }

    public static PasswordDB createPasswordDB( String type ){

        if( type.equals( "MAP" ) )
            return new PasswordMapDB();
        if( type.equals( "DB" ) )
            return new PasswordRDB();
        return null;
    }
}
