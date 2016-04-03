package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.common.CRUDJPADatabase;
import be.krivi.ucll.ip.domain.core.Comment;

/**
 * Created by Krivi on 21/02/16.
 */
public class CommentRDB extends CRUDJPADatabase<Comment> implements CommentDB{

    public CommentRDB( String name ){
        super( name, Comment.class );
    }
}
