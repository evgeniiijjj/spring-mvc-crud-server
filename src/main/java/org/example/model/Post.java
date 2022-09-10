package org.example.model;

public class Post {
  private long id;
  private String content;
  private transient boolean removed;

  public Post() {
  }

  public Post(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isRemoved() {
    return removed;
  }

  public boolean isActive() {
    return !removed;
  }

  public Post remove() {
    removed = true;
    return this;
  }
}
