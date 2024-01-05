package jsonplaceholder.api.positive;

import jsonplaceholder.api.AbstractTest;
import model.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import service.PostFactory;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static service.PostFactory.MAX_POST_BODY_LENGTH;
import static service.PostFactory.MAX_POST_TITLE_LENGTH;
import static util.RandomStringGenerator.generateAlphaNumericRandomString;

@Execution(ExecutionMode.SAME_THREAD)
public class PostNewPostTest extends AbstractTest {

  @Test
  public void testCreatePostWithAllData() {
    Post newPost = PostFactory.createNewRandomPost();
    given().
        header("Content-Type", "application/json").
        body(newPost).
        when().
        post().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_CREATED).
        body("id", equalTo(postsNumber + 1)).
        body("userId", equalTo(newPost.getUserId())).
        body("title", equalTo(newPost.getTitle())).
        body("body", equalTo(newPost.getBody()));
  }

  @Test
  public void testCreatePostWithUserIdOnly() {
    int userId = new SecureRandom().nextInt(usersNumber + 1);
    String body = String.format("{\"userId\":%s}", userId);
    given().
        header("Content-Type", "application/json").
        body(body).
        when().
        post().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_CREATED).
        body("id", equalTo(postsNumber + 1)).
        body("userId", equalTo(userId));
  }

  @Test
  public void testCreatePostWithTitleOnly() {
    String title = generateAlphaNumericRandomString(MAX_POST_TITLE_LENGTH);
    String requestBody = String.format("{\"title\":\"%s\"}", title);
    given().
        header("Content-Type", "application/json").
        body(requestBody).
        when().
        post().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_CREATED).
        body("id", equalTo(postsNumber + 1)).
        body("title", equalTo(title));
  }

  @Test
  public void testCreatePostWithBodyOnly() {
    String postBody = generateAlphaNumericRandomString(MAX_POST_BODY_LENGTH);
    String requestBody = String.format("{\"body\":\"%s\"}", postBody);
    given().
        header("Content-Type", "application/json").
        body(requestBody).
        when().
        post().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_CREATED).
        body("id", equalTo(postsNumber + 1)).
        body("body", equalTo(postBody));
  }

  @Test
  public void testCreatePostWithSpecialCharsData() {
    Post newPost = PostFactory.createNewRandomPostWithSpecialChars();
    given().
        header("Content-Type", "application/json").
        body(newPost).
        when().
        post().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_CREATED).
        body("id", equalTo(postsNumber + 1)).
        body("userId", equalTo(newPost.getUserId())).
        body("title", equalTo(newPost.getTitle())).
        body("body", equalTo(newPost.getBody()));
  }


}
