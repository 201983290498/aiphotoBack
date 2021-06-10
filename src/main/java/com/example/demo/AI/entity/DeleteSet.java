package com.example.demo.AI.entity;

public class DeleteSet {
  private String requestId;
  private String state;
  private String body;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  @Override
  public String toString() {
    return "DeleteSet{" +
      "requestId='" + requestId + '\'' +
      ", state='" + state + '\'' +
      ", body='" + body + '\'' +
      '}';
  }

  public DeleteSet(String requestId, String state, String body) {
    this.requestId = requestId;
    this.state = state;
    this.body = body;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public DeleteSet() {

  }
}
