package jsonplaceholder.api.negative;

import io.restassured.RestAssured;
import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.TestConfiguration;
import model.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import service.PostFactory;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;

public class DeletePostsNegativeTest extends AbstractTest {

  @Test
  public void testDeleteNonExistingPost() {
    Post newPost = PostFactory.createNewRandomPost();
    int postId = new SecureRandom().nextInt(postsNumber + 1, postsNumber * 2);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    given().
        header("Content-Type", "application/json").
        body(newPost).
        when().
        delete().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
  }

}
