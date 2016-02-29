package model.wrapper;

import java.util.Date;

/**
 * Created by Krivi on 21/02/16.
 */
public class Password{

    private String password;
    private Date date;

    private int upvotes;
    private int downvotes;

    /* The user that created the password needs to be able to remove it
    -> wait for Spring, which will handle auth
    private int user_id; ?
    private int network_id
    */

    public Password( String password, Date date, int upvotes, int downvotes ){
        this.password = password;
        this.date = date;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public Password( String password ){
        this( password, new Date(), 0, 0 );
    }

    public void upvote(){
        upvotes++;
    }

    public void downvote(){
        downvotes++;
    }

    public void removeUpvote(){
        upvotes--;
    }

    public void removeDownvote(){
        downvotes--;
    }

    public double getScore(){
        return upvotes - downvotes;
    }

    public int getUpvotes(){
        return upvotes;
    }

    public int getDownvotes(){
        return downvotes;
    }

    public String getPassword(){
        return password;
    }

    public Date getDate(){
        return date;
    }
}
