package jsonplaceholder.api.negative;

import io.restassured.RestAssured;
import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.TestConfiguration;
import jsonplaceholder.api.service.CommentService;
import model.Comment;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.SAME_THREAD)
public class GetCommentsNegativeTest extends AbstractTest {

  @Test
  public void testGetCommentsToNonExistingPost() {
    int postId = new SecureRandom().nextInt(postsNumber + 2, postsNumber * 2);
    RestAssured.basePath =
        TestConfiguration.basePostPath + "/" + postId + "/" + TestConfiguration.baseCommentPath;
    given().
        when().
        get().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
  }

  @Test
  public void testGetCommentsFilteredByNonExistingPostId() {
    int postId = new SecureRandom().nextInt(postsNumber + 1, postsNumber * 2);
    RestAssured.basePath = TestConfiguration.baseCommentPath;
    CommentService commentService = new CommentService();
    Comment[] comments = commentService.getCommentsFilteredByPostId(postId);
    assertEquals(0, comments.length);
  }
}
