package jsonplaceholder.api.service;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import jsonplaceholder.api.TestConfiguration;
import model.Comment;

import static io.restassured.RestAssured.given;

public class CommentService {

  public Comment[] getCommentsByPostId(int id) {
    RestAssured.basePath =
        TestConfiguration.basePostPath + "/" + id + "/" + TestConfiguration.baseCommentPath;
    given().when().get().then().log().body();
    String response = given().when().get().asString();
    return new Gson().fromJson(response, Comment[].class);
  }

  public Comment[] getCommentsFilteredByPostId(int postId) {
    RestAssured.basePath = TestConfiguration.baseCommentPath;
    given().queryParam("postId", postId).when().get().then().log().body();
    String response = given().queryParam("postId", postId).when().get().asString();
    return new Gson().fromJson(response, Comment[].class);
  }

}
