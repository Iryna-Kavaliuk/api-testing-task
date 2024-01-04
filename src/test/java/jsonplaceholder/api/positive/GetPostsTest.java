package jsonplaceholder.api.positive;

import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.service.PostService;
import model.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPostsTest extends AbstractTest {

  @Test
  public void testGetAllPosts() {
    given().
        when().
        get().
        then().
        log().status().
        assertThat().statusCode(HttpStatus.SC_OK).
        body("size()", is(postsNumber));
  }

  @Test
  public void testGetPostById() {
    PostService postService = new PostService();
    int id = new SecureRandom().nextInt(postsNumber + 1);
    Post post = postService.getPostById(id);
    assertEquals(id, post.getId());
  }

}
