package jsonplaceholder.api.service;

import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jsonplaceholder.api.TestConfiguration;
import model.Post;

import static io.restassured.RestAssured.given;

public class PostService {

  private String endpoint = TestConfiguration.baseEndpoint + TestConfiguration.basePostPath;

  public Post getPostById(int id) {
    given().queryParam("id", id).when().get(endpoint).then().log().body();
    Response response = given().queryParam("id", id).when().get(endpoint);
    String responseAsSting = response.asString();

    Post[] posts = new Gson().fromJson(responseAsSting, Post[].class);
    return posts[0];
  }

  public int getPostsNumber() {
    Response response = given().when().get(endpoint);
    JsonPath js = new JsonPath(response.asString());
    return js.getInt("data.size()");
  }

}
