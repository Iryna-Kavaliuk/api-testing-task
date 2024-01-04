package jsonplaceholder.api;

import io.restassured.RestAssured;
import jsonplaceholder.api.service.PostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static model.User.getUsersNumber;

public abstract class AbstractTest {

  public static int postsNumber;
  public static int usersNumber;

  @BeforeEach
  public void initialiseURL() {
    RestAssured.baseURI = TestConfiguration.baseEndpoint;
    RestAssured.basePath = TestConfiguration.basePostPath;
  }

  @BeforeAll
  public static void initialisePostsNumber() {
      PostService postService = new PostService();
      postsNumber = postService.getPostsNumber();
  }

  @BeforeAll
  public static void initialiseUsersNumber() {
    usersNumber = getUsersNumber();
  }

}

