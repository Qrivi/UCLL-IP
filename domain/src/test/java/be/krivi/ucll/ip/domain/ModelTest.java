package be.krivi.ucll.ip.domain;

import be.krivi.ucll.ip.domain.service.NetworkService;

/**
 * Created by Krivi on 21/02/16.
 */
public class ModelTest{

    NetworkService networkService;
//
//    @Before
//    public void before(){
//        networkService = new NetworkService( "MAP" );
//    }
//
//    @Test
//    public void test_create_new_comment() throws Exception{
//        Comment comment = new Comment( "Lorem ipsum dolor sit amet." );
//
//        networkService.addComment( comment );
//    }
//
//    @Test( expected = DomainException.class )
//    public void test_fail_on_creating_empty_comment() throws Exception{
//        Comment comment = new Comment( "" );
//
//        networkService.addComment( comment );
//    }
//
//    @Test
//    public void test_get_comment() throws Exception{
//        Comment comment = new Comment( "Dolor sit amet." );
//
//        networkService.addComment( comment );
//
//        Assert.assertEquals( comment.getComment(), networkService.getCommentById( comment.getId() ).getComment() );
//    }
//
//    @Test
//    public void test_delete_comment() throws Exception{
//        Comment comment = new Comment( "Acta est fabula" );
//
//        networkService.addComment( comment );
//        networkService.deleteComment( comment );
//    }
//
//    @Test( expected = DatabaseException.class )
//    public void test_fail_on_delete_non_existent_comment() throws Exception{
//        Comment comment = new Comment( "Carpe diem." );
//
//        networkService.deleteComment( comment );
//    }
//
//    @Test
//    public void test_update_comment() throws Exception{
//        Comment comment = new Comment( "Scire est..." );
//
//        networkService.addComment( comment );
//
//        comment.setComment( "Scire est mensurare." );
//
//        networkService.updateComment( comment );
//
//        Assert.assertEquals( comment.getComment(), networkService.getCommentById( comment.getId() ).getComment() );
//    }
}
