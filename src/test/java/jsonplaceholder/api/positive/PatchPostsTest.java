package jsonplaceholder.api.positive;

import io.restassured.RestAssured;
import jsonplaceholder.api.AbstractTest;
import jsonplaceholder.api.TestConfiguration;
import jsonplaceholder.api.service.PostService;
import model.Post;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static service.PostFactory.MAX_POST_BODY_LENGTH;
import static service.PostFactory.MAX_POST_TITLE_LENGTH;
import static util.RandomStringGenerator.generateAlphaNumericRandomString;

public class PatchPostsTest extends AbstractTest {

  @Test
    public void testUpdatePostWithUserId() {
    int postId = new SecureRandom().nextInt(postsNumber + 1);
    int userId = new SecureRandom().nextInt(usersNumber + 1);
    PostService postService = new PostService();
    Post post = postService.getPostById(postId);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    String body = String.format("{\"userId\":%s}", userId);
    given().
        header("Content-Type", "application/json").
        body(body).
        when().
        patch().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_OK).
        body("id", equalTo(postId)).
        body("userId", equalTo(userId)).
        body("title", equalTo(post.getTitle())).
        body("body", equalTo(post.getBody()));
  }

  @Test
  public void testUpdatePostWithTitle() {
    int postId = new SecureRandom().nextInt(postsNumber + 1);
    String title = generateAlphaNumericRandomString(MAX_POST_TITLE_LENGTH);
    PostService postService = new PostService();
    Post post = postService.getPostById(postId);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    String requestBody = String.format("{\"title\":\"%s\"}", title);
    given().
        header("Content-Type", "application/json").
        body(requestBody).
        when().
        patch().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_OK).
        body("id", equalTo(postId)).
        body("title", equalTo(title)).
        body("userId", equalTo(post.getUserId())).
        body("body", equalTo(post.getBody()));
  }

  @Test
  public void testUpdatePostWithBody() {
    int postId = new SecureRandom().nextInt(postsNumber + 1);
    String postBody = generateAlphaNumericRandomString(MAX_POST_BODY_LENGTH);
    PostService postService = new PostService();
    Post post = postService.getPostById(postId);
    RestAssured.basePath = TestConfiguration.basePostPath + "/" + postId;
    String requestBody = String.format("{\"body\":\"%s\"}", postBody);
    given().
        header("Content-Type", "application/json").
        body(requestBody).
        when().
        patch().
        then().
        log().status().
        log().body().
        assertThat().statusCode(HttpStatus.SC_OK).
        body("id", equalTo(postId)).
        body("body", equalTo(postBody)).
        body("userId", equalTo(post.getUserId())).
        body("title", equalTo(post.getTitle()));

  }

}
