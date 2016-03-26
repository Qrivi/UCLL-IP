package be.krivi.ucll.ip.domain.db;

import be.krivi.ucll.ip.domain.core.Comment;
import be.krivi.ucll.ip.domain.db.map.CRUDMapDB;

/**
 * Created by Krivi on 21/02/16.
 */
public class CommentMapDB extends CRUDMapDB<Comment> implements CommentDB{

    public CommentMapDB(){
        super();
    }

}
