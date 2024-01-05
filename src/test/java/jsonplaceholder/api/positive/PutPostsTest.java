package jsonplaceholder.api.positive;

import io.restassured.RestAssured;
import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.TestConfiguration;
import model.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import service.PostFactory;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Execution(ExecutionMode.SAME_THREAD)
public class PutPostsTest extends AbstractTest {

  @Test
  public void testUpdatetExistingPost() {
    Post newPost = PostFactory.createNewRandomPost();
    int postId = new SecureRandom().nextInt(postsNumber + 1);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    given().
        header("Content-Type", "application/json").
        body(newPost).
        when().
        put().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_OK).
        body("id", equalTo(postId)).
        body("userId", equalTo(newPost.getUserId())).
        body("title", equalTo(newPost.getTitle())).
        body("body", equalTo(newPost.getBody()));
  }

  @Test
  public void testUpdatePostWithSpecialCharsData() {
    int postId = new SecureRandom().nextInt(postsNumber + 1);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    Post newPost = PostFactory.createNewRandomPostWithSpecialChars();
    given().
        header("Content-Type", "application/json").
        body(newPost).
        when().
        put().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_OK).
        body("id", equalTo(postId)).
        body("userId", equalTo(newPost.getUserId())).
        body("title", equalTo(newPost.getTitle())).
        body("body", equalTo(newPost.getBody()));
  }

}
