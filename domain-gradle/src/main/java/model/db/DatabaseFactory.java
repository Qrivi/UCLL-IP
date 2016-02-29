package model.db;

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
}
