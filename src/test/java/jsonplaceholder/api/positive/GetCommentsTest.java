package jsonplaceholder.api.positive;

import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.service.CommentService;
import model.Comment;
import org.junit.jupiter.api.Test;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCommentsTest extends AbstractTest {

  @Test
  //Testing GET /posts/{id}/comments
  public void testGetCommentsToPost() {
    CommentService commentService = new CommentService();
    int id = new SecureRandom().nextInt(postsNumber + 1);
    Comment[] comments = commentService.getCommentsByPostId(id);
    assertTrue(checkPostIdByValue(id, comments), "Unexpected GET results!");
  }

  @Test
  //Testing GET /comments?postId={id}
  public void testGetCommentsFilteredByPostId() {
    CommentService commentService = new CommentService();
    int id = new SecureRandom().nextInt(postsNumber + 1);
    Comment[] comments = commentService.getCommentsFilteredByPostId(id);
    assertTrue(checkPostIdByValue(id, comments), "Unexpected filtering results!");
  }

  private boolean checkPostIdByValue(int id, Comment[] comments) {
    for(Comment comment : comments) {
      if(comment.getPostId() != id) {
        return false;
      }
    }
    return true;
  }
}
