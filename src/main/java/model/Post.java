package model;

public class Post {
  private int id;
  private final int userId;
  private final String title;
  private final String body;

  public Post(int userId, String title, String body) {
    this.userId = userId;
    this.title = title;
    this.body = body;
  }

  public int getId() {
    return id;
  }

  public int getUserId() {
    return userId;
  }

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }
}
