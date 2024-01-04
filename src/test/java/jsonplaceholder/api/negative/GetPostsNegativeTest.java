package jsonplaceholder.api.negative;

import io.restassured.RestAssured;
import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.TestConfiguration;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static io.restassured.RestAssured.given;

public class GetPostsNegativeTest extends AbstractTest {

  @Test
  public void testGetPostByNonExistingId() {
    int postId = new SecureRandom().nextInt(postsNumber + 1, postsNumber * 2);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    given().
        when().
        get().
        then().
        log().status().
        assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
  }
}
