package jsonplaceholder.api.positive;

import io.restassured.RestAssured;
import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.TestConfiguration;
import model.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import service.PostFactory;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;

public class DeletePostsTest extends AbstractTest {

  @Test
  public void testDeleteExistingPost() {
    Post newPost = PostFactory.createNewRandomPost();
    int postId = new SecureRandom().nextInt(postsNumber + 1);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    given().
        header("Content-Type", "application/json").
        body(newPost).
        when().
        delete().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_OK);
  }

}
