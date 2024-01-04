package service;

import model.Post;
import util.RandomStringGenerator;
import java.security.SecureRandom;

import static model.User.getUsersNumber;

public class PostFactory {

  public final static int MAX_POST_TITLE_LENGTH = 30;
  public final static int MAX_POST_BODY_LENGTH = 300;

  public static Post createNewRandomPost() {
    int userId = new SecureRandom().nextInt(getUsersNumber() + 1);
    String title = RandomStringGenerator.generateAlphaNumericRandomString(MAX_POST_TITLE_LENGTH);
    String body = RandomStringGenerator.generateAlphaNumericRandomString(MAX_POST_BODY_LENGTH);
    return new Post(userId, title, body);
  }

  public static Post createNewRandomPostWithSpecialChars() {
    int userId = new SecureRandom().nextInt(getUsersNumber() + 1);
    String title =
        RandomStringGenerator.generateRandomStringWithSpecialAndNonUTFSymbols(MAX_POST_TITLE_LENGTH);
    String body =
        RandomStringGenerator.generateRandomStringWithSpecialAndNonUTFSymbols(MAX_POST_BODY_LENGTH);
    return new Post(userId, title, body);
  }

}
