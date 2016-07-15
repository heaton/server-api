package com.thoughtworks.training.banking.model;

import com.google.common.base.Objects;

public class Greeting {

  private final long id;
  private final String content;

  public Greeting(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Greeting greeting = (Greeting) o;
    return id == greeting.id &&
        Objects.equal(content, greeting.content);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, content);
  }

}
