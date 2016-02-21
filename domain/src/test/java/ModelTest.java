import model.Model;
import model.exception.DatabaseException;
import model.exception.DomainException;
import model.wrapper.Comment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Krivi on 21/02/16.
 */
public class ModelTest{

    Model model;

    @Before
    public void before(){
        model = new Model( "MAP" );
    }

    @Test
    public void test_create_new_comment() throws Exception{
        Comment comment = new Comment( "Lorem ipsum dolor sit amet." );

        model.add( comment );
    }

    @Test( expected = DomainException.class )
    public void test_fail_on_creating_empty_comment() throws Exception{
        Comment comment = new Comment( "" );

        model.add( comment );
    }

    @Test
    public void test_get_comment() throws Exception{
        Comment comment = new Comment( "Dolor sit amet." );

        model.add( comment );

        Assert.assertEquals( comment.getComment(), model.getById( comment.getId() ).getComment() );
    }

    @Test( expected = DatabaseException.class )
    public void test_fail_on_get_non_existent_comment() throws Exception{
        Comment comment = new Comment( "Carpe diem." );

        model.getById( comment.getId() );
    }

    @Test
    public void test_delete_comment() throws Exception{
        Comment comment = new Comment( "Acta est fabula" );

        model.add( comment );
        model.delete( comment );
    }

    @Test( expected = DatabaseException.class )
    public void test_fail_on_delete_non_existent_comment() throws Exception{
        Comment comment = new Comment( "Carpe diem." );

        model.delete( comment );
    }

    @Test
    public void test_update_comment() throws Exception{
        Comment comment = new Comment( "Scire est..." );

        model.add( comment );

        comment.setComment( "Scire est mensurare." );

        model.update( comment );

        Assert.assertEquals( comment.getComment(), model.getById( comment.getId() ).getComment() );
    }
}
